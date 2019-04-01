package net.sunwukong.www.marketing.bean;

import java.util.Date;

public class UserInfo {
    private String id;

    private String userNo;

    private String userHead;

    private String userName;

    private String nikeName;

    private String sexNo;

    private String phoneNo;

    private String mailboxNo;

    private String passWord;

    private Date regionDate;

    private String userType;

    private Date lastLoginDate;

    private String userState;

    private Integer evaluateDividing;

    private String userGrade;

    private Date userGradeChangeDate;

    private String otherAppId;

    private String otherAppName;

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

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead == null ? null : userHead.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName == null ? null : nikeName.trim();
    }

    public String getSexNo() {
        return sexNo;
    }

    public void setSexNo(String sexNo) {
        this.sexNo = sexNo == null ? null : sexNo.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getMailboxNo() {
        return mailboxNo;
    }

    public void setMailboxNo(String mailboxNo) {
        this.mailboxNo = mailboxNo == null ? null : mailboxNo.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public Date getRegionDate() {
        return regionDate;
    }

    public void setRegionDate(Date regionDate) {
        this.regionDate = regionDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState == null ? null : userState.trim();
    }

    public Integer getEvaluateDividing() {
        return evaluateDividing;
    }

    public void setEvaluateDividing(Integer evaluateDividing) {
        this.evaluateDividing = evaluateDividing;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade == null ? null : userGrade.trim();
    }

    public Date getUserGradeChangeDate() {
        return userGradeChangeDate;
    }

    public void setUserGradeChangeDate(Date userGradeChangeDate) {
        this.userGradeChangeDate = userGradeChangeDate;
    }

    public String getOtherAppId() {
        return otherAppId;
    }

    public void setOtherAppId(String otherAppId) {
        this.otherAppId = otherAppId == null ? null : otherAppId.trim();
    }

    public String getOtherAppName() {
        return otherAppName;
    }

    public void setOtherAppName(String otherAppName) {
        this.otherAppName = otherAppName == null ? null : otherAppName.trim();
    }
}