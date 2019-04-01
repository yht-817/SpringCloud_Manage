package net.sunwukong.www.user.bean;

import java.util.Date;

public class UserSubord {
    private String id;

    private String userNo;

    private String subUserNo;

    private Date joinDate;

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

    public String getSubUserNo() {
        return subUserNo;
    }

    public void setSubUserNo(String subUserNo) {
        this.subUserNo = subUserNo == null ? null : subUserNo.trim();
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}