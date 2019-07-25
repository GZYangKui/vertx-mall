package cn.navigational.base;

import cn.navigational.annotation.*;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static cn.navigational.config.Constants.*;


public class VertxApplication {

    //配置文件路径
    private String config = "config/config.json";

    //vertx 实例
    private final Vertx vertx = Vertx.vertx();


    private final Logger logger = LogManager.getLogger();

    private final JsonArray apiList = new JsonArray();

    private final DeploymentOptions options = new DeploymentOptions();

    private final List<String> verticle = new ArrayList<>();


    /**
     * 初始化应用
     */
    public void init() {
        final FileSystem fs = vertx.fileSystem();
        final Application app = this.getClass().getDeclaredAnnotation(Application.class);
        final ScanPackage scanPackage = this.getClass().getDeclaredAnnotation(ScanPackage.class);

        if (app != null) {
            config = app.config();
        }

        final JsonObject appConfig = fs.readFileBlocking(config).toJsonObject();

        scanVerticle(scanPackage.packages());

        appConfig.put(API, apiList);

        options.setConfig(appConfig);

        if (!verticle.isEmpty()) {
            verticle.forEach(_clazz -> {
                logger.info("start deploy {}", _clazz);
                vertx.deployVerticle(_clazz, options, _rs -> {
                    if (_rs.succeeded()) {
                        logger.info("{} deployment success!", _clazz);
                    } else {
                        _rs.cause().printStackTrace();
                        logger.error("{} deployment failed:{}", _clazz, _rs.cause().getMessage());
                    }
                });
            });
        }
    }

    /**
     * 扫描@Verticle注解和@RequestMapping注解
     *
     * @param pack 所需要扫描的包路径
     */
    private void scanVerticle(String... pack) {
        if (pack.length == 0) {
            return;
        }
        for (String path : pack) {
            try {
                final Enumeration<URL> clazz = getClass().getClassLoader().getResources(path.replaceAll("\\.", "/"));
                while (clazz.hasMoreElements()) {
                    final URL url = clazz.nextElement();
                    if (url != null) {
                        //文件系统
                        if (url.getProtocol().equals("file")) {
                            final String packagePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
                            addClass(packagePath, path);
                        } else {
                            //Jar
                            logger.info("class type:{}", "jar");
                            verticle.addAll(scanClassFromJar(url, path));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("scan package failed:{}", e.getCause().getMessage());
            }
        }
    }

    /**
     * 从jar里面读取class路径
     *
     * @param url 初始包路径
     */
    private List<String> scanClassFromJar(final URL url, final String path) throws IOException {
        final List<String> list = new ArrayList<>();
        final JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        if (Objects.nonNull(jarURLConnection) && Objects.nonNull(jarURLConnection.getJarFile())) {
            final JarFile jarFile = jarURLConnection.getJarFile();
            final Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                final JarEntry jarEntry = entries.nextElement();
                final String jarEntryName = jarEntry.getName();
                final String prefix = jarEntryName.replaceAll("/", "\\.");
                if (prefix.startsWith(path) && jarEntryName.endsWith(".class")) {
                    final String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", "\\.");
                    final boolean cl = isFitClass(className);
                    if (cl) {
                        logger.info(className);
                        list.add(className);
                    }
                }
            }

        }
        return list;
    }

    /**
     * 添加class到list之中
     *
     * @param packPath 包路径
     * @param packName 包名
     * @return 包含该路径下所有class文件的list集合
     */
    private void addClass(String packPath, String packName) {
        final File[] files = new File(packPath).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return dir.isFile() && name.endsWith(".class") || dir.isDirectory();
            }
        });
        for (File file : files) {
            final String fileName = file.getName();
            if (file.isFile()) {
                final String className = packName + "." + fileName.split("\\.")[0];
                final boolean clazz = isFitClass(className);
                if (clazz) {
                    verticle.add(className);
                }
            } else {
                final String subPath = packPath + File.separator + fileName;
                final String subPackage = packName + "." + fileName;
                addClass(subPath, subPackage);
            }
        }
    }

    /**
     * 判断是否标注@Verticle注解
     */
    private boolean isFitClass(String className) {
        final Class clazz;
        try {
            clazz = Class.forName(className);
            if (clazz.getDeclaredAnnotation(Verticle.class) != null) {
                final Router router = (Router) clazz.getAnnotation(Router.class);
                if (router == null) {
                    return true;
                }
                final Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    final RequestMapping map = method.getAnnotation(RequestMapping.class);
                    if (map == null) {
                        return true;
                    }
                    final JsonObject api = new JsonObject();
                    final JsonArray validator = new JsonArray();
                    for (String s : map.validators()) {
                        validator.add(s);
                    }
                    api.put(API, router.api() + map.api());
                    api.put(HTTP_METHOD, map.method().name());
                    api.put("comment", map.description());
                    api.put("validator", validator);
                    apiList.add(api);
                }
                return true;
            }
        } catch (ClassNotFoundException e) {
            logger.error("class {} not found!", className);
        }
        return false;
    }

    /**
     * 设置配置文件路径
     *
     * @param config 配置文件
     */
    @Fluent
    public VertxApplication setConfig(String config) {
        this.config = config;
        return this;
    }


    public DeploymentOptions getOptions() {
        return options;
    }

}
