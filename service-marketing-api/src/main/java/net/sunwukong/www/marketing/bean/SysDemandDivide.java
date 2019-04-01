package net.sunwukong.www.marketing.bean;

import java.math.BigDecimal;
import java.util.Date;

public class SysDemandDivide {
    private String id;

    private Date createDate;

    private BigDecimal demandDivide;

    private BigDecimal receiptDivide;

    private BigDecimal serviceDivide;

    private BigDecimal mangerDivide;

    private BigDecimal platformDivide;

    private String divideExecstate;

    private String userNoPlatform;

    private String userNamePlatform;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getDemandDivide() {
        return demandDivide;
    }

    public void setDemandDivide(BigDecimal demandDivide) {
        this.demandDivide = demandDivide;
    }

    public BigDecimal getReceiptDivide() {
        return receiptDivide;
    }

    public void setReceiptDivide(BigDecimal receiptDivide) {
        this.receiptDivide = receiptDivide;
    }

    public BigDecimal getServiceDivide() {
        return serviceDivide;
    }

    public void setServiceDivide(BigDecimal serviceDivide) {
        this.serviceDivide = serviceDivide;
    }

    public BigDecimal getMangerDivide() {
        return mangerDivide;
    }

    public void setMangerDivide(BigDecimal mangerDivide) {
        this.mangerDivide = mangerDivide;
    }

    public BigDecimal getPlatformDivide() {
        return platformDivide;
    }

    public void setPlatformDivide(BigDecimal platformDivide) {
        this.platformDivide = platformDivide;
    }

    public String getDivideExecstate() {
        return divideExecstate;
    }

    public void setDivideExecstate(String divideExecstate) {
        this.divideExecstate = divideExecstate == null ? null : divideExecstate.trim();
    }

    public String getUserNoPlatform() {
        return userNoPlatform;
    }

    public void setUserNoPlatform(String userNoPlatform) {
        this.userNoPlatform = userNoPlatform == null ? null : userNoPlatform.trim();
    }

    public String getUserNamePlatform() {
        return userNamePlatform;
    }

    public void setUserNamePlatform(String userNamePlatform) {
        this.userNamePlatform = userNamePlatform == null ? null : userNamePlatform.trim();
    }
}