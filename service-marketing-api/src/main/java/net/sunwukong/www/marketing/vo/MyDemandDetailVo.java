package net.sunwukong.www.marketing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: MyDemandDetailVo
 * @Author: kangdong
 * @Date: 2018/6/27 下午4:23
 * @Description: 我的需求或服务详情
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@ApiModel(value = "MyDemandDetailVo",description = "我的需求或服务详情")
public class MyDemandDetailVo {

    //==============需求信息==============
    @ApiModelProperty(dataType = "id",name = "id",value = "id编码")
    private String id;

    @ApiModelProperty(dataType = "String",name = "demandNo",value = "需求单号")
    private String demandNo;

    @ApiModelProperty(dataType = "String",name = "categoryOneName",value = "一级分类名")
    private String categoryOneName;

    @ApiModelProperty(dataType = "String",name = "categoryTwoName",value = "二级分类名")
    private String categoryTwoName;

    //private String cityNo;
    @ApiModelProperty(dataType = "String",name = "cityName",value = "城市名")
    private String cityName;

    @ApiModelProperty(dataType = "String",name = "specialRemark",value = "特殊说明")
    private String specialRemark;

    @ApiModelProperty(dataType = "Date",name = "submitDate",value = "提交时间")
    private String submitDate;

    @ApiModelProperty(dataType = "BigDecimal",name = "payNum",value = "支付币数")
    private BigDecimal payNum;

    @ApiModelProperty(dataType = "String",name = "demandState",value = "需求状态")
    private String demandState;


    //==============需求服务信息==============
    @ApiModelProperty(dataType = "String",name = "serverState",value = "服务状态")
    private String serverState;

    @ApiModelProperty(dataType = "Date",name = "serverDate",value = "服务时间")
    private String serverDate;


    //==============需求方或服务方信息==============
    @ApiModelProperty(dataType = "String",name = "userId",value = "用户id")
    private String userId;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "userHead",value = "头像")
    private String userHead;

    @ApiModelProperty(dataType = "String",name = "userName",value = "名称")
    private String userName;

    @ApiModelProperty(dataType = "String",name = "userNikeName",value = "昵称")
    private String userNikeName;

    @ApiModelProperty(dataType = "String",name = "address",value = "需求方地址")
    private String address;

    @ApiModelProperty(dataType = "String",name = "phoneNo",value = "需求方联系电话")
    private String phoneNo;

    private Boolean showButton;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDemandNo() {
        return demandNo;
    }

    public void setDemandNo(String demandNo) {
        this.demandNo = demandNo;
    }

    public String getCategoryOneName() {
        return categoryOneName;
    }

    public void setCategoryOneName(String categoryOneName) {
        this.categoryOneName = categoryOneName;
    }

    public String getCategoryTwoName() {
        return categoryTwoName;
    }

    public void setCategoryTwoName(String categoryTwoName) {
        this.categoryTwoName = categoryTwoName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSpecialRemark() {
        return specialRemark;
    }

    public void setSpecialRemark(String specialRemark) {
        this.specialRemark = specialRemark;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public BigDecimal getPayNum() {
        return payNum;
    }

    public void setPayNum(BigDecimal payNum) {
        this.payNum = payNum;
    }

    public String getDemandState() {
        return demandState;
    }

    public void setDemandState(String demandState) {
        this.demandState = demandState;
    }

    public String getServerState() {
        return serverState;
    }

    public void setServerState(String serverState) {
        this.serverState = serverState;
    }

    public String getServerDate() {
        return serverDate;
    }

    public void setServerDate(String serverDate) {
        this.serverDate = serverDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNikeName() {
        return userNikeName;
    }

    public void setUserNikeName(String userNikeName) {
        this.userNikeName = userNikeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Boolean getShowButton() {
        return showButton;
    }

    public void setShowButton(Boolean showButton) {
        this.showButton = showButton;
    }
}
