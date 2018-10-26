package com.zcgx.ticNews.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_ftban")
public class FTBanList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String applySn;
    private String applySnTT;
    private String applyEnterAddress;
    private String enterpriseName;
    private String enterpriseNameTT;
    private String isOff;
    private String newProcessid;
    private String offDate;
    private String orgName;
    private String processid;
    private String productName;
    private String productNameTT;
    private String provinceConfirm;
    private Date createTime = new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApplySn() {
        return applySn;
    }

    public void setApplySn(String applySn) {
        this.applySn = applySn;
    }

    public String getApplySnTT() {
        return applySnTT;
    }

    public void setApplySnTT(String applySnTT) {
        this.applySnTT = applySnTT;
    }


    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseNameTT() {
        return enterpriseNameTT;
    }

    public void setEnterpriseNameTT(String enterpriseNameTT) {
        this.enterpriseNameTT = enterpriseNameTT;
    }


    public String getNewProcessid() {
        return newProcessid;
    }

    public void setNewProcessid(String newProcessid) {
        this.newProcessid = newProcessid;
    }


    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNameTT() {
        return productNameTT;
    }

    public void setProductNameTT(String productNameTT) {
        this.productNameTT = productNameTT;
    }

    public String getProvinceConfirm() {
        return provinceConfirm;
    }

    public void setProvinceConfirm(String provinceConfirm) {
        this.provinceConfirm = provinceConfirm;
    }

    public String getIsOff() {
        return isOff;
    }

    public void setIsOff(String isOff) {
        this.isOff = isOff;
    }

    public String getOffDate() {
        return offDate;
    }

    public void setOffDate(String offDate) {
        this.offDate = offDate;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getApplyEnterAddress() {
        return applyEnterAddress;
    }

    public void setApplyEnterAddress(String applyEnterAddress) {
        this.applyEnterAddress = applyEnterAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
