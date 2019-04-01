package net.sunwukong.www.marketing.bean;

import java.util.Date;

public class InformationEvaluateParise {
    private String id;

    private String informationNo;

    private String evaluateNo;

    private String userNo;

    private Date pariseDate;

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

    public String getEvaluateNo() {
        return evaluateNo;
    }

    public void setEvaluateNo(String evaluateNo) {
        this.evaluateNo = evaluateNo == null ? null : evaluateNo.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public Date getPariseDate() {
        return pariseDate;
    }

    public void setPariseDate(Date pariseDate) {
        this.pariseDate = pariseDate;
    }
}