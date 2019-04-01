package net.sunwukong.www.marketing.bean;

import java.math.BigDecimal;

public class UserAccount {
    private String id;

    private String userNo;

    private BigDecimal accountAmount;

    private BigDecimal forzenAccountAmount;

    private BigDecimal cashAccountAmount;

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

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public BigDecimal getForzenAccountAmount() {
        return forzenAccountAmount;
    }

    public void setForzenAccountAmount(BigDecimal forzenAccountAmount) {
        this.forzenAccountAmount = forzenAccountAmount;
    }

    public BigDecimal getCashAccountAmount() {
        return cashAccountAmount;
    }

    public void setCashAccountAmount(BigDecimal cashAccountAmount) {
        this.cashAccountAmount = cashAccountAmount;
    }
}