package com.eureka.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * @author tjl
 * @version V1.0
 * @Title: HttpRequestUtil
 * @Package util
 * @Description: 通过http调用第三方接口(用一句话描述该文件做什么)
 * @date 2018-4-11
 */
public class HttpRequestUtil {



    public static String readContentFromPost(String url, Map<String, Object> parms) throws IOException {
        // Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(url);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        connection.setRequestMethod("POST");
        // Post 请求不能使用缓存
        connection.setUseCaches(false);
        // 设置本次连接是否自动重定向
        connection.setInstanceFollowRedirects(true);
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        StringBuffer content = new StringBuffer();
        if (!parms.isEmpty()) {
            Set<String> keys = parms.keySet();
            for (String string : keys) {
                content.append(string + "=" + URLEncoder.encode(String.valueOf(parms.get(string)), "utf-8")).append("&");
            }

        }
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        out.writeBytes(content.toString());
        // 流用完记得关
        out.flush();
        out.close();
        // 获取响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuffer req = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            req.append(line);
        }
        reader.close();
        // 该干的都干完了,记得把连接断了
        connection.disconnect();

        return req.toString();
    }

    public static void main(String[] args) {

        try {
            JSONObject jsStr = new JSONObject();
            Map<String, Object> map = new HashMap<>();
            map.put("projectName", "da");
            jsStr = JSONObject.parseObject(readContentFromPost("https://www.baidu.com/", map));
            System.out.println(jsStr.toJSONString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
