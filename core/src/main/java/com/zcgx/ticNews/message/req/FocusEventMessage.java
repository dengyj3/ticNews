package com.zcgx.ticNews.message.req;

/**
 * 关注事件/取消关注事件
 */
public class FocusEventMessage extends BaseMessage {
    // 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
    private String Event;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
