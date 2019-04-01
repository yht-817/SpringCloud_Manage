package net.sunwukong.www.marketing.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ChoiceInfoDetailVo
 * @Author: kangdong
 * @Date: 2018/7/12 上午11:32
 * @Description: 精选信息详情
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */
public class ChoiceInfoDetailVo {

    //===================精选信息===================
    private String id;

    private String userNo;

    private String resourceNo;

    private String resourceName;

    private String choiceType;

    private Integer putNum;

    private Integer payNum;

    private BigDecimal couponAmount;

    private BigDecimal payAmount;

    private BigDecimal costAmount;

    private BigDecimal shareRebateAmount;

    private BigDecimal cutMinAmount;

    private BigDecimal payAmountWukb;

    private String titleDesc;

    private String copywritUrl;

    private String positionNo;

    private String bannerPhoto;

    private String detailPhoto;

    private Integer praiseNum;

    private Date createDate;

    private String executeState;

    private String useRules;

    private String useExplain;

    private Integer maxPayNum;


    //===================商户信息/申请精选用户信息===================
    private String merchantName;

    private String merchantContract;

    private String merchantAddress;

    private String merchantPhone;

    private Date startDate;

    private Date endDate;


    //是否已收藏
    private Boolean collection;

    // 返回分享图片
    private String imgurl;

    // 查看是否存在电话号
    private boolean phoneno;


    public boolean isPhoneno() {
        return phoneno;
    }

    public void setPhoneno(boolean phoneno) {
        this.phoneno = phoneno;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

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

    public String getResourceNo() {
        return resourceNo;
    }

    public void setResourceNo(String resourceNo) {
        this.resourceNo = resourceNo;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(String choiceType) {
        this.choiceType = choiceType;
    }

    public Integer getPutNum() {
        return putNum;
    }

    public void setPutNum(Integer putNum) {
        this.putNum = putNum;
    }

    public Integer getPayNum() {
        return payNum;
    }

    public void setPayNum(Integer payNum) {
        this.payNum = payNum;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public BigDecimal getShareRebateAmount() {
        return shareRebateAmount;
    }

    public void setShareRebateAmount(BigDecimal shareRebateAmount) {
        this.shareRebateAmount = shareRebateAmount;
    }

    public BigDecimal getCutMinAmount() {
        return cutMinAmount;
    }

    public void setCutMinAmount(BigDecimal cutMinAmount) {
        this.cutMinAmount = cutMinAmount;
    }

    public BigDecimal getPayAmountWukb() {
        return payAmountWukb;
    }

    public void setPayAmountWukb(BigDecimal payAmountWukb) {
        this.payAmountWukb = payAmountWukb;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public String getCopywritUrl() {
        return copywritUrl;
    }

    public void setCopywritUrl(String copywritUrl) {
        this.copywritUrl = copywritUrl;
    }

    public String getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(String positionNo) {
        this.positionNo = positionNo;
    }

    public String getBannerPhoto() {
        return bannerPhoto;
    }

    public void setBannerPhoto(String bannerPhoto) {
        this.bannerPhoto = bannerPhoto;
    }

    public String getDetailPhoto() {
        return detailPhoto;
    }

    public void setDetailPhoto(String detailPhoto) {
        this.detailPhoto = detailPhoto;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getExecuteState() {
        return executeState;
    }

    public void setExecuteState(String executeState) {
        this.executeState = executeState;
    }

    public String getUseExplain() {
        return useExplain;
    }

    public void setUseExplain(String useExplain) {
        this.useExplain = useExplain;
    }

    public Integer getMaxPayNum() {
        return maxPayNum;
    }

    public void setMaxPayNum(Integer maxPayNum) {
        this.maxPayNum = maxPayNum;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantContract() {
        return merchantContract;
    }

    public void setMerchantContract(String merchantContract) {
        this.merchantContract = merchantContract;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getCollection() {
        return collection;
    }

    public void setCollection(Boolean collection) {
        this.collection = collection;
    }

    public String getUseRules() {
        return useRules;
    }

    public void setUseRules(String useRules) {
        this.useRules = useRules;
    }
}
