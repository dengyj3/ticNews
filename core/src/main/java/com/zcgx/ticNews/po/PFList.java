package com.zcgx.ticNews.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_pf_list")
public class PFList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String processid;
    private String cas;
    private String cname;
    private String pfname;
    private int ylid;
    private Date createTime = new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPfname() {
        return pfname;
    }

    public void setPfname(String pfname) {
        this.pfname = pfname;
    }

    public int getYlid() {
        return ylid;
    }

    public void setYlid(int ylid) {
        this.ylid = ylid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PFList{" +
                "id=" + id +
                ", processid='" + processid + '\'' +
                ", cas='" + cas + '\'' +
                ", cname='" + cname + '\'' +
                ", pfname='" + pfname + '\'' +
                ", ylid=" + ylid +
                ", createTime=" + createTime +
                '}';
    }
}
