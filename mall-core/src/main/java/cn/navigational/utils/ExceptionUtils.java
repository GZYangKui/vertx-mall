package cn.navigational.utils;

import java.util.Optional;

public class ExceptionUtils {
    /**
     * 处理可空异常
     *
     * @param e 异常信息
     * @return 返回异常信息字符串
     */
    public static String nullableStr(Throwable e) {
        e.printStackTrace();
        return Optional.ofNullable(e.getCause()).orElse(new Exception("null")).getMessage();
    }
}
