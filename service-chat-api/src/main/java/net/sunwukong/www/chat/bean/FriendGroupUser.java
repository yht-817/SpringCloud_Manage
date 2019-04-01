package net.sunwukong.www.chat.bean;

import java.util.Date;

public class FriendGroupUser {
    private String id;

    private String groupNo;

    private String userNo;

    private String friendUserNo;

    private String inviteNo;

    private String groupUserState;

    private Date joinDate;

    private Date changeDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo == null ? null : groupNo.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getFriendUserNo() {
        return friendUserNo;
    }

    public void setFriendUserNo(String friendUserNo) {
        this.friendUserNo = friendUserNo == null ? null : friendUserNo.trim();
    }

    public String getInviteNo() {
        return inviteNo;
    }

    public void setInviteNo(String inviteNo) {
        this.inviteNo = inviteNo == null ? null : inviteNo.trim();
    }

    public String getGroupUserState() {
        return groupUserState;
    }

    public void setGroupUserState(String groupUserState) {
        this.groupUserState = groupUserState == null ? null : groupUserState.trim();
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
}