package net.sunwukong.www.user.bean;

import java.util.Date;

public class UserOtherLogin {
    private String id;

    private String userNo;

    private String otherAppType;

    private String otherAppId;

    private Date otherAppDate;

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

    public String getOtherAppType() {
        return otherAppType;
    }

    public void setOtherAppType(String otherAppType) {
        this.otherAppType = otherAppType == null ? null : otherAppType.trim();
    }

    public String getOtherAppId() {
        return otherAppId;
    }

    public void setOtherAppId(String otherAppId) {
        this.otherAppId = otherAppId == null ? null : otherAppId.trim();
    }

    public Date getOtherAppDate() {
        return otherAppDate;
    }

    public void setOtherAppDate(Date otherAppDate) {
        this.otherAppDate = otherAppDate;
    }
}