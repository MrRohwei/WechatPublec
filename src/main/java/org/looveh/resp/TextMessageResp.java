package org.looveh.resp;

/**
 *响应消息 （文本消息）
 */
public class TextMessageResp extends BaseMessageResp {
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
