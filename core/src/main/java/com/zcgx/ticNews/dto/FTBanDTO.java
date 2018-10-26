package com.zcgx.ticNews.dto;

import com.zcgx.ticNews.po.PFList;

import java.util.List;

public class FTBanDTO {
    private String processid;
    private String enterpriseName;
    private String isOff;
    private String productName;
    private String privinceConfirm;
    private List<PFList> pfLists;

    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getIsOff() {
        return isOff;
    }

    public void setIsOff(String isOff) {
        this.isOff = isOff;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrivinceConfirm() {
        return privinceConfirm;
    }

    public void setPrivinceConfirm(String privinceConfirm) {
        this.privinceConfirm = privinceConfirm;
    }

    public List<PFList> getPfLists() {
        return pfLists;
    }

    public void setPfLists(List<PFList> pfLists) {
        this.pfLists = pfLists;
    }

    @Override
    public String toString() {
        return "FTBanDTO{" +
                "processid='" + processid + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", isOff='" + isOff + '\'' +
                ", productName='" + productName + '\'' +
                ", privinceConfirm='" + privinceConfirm + '\'' +
                ", pfLists=" + pfLists +
                '}';
    }
}
