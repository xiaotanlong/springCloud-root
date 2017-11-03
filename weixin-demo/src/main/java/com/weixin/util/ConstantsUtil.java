package com.weixin.util;

/**
 * @ProjectName springcloudroot
 * @PackageName com.weixin.util
 * @Author tanjianglong
 * @CreatedTime 2017/10/30.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
public class ConstantsUtil {
    /**获取access_token 接口url*/
    public static String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";
    /**获取access_token 接口url*/
    public static String getGetAccessTokenUrl(String appid,String secret)
    {
        return GET_ACCESS_TOKEN + "appid=" + appid + "&secret=" + secret;
    }
}
