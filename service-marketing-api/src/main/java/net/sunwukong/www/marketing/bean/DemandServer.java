package net.sunwukong.www.marketing.bean;

import java.util.Date;

public class DemandServer {
    private String id;

    private String userNo;

    private String superUserNo;

    private String demandNo;

    private String demandState;

    private Date changeDate;

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

    public String getSuperUserNo() {
        return superUserNo;
    }

    public void setSuperUserNo(String superUserNo) {
        this.superUserNo = superUserNo == null ? null : superUserNo.trim();
    }

    public String getDemandNo() {
        return demandNo;
    }

    public void setDemandNo(String demandNo) {
        this.demandNo = demandNo == null ? null : demandNo.trim();
    }

    public String getDemandState() {
        return demandState;
    }

    public void setDemandState(String demandState) {
        this.demandState = demandState == null ? null : demandState.trim();
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
}