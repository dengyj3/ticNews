package com.zcgx.ticNews.message.resp;

public class TextMessage extends BaseMessage {
    private long MsgId;
    private String Content;

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
}
