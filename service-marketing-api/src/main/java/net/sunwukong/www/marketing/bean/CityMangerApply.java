package net.sunwukong.www.marketing.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "CityMangerApply",description = "城市运营官申请")
public class CityMangerApply {

    @ApiModelProperty(dataType = "String",name = "id",value = "ID")
    private String id;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @NotNull(message = "用户编码不能为空")
    private String userNo;

    @ApiModelProperty(dataType = "Integer",name = "age",value = "年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @ApiModelProperty(dataType = "String",name = "selfEvaluation",value = "个人介绍")
    private String selfEvaluation;

    @ApiModelProperty(dataType = "String",name = "cityNo",value = "城市编码")
    @NotNull(message = "城市编码不能为空")
    private String cityNo;

    @ApiModelProperty(dataType = "String",name = "cityName",value = "城市名称")
    @NotNull(message = "城市名称不能为空")
    private String cityName;

    @ApiModelProperty(dataType = "String",name = "applyDate",value = "申请时间")
    private Date applyDate;

    @ApiModelProperty(dataType = "String",name = "contractAddress",value = "联系地址")
    @NotNull(message = "联系地址不能为空")
    private String contractAddress;

    @ApiModelProperty(dataType = "String",name = "longitude",value = "经度")
    private String longitude;

    @ApiModelProperty(dataType = "String",name = "latitude",value = "纬度")
    private String latitude;

    @ApiModelProperty(dataType = "String",name = "auditState",value = "审核状态")
    private String auditState;

    @ApiModelProperty(dataType = "String",name = "auditDate",value = "审核时间")
    private Date auditDate;

    @ApiModelProperty(dataType = "String",name = "userNoPlatform",value = "平台编码")
    private String userNoPlatform;

    @ApiModelProperty(dataType = "String",name = "userNamePlatform",value = "平台名称")
    private String userNamePlatform;

    @ApiModelProperty(dataType = "String",name = "sexNo",value = "性别")
    @NotNull(message = "性别不能为空")
    private String sexNo;

    @ApiModelProperty(dataType = "String",name = "phoneNo",value = "电话号码")
    @NotNull(message = "电话号码不能为空")
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSelfEvaluation() {
        return selfEvaluation;
    }

    public void setSelfEvaluation(String selfEvaluation) {
        this.selfEvaluation = selfEvaluation == null ? null : selfEvaluation.trim();
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

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress == null ? null : contractAddress.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState == null ? null : auditState.trim();
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getUserNoPlatform() {
        return userNoPlatform;
    }

    public void setUserNoPlatform(String userNoPlatform) {
        this.userNoPlatform = userNoPlatform == null ? null : userNoPlatform.trim();
    }

    public String getUserNamePlatform() {
        return userNamePlatform;
    }

    public void setUserNamePlatform(String userNamePlatform) {
        this.userNamePlatform = userNamePlatform == null ? null : userNamePlatform.trim();
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
}