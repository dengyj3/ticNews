package com.zcgx.ticNews.message.req;

public class BaseMessage {
    // 开发者微信号
    private String ToUserName;
    // 发送方账号(一个OpenID)
    private String FromUserName;
    // 消息创建时间
    private long CreateTime;
    // 消息类型(text/image/location/link)
    private String MsgType;
    // 消息id, 64位整型
    private long MsgId;

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

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long MsgId) {
        this.MsgId = MsgId;
    }
}
