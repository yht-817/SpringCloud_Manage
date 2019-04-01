package net.sunwukong.www.marketing.bean;

import java.math.BigDecimal;

public class PlatformAccount {
    private String id;

    private BigDecimal accountAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }
}