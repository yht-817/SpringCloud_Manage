package net.sunwukong.www.user.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "UserInfo",description = "用户实体")
public class UserInfo {
    @ApiModelProperty(dataType = "String",name = "id",value = "用户ID")
    private String id;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "userHead",value = "用户头像地址")
    private String userHead;

    @ApiModelProperty(dataType = "String",name = "userName",value = "用户名")
    private String userName;

    @ApiModelProperty(dataType = "String",name = "nikeName",value = "用户昵称")
    private String nikeName;

    @ApiModelProperty(dataType = "String",name = "sexNo",value = "性别")
    private String sexNo;

    @ApiModelProperty(dataType = "String",name = "phoneNo",value = "电话号码")
    private String phoneNo;

    @ApiModelProperty(dataType = "String",name = "mailboxNo",value = "邮箱")
    private String mailboxNo;

    @ApiModelProperty(dataType = "String",name = "passWord",value = "密码")
    @NotNull(message = "密码不能为空")
    private String passWord;

    @ApiModelProperty(dataType = "Date",name = "regionDate",value = "注册时间")
    private Date regionDate;

    @ApiModelProperty(dataType = "String",name = "userType",value = "用户类型")
    private String userType;

    @ApiModelProperty(dataType = "Date",name = "lastLoginDate",value = "最后登录时间")
    private Date lastLoginDate;

    @ApiModelProperty(dataType = "String",name = "userState",value = "用户状态")
    private String userState;

    @ApiModelProperty(dataType = "Integer",name = "evaluateDividing",value = "累计评价分值")
    private Integer evaluateDividing;

    @ApiModelProperty(dataType = "String",name = "userGrade",value = "用户等级")
    private String userGrade;

    @ApiModelProperty(dataType = "Date",name = "userGradeChangeDate",value = "用户等级变更日期")
    private Date userGradeChangeDate;

    @ApiModelProperty(dataType = "String",name = "otherAppId",value = "第三方ID号")
    private String otherAppId;

    @ApiModelProperty(dataType = "String",name = "otherAppName",value = "第三方应用名")
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", userNo='" + userNo + '\'' +
                ", userHead='" + userHead + '\'' +
                ", userName='" + userName + '\'' +
                ", nikeName='" + nikeName + '\'' +
                ", sexNo='" + sexNo + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", mailboxNo='" + mailboxNo + '\'' +
                ", passWord='" + passWord + '\'' +
                ", regionDate=" + regionDate +
                ", userType='" + userType + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", userState='" + userState + '\'' +
                ", evaluateDividing=" + evaluateDividing +
                ", userGrade='" + userGrade + '\'' +
                ", userGradeChangeDate=" + userGradeChangeDate +
                ", otherAppId='" + otherAppId + '\'' +
                ", otherAppName='" + otherAppName + '\'' +
                '}';
    }
}