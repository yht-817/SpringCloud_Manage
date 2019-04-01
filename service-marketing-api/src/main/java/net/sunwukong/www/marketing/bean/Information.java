package net.sunwukong.www.marketing.bean;

import java.util.Date;

public class Information {
    private String id;

    private String informationNo;

    private String informationType;

    private String informationClass;

    private String informationCityNo;

    private String cityName;

    private String informationMode;

    private String couponNo;

    private String userNo;

    private String informationName;

    private String informationPhoto;

    private String photoSizeType;

    private String informationUrl;

    private Date releaseDate;

    private String setTopState;

    private String hotState;

    private Integer evaluateNum;

    private Integer clickNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInformationNo() {
        return informationNo;
    }

    public void setInformationNo(String informationNo) {
        this.informationNo = informationNo == null ? null : informationNo.trim();
    }

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

    public String getInformationUrl() {
        return informationUrl;
    }

    public void setInformationUrl(String informationUrl) {
        this.informationUrl = informationUrl == null ? null : informationUrl.trim();
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSetTopState() {
        return setTopState;
    }

    public void setSetTopState(String setTopState) {
        this.setTopState = setTopState == null ? null : setTopState.trim();
    }

    public String getHotState() {
        return hotState;
    }

    public void setHotState(String hotState) {
        this.hotState = hotState == null ? null : hotState.trim();
    }

    public Integer getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(Integer evaluateNum) {
        this.evaluateNum = evaluateNum;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }
}