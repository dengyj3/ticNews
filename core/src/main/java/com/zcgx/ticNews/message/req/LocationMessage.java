package com.zcgx.ticNews.message.req;

public class LocationMessage extends BaseMessage {
    // 地理位置纬度
    private String locationX;
    // 地理位置经度
    private String locationY;
    // 地图缩放大小
    private String scale;
    // 地理位置信息
    private String label;

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
