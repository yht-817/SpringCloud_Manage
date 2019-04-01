package net.sunwukong.www.marketing.bean;

import java.util.Date;

public class DemandEvaluate {
    private String id;

    private String demandNo;

    private String userNo;

    private String complainState;

    private Integer presonStar;

    private Integer timeStar;

    private Integer qualityStat;

    private String evaluateRemark;

    private Date evaluateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDemandNo() {
        return demandNo;
    }

    public void setDemandNo(String demandNo) {
        this.demandNo = demandNo == null ? null : demandNo.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getComplainState() {
        return complainState;
    }

    public void setComplainState(String complainState) {
        this.complainState = complainState == null ? null : complainState.trim();
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
        this.evaluateRemark = evaluateRemark == null ? null : evaluateRemark.trim();
    }

    public Date getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(Date evaluateDate) {
        this.evaluateDate = evaluateDate;
    }
}