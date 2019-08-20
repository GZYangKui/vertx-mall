package cn.navigational.admin.client.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;


public class ResourceUtils {
    private static final Logger logger = LogManager.getLogger(ResourceUtils.class);

    /**
     * 加载fxml文件
     *
     * @param url 文件url
     * @return 如果加载成功返回{@link javafx.scene.Parent},否则返回null
     */
    public static Parent loadFXML(final String url) {
        Parent root = null;
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource(url));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("加载fxml文件失败:{}", e.getCause().getMessage());
        }
        return root;
    }

    /**
     * 加载css文件
     *
     * @param url
     * @return 返回css URL对象
     */
    public static URL loadCSS(final String url) {
        return ClassLoader.getSystemResource(url);
    }
}
