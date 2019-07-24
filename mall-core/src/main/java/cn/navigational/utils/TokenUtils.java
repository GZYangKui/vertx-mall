package cn.navigational.utils;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TokenUtils {

    //使用java生成对应的Key
    public static Key generateKey(String secretStr) {
        byte[] encode = Base64.getDecoder().decode(secretStr);
        return new SecretKeySpec(encode, 0, encode.length, "HmacSHA256");
    }

    /**
     * sha1加密
     *
     * @param data
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException
     */
    public static String sha1(String data) throws NoSuchAlgorithmException {
        //加盐   更安全一些
        data += "lyz";
        //信息摘要器                                算法名称
        final MessageDigest md = MessageDigest.getInstance("SHA1");
        //把字符串转为字节数组
        byte[] b = data.getBytes();
        //使用指定的字节来更新我们的摘要
        md.update(b);
        //获取密文  （完成摘要计算）
        byte[] b2 = md.digest();
        //获取计算的长度
        int len = b2.length;
        //16进制字符串
        String str = "0123456789abcdef";
        //把字符串转为字符串数组
        char[] ch = str.toCharArray();

        //创建一个40位长度的字节数组
        char[] chs = new char[len * 2];
        //循环20次
        for (int i = 0, k = 0; i < len; i++) {
            byte b3 = b2[i];//获取摘要计算后的字节数组中的每个字节
            // >>>:无符号右移
            // &:按位与
            //0xf:0-15的数字
            chs[k++] = ch[b3 >>> 4 & 0xf];
            chs[k++] = ch[b3 & 0xf];
        }
        //字符数组转为字符串
        return new String(chs);
    }
}
