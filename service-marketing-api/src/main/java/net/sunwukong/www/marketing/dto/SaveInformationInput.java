package net.sunwukong.www.marketing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "SaveInformationInput",description = "发布资讯入参")
public class SaveInformationInput {

    @ApiModelProperty(dataType = "String",name = "informationType",value = "资讯类型")
    private String informationType;

    @ApiModelProperty(dataType = "String",name = "informationClass",value = "资讯分类")
    private String informationClass;

    @ApiModelProperty(dataType = "String",name = "informationCityNo",value = "城市编码")
    private String informationCityNo;

    @ApiModelProperty(dataType = "String",name = "cityName",value = "城市名称")
    private String cityName;

    @ApiModelProperty(dataType = "String",name = "informationMode",value = "资讯类型")
    private String informationMode;

    @ApiModelProperty(dataType = "String",name = "couponNo",value = "优惠券编码")
    private String couponNo;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "发布人编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "informationName",value = "资讯标题")
    private String informationName;

    @ApiModelProperty(dataType = "String",name = "informationPhoto",value = "资讯图片")
    private String informationPhoto;

    @ApiModelProperty(dataType = "String",name = "photoSizeType",value = "图片尺寸类型")
    private String photoSizeType;

    @ApiModelProperty(dataType = "String",name = "informationContent",value = "资讯内容")
    private String informationContent;

    public String getInformationType() {
        return informationType;
    }

    public void setInformationType(String informationType) {
        this.informationType = informationType == null ? null : informationType.trim();
    }

    public String getInformationClass() {
        return informationClass;
    }

    public void setInformationClass(String informationClass) {
        this.informationClass = informationClass == null ? null : informationClass.trim();
    }

    public String getInformationCityNo() {
        return informationCityNo;
    }

    public void setInformationCityNo(String informationCityNo) {
        this.informationCityNo = informationCityNo == null ? null : informationCityNo.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getInformationMode() {
        return informationMode;
    }

    public void setInformationMode(String informationMode) {
        this.informationMode = informationMode == null ? null : informationMode.trim();
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo == null ? null : couponNo.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName == null ? null : informationName.trim();
    }

    public String getInformationPhoto() {
        return informationPhoto;
    }

    public void setInformationPhoto(String informationPhoto) {
        this.informationPhoto = informationPhoto == null ? null : informationPhoto.trim();
    }

    public String getPhotoSizeType() {
        return photoSizeType;
    }

    public void setPhotoSizeType(String photoSizeType) {
        this.photoSizeType = photoSizeType == null ? null : photoSizeType.trim();
    }

    public String getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent;
    }
}