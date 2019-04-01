package net.sunwukong.www.marketing.bean;

import java.math.BigDecimal;

public class ChoiceUserPayDetail {
    private String id;

    private String userNo;

    private String payNo;

    private String payModeNo;

    private BigDecimal payAmount;

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

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    public String getPayModeNo() {
        return payModeNo;
    }

    public void setPayModeNo(String payModeNo) {
        this.payModeNo = payModeNo == null ? null : payModeNo.trim();
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}