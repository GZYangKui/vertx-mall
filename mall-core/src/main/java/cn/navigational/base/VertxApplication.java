package cn.navigational.base;

import cn.navigational.annotation.Application;
import cn.navigational.annotation.ScanPackage;
import cn.navigational.annotation.Verticle;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AbstractVerticle;
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
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static cn.navigational.config.Constants.API;


public class VertxApplication {
    /**
     * 配置文件路径
     */
    private String config = "config/config.json";
    /**
     * api定义文件路径
     */
    private String api = "config/api.json";

    /**
     * vertx实例对象
     */
    private final Vertx vertx = Vertx.vertx();

    private final Logger logger = LogManager.getLogger();

    /**
     * 初始化应用
     */
    public void init() {
        final FileSystem fs = vertx.fileSystem();
        System.out.println(fs.readFileBlocking("config/banner.txt").toString());
        final Application annotation = this.getClass().getDeclaredAnnotation(Application.class);
        final ScanPackage scanPackage = this.getClass().getDeclaredAnnotation(ScanPackage.class);
        if (annotation != null) {
            api = annotation.api();
            config = annotation.config();
        }
        final JsonObject appConfig = fs.readFileBlocking(config).toJsonObject();
        final JsonArray apiList = fs.readFileBlocking(api).toJsonArray();
        final DeploymentOptions options = new DeploymentOptions();
        appConfig.put(API, apiList);
        options.setConfig(appConfig);

        if (scanPackage != null) {
            scanVerticle(scanPackage.packages()).forEach(_clazz -> {
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
     * 扫描verticle
     *
     * @param pack
     * @return
     */
    private List<String> scanVerticle(String... pack) {
        final List<String> verticle = new ArrayList<>();
        if (pack.length == 0) {
            return verticle;
        }
        for (String path : pack) {
            try {
                final Enumeration<URL> clazz = getClass().getClassLoader().getResources(path.replaceAll("\\.", "/"));
                while (clazz.hasMoreElements()) {
                    final URL url = clazz.nextElement();
                    if (url != null) {
                        //文件系统
                        if (url.getProtocol().equals("file")) {
                            final String packagePath = URLDecoder.decode(url.getFile(), "UTF-8");
                            addClass(packagePath, path, verticle);
                        } else {
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
        return verticle;
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
    private void addClass(String packPath, String packName, List<String> list) {
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
                    list.add(className);
                }
            } else {
                final String subPath = packPath + File.separator + fileName;
                final String subPackage = packName + "." + fileName;
                addClass(subPath, subPackage, list);
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

    /**
     * 设置api定义文件
     *
     * @param api api文件路径
     */
    @Fluent
    public VertxApplication setApi(String api) {
        this.api = api;
        return this;
    }
}
