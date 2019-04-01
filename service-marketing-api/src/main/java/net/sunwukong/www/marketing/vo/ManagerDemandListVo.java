package net.sunwukong.www.marketing.vo;/**
 * Created by tungkang on 2018/6/21.
 */

import java.util.Date;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ManagerDemandListVo
 * @Author: kangdong
 * @Date: 2018/6/21 下午9:02
 * @Description: 城市运营官管理需求服务信息列表(被投诉、未开始)
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class ManagerDemandListVo {

    //id标识
    private String id;
    //用户编码
    private String userNo;
    //用户名称或机构名称
    private String userName;
    //用户头像
    private String userHead;
    //用户类型，10020001普通用户，10020002城市管理者，10020003猴王服务机构
    private String userType;
    //用户等级
    private String userGrade;
    //用户星级(评分)
    private String userStar;
    //订单号
    private String demandNo;
    //特别说明
    private String specialRemark;
    //状态编码
    private String stateCode;
    //状态名称
    private String stateName;
    //完成订单数
    private String finishCount;
    //时间
    private Date datetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserStar() {
        return userStar;
    }

    public void setUserStar(String userStar) {
        this.userStar = userStar;
    }

    public String getDemandNo() {
        return demandNo;
    }

    public void setDemandNo(String demandNo) {
        this.demandNo = demandNo;
    }

    public String getSpecialRemark() {
        return specialRemark;
    }

    public void setSpecialRemark(String specialRemark) {
        this.specialRemark = specialRemark;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(String finishCount) {
        this.finishCount = finishCount;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
