package com.zcgx.ticNews.message.req;

public class ImageMessage extends BaseMessage {
    // 图片链接
    private String picUrl;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
