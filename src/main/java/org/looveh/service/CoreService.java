package org.looveh.service;

import org.looveh.resp.Image;
import org.looveh.resp.ImageMessageResp;
import org.looveh.resp.TextMessageResp;
import org.looveh.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

public class CoreService {
    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            Iterator<Map.Entry<String, String>> iterator = requestMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                System.out.println(next.getKey()+"--"+next.getValue());
            }

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
//                respContent = "您发送的是" + requestMap.get("Content");
                respContent = "百度网址<a href=\"www.baidu.com\">www.baidu.com</a>";
                // 回复文本消息
                respMessage = initTextMessage(respContent, fromUserName, toUserName);
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
//                respContent = "您发送的是图片消息！";
                respMessage = initImageMessage(requestMap.get("MediaId"), fromUserName, toUserName);
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "感谢您的关注！\n目前公众号正在维护中。请等候公众号开放/微笑";
                    respMessage = initTextMessage(respContent,fromUserName,toUserName);

                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 自定义菜单权没有开放，暂不处理该类消息
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(respMessage);
        return respMessage;
    }

    public static String initTextMessage(String respContent, String fromUserName, String toUserName) {
        String respMessage = "";
        TextMessageResp textMessage = new TextMessageResp();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setFuncFlag(0);
        textMessage.setContent(respContent);
        respMessage = MessageUtil.textMessageToXml(textMessage);
        return respMessage;
    }

    public static String initImageMessage(String mediaId, String fromUserName, String toUserName) {
        String respMessage = "";
        Image image = new Image();
        image.setMediaId(mediaId);
        ImageMessageResp imageMessageResp = new ImageMessageResp();
        imageMessageResp.setToUserName(fromUserName);
        imageMessageResp.setFromUserName(toUserName);
        imageMessageResp.setCreateTime(System.currentTimeMillis());
//        imageMessageResp.setFuncFlag(0);
        imageMessageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
        imageMessageResp.setImage(image);
        respMessage = MessageUtil.imageMessageToXml(imageMessageResp);
        return respMessage;
    }

    public static void main(String[] args) {
        System.out.println("卢威".getBytes().length);
    }
}
