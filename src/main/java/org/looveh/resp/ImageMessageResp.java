package org.looveh.resp;

import org.looveh.req.BaseMessageReq;

/**
 * Create with IDEA
 * User:Looveh
 * Date:18/9/27
 * Time:上午10:42
 * Desc:请求图片消息
 */

public class ImageMessageResp extends BaseMessageResp {

//    图片链接（由系统生成）
    private String PicUrl;
//    图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
//    private String MediaId;
    private Image image;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

//    public String getMediaId() {
//        return MediaId;
//    }
//
//    public void setMediaId(String mediaId) {
//        MediaId = mediaId;
//    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
