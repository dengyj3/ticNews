package com.zcgx.ticNews.po;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int subscribe;
    private String openid;
    private String unionid;
    private int subscribeNewspaper = 2;//默认未订阅
    private int userSource = 1;//默认来源公众号
    private String mpOpenid;
    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public int getSubscribeNewspaper() {
        return subscribeNewspaper;
    }

    public void setSubscribeNewspaper(int subscribeNewspaper) {
        this.subscribeNewspaper = subscribeNewspaper;
    }

    public int getUserSource() {
        return userSource;
    }

    public void setUserSource(int userSource) {
        this.userSource = userSource;
    }

    public String getMpOpenid() {
        return mpOpenid;
    }

    public void setMpOpenid(String mpOpenid) {
        this.mpOpenid = mpOpenid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", subscribe=" + subscribe +
                ", openid='" + openid + '\'' +
                ", unionid='" + unionid + '\'' +
                ", subscribeNewspaper=" + subscribeNewspaper +
                ", userSource=" + userSource +
                ", mpOpenid='" + mpOpenid + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
