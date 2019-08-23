package cn.navigational.admin.client.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;


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
            logger.error("加载fxml文件失败:{}", Optional.ofNullable(e.getCause()).orElse(new Exception("图片不存在")).getMessage());
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

    /**
     * 加载图片资源
     *
     * @param url    图片地址
     * @param width  图片宽度
     * @param height 图片高度
     * @return 返回Image对象 如果加载成功 返回实例对象 否则返回null
     */
    public static Image loadImage(final String url, final double width, final double height) {
        Image image = null;

        try {
            image = new Image(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(url)), width, height, false, true);

        } catch (NullPointerException e) {

            logger.error("加载图片资源失败,原因:{}", Optional.of(e.getCause()).orElse(new Exception("图片不存在")).getMessage());
        }
        return image;
    }
}
