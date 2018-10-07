package com.zcgx.ticNews.message.req;



/**
 * 自定义菜单事件推送
 */
public class CustomeMenuEventMessage extends BaseMessage {
    // 点击菜单拉取消息时的事件推送CLICK 或 点击菜单跳转链接时的事件推送VIEW
    private String Event;
    // 事件KEY值，与自定义菜单接口中KEY值对应 或 事件KEY值，设置的跳转URL
    private String EventKey = "EventKey";

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
