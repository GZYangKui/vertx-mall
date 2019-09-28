package cn.navigational.base;

import cn.navigational.annotation.Application;
import cn.navigational.annotation.ScanPackage;
import cn.navigational.annotation.Verticle;
import cn.navigational.utils.ExceptionUtils;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static cn.navigational.utils.ExceptionUtils.nullableStr;

/**
 *
 * Vert.x启动类,一般配合着{@link Application} 和 {@link ScanPackage}使用
 *
 * @author YangKui
 * @since 1.0
 *
 */
public class VertxApplication {

    //vertx 实例
    private Vertx vertx = Vertx.vertx();


    private Logger logger = LogManager.getLogger();


    private DeploymentOptions options = new DeploymentOptions();


    public void init() {

        Application app = this.getClass().getDeclaredAnnotation(Application.class);
        ScanPackage scanPackage = this.getClass().getDeclaredAnnotation(ScanPackage.class);

        //判断配置文件是否为空
        if (app != null) {
            JsonObject appConfig = initConfig(app.configs());
            options.setConfig(appConfig);
        }

        scanVerticle(scanPackage.packages());

        String[] packages = scanPackage.packages();

        List<String> verticle = scanVerticle(packages);

        //开始部署verticle
        for (String clazz : verticle) {
            logger.info("start deploy {}", clazz);
            vertx.deployVerticle(clazz, options, ar -> {
                if (ar.succeeded()) {
                    logger.info("{} deployment success!", clazz);
                } else {
                    logger.error("{} deployment failed:{}", nullableStr(ar.cause()));
                }
            });
        }
    }

    /**
     * 扫描@Verticle注解和@RequestMapping注解
     *
     * @param pack 所需要扫描的包路径
     */
    private List<String> scanVerticle(String... pack) {
        if (pack.length == 0) {
            return List.of();
        }
        List<String> verticle = new ArrayList<>();
        for (String path : pack) {
            try {
                Enumeration<URL> clazz = getClass().getClassLoader().getResources(path.replaceAll("\\.", "/"));
                while (clazz.hasMoreElements()) {
                    URL url = clazz.nextElement();
                    if (url != null) {
                        //文件系统
                        if (url.getProtocol().equals("file")) {
                            String packagePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
                            addClass(packagePath, path, verticle);
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
        return verticle;
    }

    /**
     * 从jar里面读取class路径
     *
     * @param url 初始包路径
     */
    private List<String> scanClassFromJar(URL url, String path) throws IOException {
        List<String> list = new ArrayList<>();
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        if (Objects.nonNull(jarURLConnection) && Objects.nonNull(jarURLConnection.getJarFile())) {
            JarFile jarFile = jarURLConnection.getJarFile();
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String jarEntryName = jarEntry.getName();
                String prefix = jarEntryName.replaceAll("/", "\\.");
                if (prefix.startsWith(path) && jarEntryName.endsWith(".class")) {
                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", "\\.");
                    boolean cl = isFitClass(className);
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
    private void addClass(String packPath, String packName, List<String> verticle) {
        File[] files = new File(packPath).listFiles((dir, name) -> dir.isFile() && name.endsWith(".class") || dir.isDirectory());
        assert files != null;
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = packName + "." + fileName.split("\\.")[0];
                boolean clazz = isFitClass(className);
                if (clazz) {
                    verticle.add(className);
                }
            } else {
                String subPath = packPath + File.separator + fileName;
                String subPackage = packName + "." + fileName;
                addClass(subPath, subPackage, verticle);
            }
        }
    }

    /**
     * 判断是否标注@Verticle注解
     */
    private boolean isFitClass(String className) {
        Class clazz;
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


    public DeploymentOptions getOptions() {
        return options;
    }

    /**
     * 初始化配置信息
     *
     * @param configs 配置信息数组
     */
    private JsonObject initConfig(String[] configs) {
        JsonObject obj = new JsonObject();
        FileSystem fs = vertx.fileSystem();
        for (String config : configs) {
            JsonObject o = fs.readFileBlocking(config).toJsonObject();
            obj.mergeIn(o);
        }
        return obj;
    }
}
