package net.sunwukong.www.marketing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: DemandDetailVo
 * @Author: kangdong
 * @Date: 2018/6/24 上午10:30
 * @Description: 已经接单的需求详情
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@ApiModel(value = "DemandDetailVo",description = "已经接单的需求详情")
public class DemandDetailVo {

    //==================需求信息==================
    @ApiModelProperty(dataType = "id",name = "id",value = "id编码")
    private String id;

    @ApiModelProperty(dataType = "String",name = "demandUserNo",value = "需求方编码")
    private String demandUserNo;

    @ApiModelProperty(dataType = "String",name = "demandUserHead",value = "需求方头像")
    private String demandUserHead;

    @ApiModelProperty(dataType = "String",name = "demandUserName",value = "需求方名")
    private String demandUserName;

    @ApiModelProperty(dataType = "String",name = "demandUserNikeName",value = "需求方昵称")
    private String demandUserNikeName;

    @ApiModelProperty(dataType = "String",name = "demandNo",value = "需求单号")
    private String demandNo;

    @ApiModelProperty(dataType = "String",name = "categoryOneName",value = "一级分类名")
    private String categoryOneName;

    @ApiModelProperty(dataType = "String",name = "categoryTwoName",value = "二级分类名")
    private String categoryTwoName;

    @ApiModelProperty(dataType = "String",name = "cityName",value = "城市名")
    private String cityName;

    @ApiModelProperty(dataType = "String",name = "specialRemark",value = "特殊说明")
    private String specialRemark;

    @ApiModelProperty(dataType = "Date",name = "submitDate",value = "提交时间")
    private Date submitDate;

    @ApiModelProperty(dataType = "BigDecimal",name = "payNum",value = "支付币数")
    private BigDecimal payNum;

    @ApiModelProperty(dataType = "String",name = "demandState",value = "需求状态")
    private String demandState;


    //================需求服务状态================

    @ApiModelProperty(dataType = "String",name = "serverState",value = "服务状态")
    private String serverState;


    //==================服务机构==================

    @ApiModelProperty(dataType = "String",name = "serverUserNo",value = "服务机构编号")
    private String serverUserNo;

    @ApiModelProperty(dataType = "String",name = "serverUserHead",value = "服务机构头像")
    private String serverUserHead;

    @ApiModelProperty(dataType = "String",name = "serverUserName",value = "服务名")
    private String serverUserName;

    @ApiModelProperty(dataType = "String",name = "serverUserNikeName",value = "服务机构昵称")
    private String serverUserNikeName;

    @ApiModelProperty(dataType = "String",name = "userNoManger",value = "管理者编码")
    private String userNoManger;

    @ApiModelProperty(dataType = "String",name = "address",value = "地址")
    private String address;

    @ApiModelProperty(dataType = "String",name = "phoneNo",value = "联系电话")
    private String phoneNo;


    //==================服务评价==================

    @ApiModelProperty(dataType = "String",name = "complainState",value = "投诉状态")
    private String complainState;

    @ApiModelProperty(dataType = "Integer",name = "presonStar",value = "人员评星")
    private Integer presonStar;

    @ApiModelProperty(dataType = "Integer",name = "timeStar",value = "时效评星")
    private Integer timeStar;

    @ApiModelProperty(dataType = "Integer",name = "qualityStat",value = "质量评星")
    private Integer qualityStat;

    @ApiModelProperty(dataType = "String",name = "evaluateRemark",value = "评语")
    private String evaluateRemark;

    @ApiModelProperty(dataType = "String",name = "evaluateDate",value = "评价时间")
    private Date evaluateDate;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDemandUserNo() {
        return demandUserNo;
    }

    public void setDemandUserNo(String demandUserNo) {
        this.demandUserNo = demandUserNo;
    }

    public String getDemandUserHead() {
        return demandUserHead;
    }

    public void setDemandUserHead(String demandUserHead) {
        this.demandUserHead = demandUserHead;
    }

    public String getDemandUserName() {
        return demandUserName;
    }

    public void setDemandUserName(String demandUserName) {
        this.demandUserName = demandUserName;
    }

    public String getDemandUserNikeName() {
        return demandUserNikeName;
    }

    public void setDemandUserNikeName(String demandUserNikeName) {
        this.demandUserNikeName = demandUserNikeName;
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

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
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

    public String getServerUserNo() {
        return serverUserNo;
    }

    public void setServerUserNo(String serverUserNo) {
        this.serverUserNo = serverUserNo;
    }

    public String getServerUserHead() {
        return serverUserHead;
    }

    public void setServerUserHead(String serverUserHead) {
        this.serverUserHead = serverUserHead;
    }

    public String getServerUserName() {
        return serverUserName;
    }

    public void setServerUserName(String serverUserName) {
        this.serverUserName = serverUserName;
    }

    public String getServerUserNikeName() {
        return serverUserNikeName;
    }

    public void setServerUserNikeName(String serverUserNikeName) {
        this.serverUserNikeName = serverUserNikeName;
    }

    public String getUserNoManger() {
        return userNoManger;
    }

    public void setUserNoManger(String userNoManger) {
        this.userNoManger = userNoManger;
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

    public String getComplainState() {
        return complainState;
    }

    public void setComplainState(String complainState) {
        this.complainState = complainState;
    }

    public Integer getPresonStar() {
        return presonStar;
    }

    public void setPresonStar(Integer presonStar) {
        this.presonStar = presonStar;
    }

    public Integer getTimeStar() {
        return timeStar;
    }

    public void setTimeStar(Integer timeStar) {
        this.timeStar = timeStar;
    }

    public Integer getQualityStat() {
        return qualityStat;
    }

    public void setQualityStat(Integer qualityStat) {
        this.qualityStat = qualityStat;
    }

    public String getEvaluateRemark() {
        return evaluateRemark;
    }

    public void setEvaluateRemark(String evaluateRemark) {
        this.evaluateRemark = evaluateRemark;
    }

    public Date getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(Date evaluateDate) {
        this.evaluateDate = evaluateDate;
    }
}
