package com.zcgx.ticNews.params;

/**
 * 请求二维码入参
 */
public class WXACodeParams {
    private int id;
    private int width;
    private String page;
    private int colorR;
    private int colorG;
    private int colorB;
    private boolean isHyaline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getColorR() {
        return colorR;
    }

    public void setColorR(int colorR) {
        this.colorR = colorR;
    }

    public int getColorG() {
        return colorG;
    }

    public void setColorG(int colorG) {
        this.colorG = colorG;
    }

    public int getColorB() {
        return colorB;
    }

    public void setColorB(int colorB) {
        this.colorB = colorB;
    }

    public boolean getIsHyaline() {
        return isHyaline;
    }

    public void setIsHyaline(boolean isHyaline) {
        this.isHyaline = isHyaline;
    }
}
