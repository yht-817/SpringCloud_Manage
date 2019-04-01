package net.sunwukong.www.chat.bean;

import java.util.Date;

public class FriendUser {
    private String id;

    private String userNo;

    private String friendNo;

    private String friendType;

    private Date lastContactDate;

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

    public String getFriendNo() {
        return friendNo;
    }

    public void setFriendNo(String friendNo) {
        this.friendNo = friendNo == null ? null : friendNo.trim();
    }

    public String getFriendType() {
        return friendType;
    }

    public void setFriendType(String friendType) {
        this.friendType = friendType == null ? null : friendType.trim();
    }

    public Date getLastContactDate() {
        return lastContactDate;
    }

    public void setLastContactDate(Date lastContactDate) {
        this.lastContactDate = lastContactDate;
    }
}