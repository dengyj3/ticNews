package com.zcgx.ticNews.message.req;

public class LinkMessage extends BaseMessage {
    // 消息标题
    private String title;
    // 消息描述
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 消息链接
    private String url;
}
