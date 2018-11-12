package org.looveh.util;

import java.io.*;
import java.net.*;

/**
 * Create with IDEA
 * User:Looveh
 * Date:18/9/27
 * Time:上午11:37
 * Desc:访问网络
 */

public class HttpUtil {

    /**
     *
     * @param urlAdd 请求路径
     * @param content 请求体(参数)
     * @return
     */
    public static String postReq(String urlAdd,String content) {

//        PrintWriter out = null;
        try {
            //打开url连接
            URL url = new URL(urlAdd);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求属性
//            httpConn.setRequestProperty("Content-Type","application/json");
//            httpConn.setRequestProperty("x-adviewrtb-version", "2.1");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            if(null != content){
                content = URLEncoder.encode(content, "utf-8");//防止乱码
                dos.writeBytes(content);
            }

            dos.flush();
            dos.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
            br.close();
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String lw = postReq("http://www.baidu.com", "卢威");
        System.out.println(lw);
    }
}
