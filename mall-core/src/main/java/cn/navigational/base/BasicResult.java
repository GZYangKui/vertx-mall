package cn.navigational.base;

/**
 * 响应http请求,返回基本信息(自定义状态码+自定义回复消息)
 *
 * @author YangKui
 * @since 2.0
 */
public class BasicResult {
    //自定义状态码
    private int code = 200;
    //自定义回复消息
    private String message = "ok";


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 处理成功
     */
    public static BasicResult done(String message) {
        BasicResult result = new BasicResult();

        result.setMessage(message);

        return result;
    }

    /**
     *
     * 一些提示语
     *
     */
    public static BasicResult tips(String msg){
        return tips(msg,300);
    }

    /**
     *
     * 封装提示信息
     * @param msg 提示消息
     * @param code 自定义状态码
     *
     */
    public static BasicResult tips(String msg, int code){

        BasicResult result = new BasicResult();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
}
