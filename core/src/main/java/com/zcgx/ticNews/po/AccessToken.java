package com.zcgx.ticNews.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_access_token")
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String accessToken;

    private String expiresin;

    private Date createDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public String getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(String expiresin) {
        this.expiresin = expiresin == null ? null : expiresin.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", expiresin='" + expiresin + '\'' +
                ", createdate=" + createDate +
                '}';
    }
}

