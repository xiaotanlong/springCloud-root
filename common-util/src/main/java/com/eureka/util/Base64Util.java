package com.eureka.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @ProjectName springcloudroot
 * @PackageName com.eureka.util
 * @Author tanjianglong
 * @CreatedTime 2017/9/8.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
public class Base64Util {
    /**
     * Base64加密
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * Base64解密
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

}
