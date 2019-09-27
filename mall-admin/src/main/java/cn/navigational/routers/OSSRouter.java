package cn.navigational.routers;

import cn.navigational.annotation.Router;
import cn.navigational.annotation.RouterMapping;
import cn.navigational.annotation.Verticle;
import cn.navigational.impl.RouterVerticle;
import cn.navigational.model.EBRequest;
import cn.navigational.utils.ExceptionUtils;
import cn.navigational.utils.ResponseUtils;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import static cn.navigational.config.Constants.HOST;
import static cn.navigational.utils.ExceptionUtils.nullableStr;
import static cn.navigational.utils.ResponseUtils.responseFailed;
import static cn.navigational.utils.ResponseUtils.responseSuccessJson;

/**
 * 阿里云oss配置路由
 *
 * @author YangKUi
 * @since 1.0
 */
@Verticle
@Router(api = "/api/oss")
public class OSSRouter extends RouterVerticle {
    /***************************************************
     *   OSS配置信息,实际开发替换为自己的oss信息             *
     * *************************************************/
    /****oss sign expire time(unit is seconds)*****/
    private int expire = 60;
    /****oss allow upload max size file****/
    private long maxSize = 5 * 1024 * 1024;
    /****oss buckname*****/
    private String bucketName = "vertx-mall";
    /*****oss endpoint*****/
    private String endpoint = "oss-cn-beijing.aliyuncs.com";
    /******oss accessKeyId*******/
    private String accessKeyId = "*";
    /******oss secret*******/
    private String secret = "*";


    private static final Logger logger = LogManager.getLogger(OSSRouter.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);
    }

    @RouterMapping(api = "/policy", description = "签阿里云oss")
    public void policy(final EBRequest request, final Promise<JsonObject> response) {
        ///签名属于耗时任务///
        vertx.<JsonObject>executeBlocking(fut -> {
            String url = "https://" + bucketName + "." + endpoint;
            String dir = "vertx-mall/" + randomDir();
            OSSClient client = new OSSClient(endpoint, accessKeyId, secret);
            PolicyConditions conditions = new PolicyConditions();
            conditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            conditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = client.generatePostPolicy(new Date(expire), conditions);
            byte[] bytes = postPolicy.getBytes(StandardCharsets.UTF_8);
            String policy = BinaryUtil.toBase64String(bytes);
            //生成签名
            String signature = client.calculatePostSignature(policy);
            JsonObject result = new JsonObject();
            result.put("accessKeyId", client.getCredentialsProvider().getCredentials().getAccessKeyId());
            result.put("policy", policy);
            result.put("signature", signature);
            result.put("dir", dir);
            result.put(HOST, url);
            fut.complete(result);
        }, ar -> {
            if (ar.failed()) {
                logger.error("oss签名失败:{}", nullableStr(ar.cause()));
                response.complete(responseFailed("签名失败", 200));
            } else {
                response.complete(responseSuccessJson(ar.result()));
            }
        });

    }

    /**
     * 根据日期生成文件夹
     *
     * @return 返回日期文件夹名称 例如:2019-09-27
     */
    private static String randomDir() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }
}
