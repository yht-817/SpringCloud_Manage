package net.sunwukong.www.marketing.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "DemandInfo",description = "需求发布单")
public class DemandInfo {
    @ApiModelProperty(dataType = "String", name = "id", value = "ID")
    private String id;

    @ApiModelProperty(dataType = "String", name = "userNo", value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String", name = "demandNo", value = "需求编码")
    private String demandNo;

    @ApiModelProperty(dataType = "String", name = "categoryOneNo", value = "一级类目编码")
    @NotNull(message = "一级类目编码不能为空")
    private String categoryOneNo;

    @ApiModelProperty(dataType = "String", name = "categoryOneName", value = "一级类目名称")
    @NotNull(message = "一级类目名称不能为空")
    private String categoryOneName;

    @ApiModelProperty(dataType = "String", name = "categoryTwoNo", value = "二级类目编码")
    @NotNull(message = "二级类目编码不能为空")
    private String categoryTwoNo;

    @ApiModelProperty(dataType = "String", name = "categoryTwoName", value = "二级类目名称")
    @NotNull(message = "二级类目名称不能为空")
    private String categoryTwoName;

    @ApiModelProperty(dataType = "String", name = "cityNo", value = "所在城市编码")
    @NotNull(message = "所在城市编码不能为空")
    private String cityNo;

    @ApiModelProperty(dataType = "String", name = "cityName", value = "所在城市名称")
    @NotNull(message = "所在城市名称不能为空")
    private String cityName;

    @ApiModelProperty(dataType = "String", name = "specialRemark", value = "详细说明")
    @NotNull(message = "详细说明不能为空")
    private String specialRemark;

    @ApiModelProperty(dataType = "String", name = "submitDate", value = "提交时间")
    private Date submitDate;

    @ApiModelProperty(dataType = "String", name = "scopeOneNo", value = "一级服务范围编码")
    @NotNull(message = "一级服务范围编码不能为空")
    private String scopeOneNo;

    @ApiModelProperty(dataType = "String", name = "scopeOneName", value = "一级服务范围名称")
    @NotNull(message = "一级服务范围名称不能为空")
    private String scopeOneName;

    @ApiModelProperty(dataType = "String", name = "scopeTwoNo", value = "二级服务范围编码")
    @NotNull(message = "二级服务范围编码不能为空")
    private String scopeTwoNo;

    @ApiModelProperty(dataType = "String", name = "scopeTwoName", value = "二级服务范围名称")
    @NotNull(message = "二级服务范围名称不能为空")
    private String scopeTwoName;

    @ApiModelProperty(dataType = "String", name = "payNum", value = "支付悟空币")
    @NotNull(message = "支付悟空币不能为空")
    private BigDecimal payNum;

    @ApiModelProperty(dataType = "String", name = "demandState", value = "需求状态")
    private String demandState;

    @ApiModelProperty(dataType = "String", name = "serverState", value = "服务状态")
    private String serverState;

    @ApiModelProperty(dataType = "String", name = "robUser", value = "抢单用户")
    private String robUser;

    @ApiModelProperty(dataType = "String", name = "serviceUser", value = "服务用户")
    private String serviceUser;

    @ApiModelProperty(dataType = "String", name = "changeDate", value = "变更时间")
    private Date changeDate;

    @ApiModelProperty(dataType = "String", name = "phoneNo", value = "联系电话")
    @NotNull(message = "联系电话不能为空")
    private String phoneNo;

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

    public String getDemandNo() {
        return demandNo;
    }

    public void setDemandNo(String demandNo) {
        this.demandNo = demandNo == null ? null : demandNo.trim();
    }

    public String getCategoryOneNo() {
        return categoryOneNo;
    }

    public void setCategoryOneNo(String categoryOneNo) {
        this.categoryOneNo = categoryOneNo == null ? null : categoryOneNo.trim();
    }

    public String getCategoryOneName() {
        return categoryOneName;
    }

    public void setCategoryOneName(String categoryOneName) {
        this.categoryOneName = categoryOneName == null ? null : categoryOneName.trim();
    }

    public String getCategoryTwoNo() {
        return categoryTwoNo;
    }

    public void setCategoryTwoNo(String categoryTwoNo) {
        this.categoryTwoNo = categoryTwoNo == null ? null : categoryTwoNo.trim();
    }

    public String getCategoryTwoName() {
        return categoryTwoName;
    }

    public void setCategoryTwoName(String categoryTwoName) {
        this.categoryTwoName = categoryTwoName == null ? null : categoryTwoName.trim();
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo == null ? null : cityNo.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getSpecialRemark() {
        return specialRemark;
    }

    public void setSpecialRemark(String specialRemark) {
        this.specialRemark = specialRemark == null ? null : specialRemark.trim();
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getScopeOneNo() {
        return scopeOneNo;
    }

    public void setScopeOneNo(String scopeOneNo) {
        this.scopeOneNo = scopeOneNo == null ? null : scopeOneNo.trim();
    }

    public String getScopeOneName() {
        return scopeOneName;
    }

    public void setScopeOneName(String scopeOneName) {
        this.scopeOneName = scopeOneName == null ? null : scopeOneName.trim();
    }

    public String getScopeTwoNo() {
        return scopeTwoNo;
    }

    public void setScopeTwoNo(String scopeTwoNo) {
        this.scopeTwoNo = scopeTwoNo == null ? null : scopeTwoNo.trim();
    }

    public String getScopeTwoName() {
        return scopeTwoName;
    }

    public void setScopeTwoName(String scopeTwoName) {
        this.scopeTwoName = scopeTwoName == null ? null : scopeTwoName.trim();
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
        this.demandState = demandState == null ? null : demandState.trim();
    }

    public String getServerState() {
        return serverState;
    }

    public void setServerState(String serverState) {
        this.serverState = serverState == null ? null : serverState.trim();
    }

    public String getRobUser() {
        return robUser;
    }

    public void setRobUser(String robUser) {
        this.robUser = robUser == null ? null : robUser.trim();
    }

    public String getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(String serviceUser) {
        this.serviceUser = serviceUser == null ? null : serviceUser.trim();
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }
}