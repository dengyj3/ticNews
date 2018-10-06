package com.zcgx.ticNews.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_web_log")
public class WebLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String clientIp;
    private String url;
    private String type;
    private String userName;
    private String classMethod;
    private String args;
    private Date requestTime = new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public String toString() {
        return "WebLog{" +
                "id=" + id +
                ", clientIp='" + clientIp + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", userName='" + userName + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", args='" + args + '\'' +
                ", requestTime=" + requestTime +
                '}';
    }
}
