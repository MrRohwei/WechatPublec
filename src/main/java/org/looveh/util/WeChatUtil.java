package org.looveh.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Create with IDEA
 * User:Looveh
 * Date:18/9/26
 * Time:上午9:47
 * Desc:微信工具类
 */

public class WeChatUtil {

    private static String Token = "loovehWechat";

    private static final String APPID = "wxe15fc639a6557325";//测试账号

    private static final String APPSECRET = "fce2829c908cb5e70a0bb9d701ab50bd";//测试账号

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private static Long request_time = null;
    /**
     * 签名验证
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce){
        String[] arr = new String[]{ Token,timestamp,nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * byte数组转换为十六进制字符串
     * @param byteArray
     * @return
     */
    public static String byteToStr(byte[] byteArray){
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * byte转换为十六进制字符串
     * @param mByte
     * @return
     */
    public static String byteToHexStr(byte mByte){
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;

    }

    public static String getAccessToken(){
        long current_seconds = System.currentTimeMillis() / 1000;
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        System.out.println(url);
        String s = "";
        //请求时间为空 || 当前时间距离请求时间超过7000秒
//        if(request_time == null || current_seconds - request_time >= 7000) {
            request_time = current_seconds;
            s = HttpUtil.postReq(url, null);
//        }
        System.out.println("返回:" + s);
        return "";
    }

    public static void main(String[] args) {
        getAccessToken();
    }
}
