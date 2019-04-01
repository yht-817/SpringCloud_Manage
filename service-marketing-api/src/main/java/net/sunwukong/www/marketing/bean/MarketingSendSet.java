package net.sunwukong.www.marketing.bean;

import java.math.BigDecimal;
import java.util.Date;

public class MarketingSendSet {
    private String id;

    private String sendNo;

    private String sendType;

    private BigDecimal sendAmount;

    private Date sendBeginDate;

    private Date sendEndDate;

    private Date operatorDate;

    private String userNoPlatform;

    private String userNamePlatform;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo == null ? null : sendNo.trim();
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public BigDecimal getSendAmount() {
        return sendAmount;
    }

    public void setSendAmount(BigDecimal sendAmount) {
        this.sendAmount = sendAmount;
    }

    public Date getSendBeginDate() {
        return sendBeginDate;
    }

    public void setSendBeginDate(Date sendBeginDate) {
        this.sendBeginDate = sendBeginDate;
    }

    public Date getSendEndDate() {
        return sendEndDate;
    }

    public void setSendEndDate(Date sendEndDate) {
        this.sendEndDate = sendEndDate;
    }

    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
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