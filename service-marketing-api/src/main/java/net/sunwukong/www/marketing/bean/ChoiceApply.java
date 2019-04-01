package net.sunwukong.www.marketing.bean;

import java.util.Date;

public class ChoiceApply {
    private String id;

    private String userNo;

    private String applyNo;

    private String merchantContract;

    private String merchantPhone;

    private Date applyDate;

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

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    public String getMerchantContract() {
        return merchantContract;
    }

    public void setMerchantContract(String merchantContract) {
        this.merchantContract = merchantContract == null ? null : merchantContract.trim();
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone == null ? null : merchantPhone.trim();
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
}