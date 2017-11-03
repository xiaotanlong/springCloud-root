package com.weixin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @ProjectName springcloudroot
 * @PackageName com.weixin.controller
 * @Author tanjianglong
 * @CreatedTime 2017/10/30.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@RestController
@RequestMapping(value = "/WeixinController")
public class WeixinController {
    // 设置一个全局的token,开发者自己设置。api这样解释：Token可由开发者可以任意填写，
    // 用作生成签名（该Token会和接口URL中包含的Token进行比对，从而验证安全性）
    @Value("weixin.token")
    private String token ;

    @GetMapping(value = "shakeHands")
    public void shakeHands(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String TAG = "WeixinController";
        // 设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("html/text;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        // 获取输出流
        PrintWriter printWriter = response.getWriter();

        // 根据api说明，获取上述四个参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        // // temp:临时打印，观看返回参数情况
        System.out.println(TAG + ":signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + ",echostr:" + echostr);
        // 根据api所说的“加密/校验流程”进行接入。共计三步

        // 第一步:将token、timestamp、nonce三个参数进行字典序排序
        String[] parms = new String[] { token, timestamp, nonce };// 将需要字典序排列的字符串放到数组中
        Arrays.sort(parms);// 按照api要求进行字典序排序
        // 第二步:将三个参数字符串拼接成一个字符串进行sha1加密
        // 拼接字符串
        String parmsString = "";// 注意，此处不能=null。
        for (int i = 0; i < parms.length; i++) {
            parmsString += parms[i];
        }
        // sha1加密
        String mParms = null;// 加密后的结果
        MessageDigest digest = null;
        try {
            digest = java.security.MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        digest.update(parmsString.getBytes());
        byte messageDigest[] = digest.digest();
        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        mParms = hexString.toString();// 加密结果

        /*
         * api要求： 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容， 则接入生效， 成为开发者成功，否则接入失败。
         */
        // 第三步： 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信接入成功。
        System.out.println(TAG + ":" + mParms + "---->" + signature);
        if (mParms.equals(signature)) {
            System.out.println(TAG + ":" + mParms + "---->" + signature);
            printWriter.write("echostr:" + echostr);
        } else {
            // 接入失败,不用回写
            System.out.println(TAG + "接入失败");
        }
    }
}
