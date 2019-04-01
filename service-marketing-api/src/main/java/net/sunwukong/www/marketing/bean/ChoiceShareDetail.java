package net.sunwukong.www.marketing.bean;

import java.math.BigDecimal;

public class ChoiceShareDetail {
    private String id;

    private String userNo;

    private String shareNo;

    private String payNo;

    private BigDecimal shareRebateAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getShareNo() {
        return shareNo;
    }

    public void setShareNo(String shareNo) {
        this.shareNo = shareNo == null ? null : shareNo.trim();
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    public BigDecimal getShareRebateAmount() {
        return shareRebateAmount;
    }

    public void setShareRebateAmount(BigDecimal shareRebateAmount) {
        this.shareRebateAmount = shareRebateAmount;
    }
}