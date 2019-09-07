package cn.navigational.utils;

public class Assert {
    /**
     * 检测字符串是否为空
     *
     * @param str
     * @return 为空返回{true} 不为空返回 false
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().equals("")) {
            return true;
        }
        return false;
    }
}
