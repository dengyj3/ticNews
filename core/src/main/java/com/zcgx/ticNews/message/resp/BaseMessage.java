package com.zcgx.ticNews.message.resp;

public class BaseMessage {
    // 接收方账号(收到的OpenID)
    private String toUserName;
    // 开发者微信号
    private String fromUserName;
    // 消息创建时间
    private long createTime;
    // 消息类型(text/music/news)
    private String msgType;
    // 位0x0001被标志时, 星标刚收到的消息
    private long funcFlag;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public long getFuncFlag() {
        return funcFlag;
    }

    public void setFuncFlag(long funcFlag) {
        this.funcFlag = funcFlag;
    }
}
