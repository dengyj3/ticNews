package com.zcgx.ticNews.message.resp;

public class BaseMessage {
    // 接收方账号(收到的OpenID)
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间
    private long CreateTime;
    // 消息类型(text/music/news/event)
    private String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String ToUserName) {
        this.ToUserName = ToUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String FromUserName) {
        this.FromUserName = FromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String MsgType) {
        this.MsgType = MsgType;
    }

}
