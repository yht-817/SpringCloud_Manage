package net.sunwukong.www.user.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserHomePage",description = "个人主页信息")
public class UserHomePage {
    @ApiModelProperty(dataType = "String",name = "id",value = "主页ID")
    private String id;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "selfEvaluation",value = "自我介绍")
    private String selfEvaluation;

    private String homePhoto1;

    private String homePhoto2;

    private String homePhoto3;

    private String homePhoto4;

    private String homePhoto5;

    private String homePhoto6;

    private String homePhoto7;

    private String homePhoto8;

    private String homePhoto9;

    @ApiModelProperty(dataType = "String",name = "coverNo",value = "封面标识")
    private String coverNo;

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

    public String getSelfEvaluation() {
        return selfEvaluation;
    }

    public void setSelfEvaluation(String selfEvaluation) {
        this.selfEvaluation = selfEvaluation == null ? null : selfEvaluation.trim();
    }

    public String getHomePhoto1() {
        return homePhoto1;
    }

    public void setHomePhoto1(String homePhoto1) {
        this.homePhoto1 = homePhoto1 == null ? null : homePhoto1.trim();
    }

    public String getHomePhoto2() {
        return homePhoto2;
    }

    public void setHomePhoto2(String homePhoto2) {
        this.homePhoto2 = homePhoto2 == null ? null : homePhoto2.trim();
    }

    public String getHomePhoto3() {
        return homePhoto3;
    }

    public void setHomePhoto3(String homePhoto3) {
        this.homePhoto3 = homePhoto3 == null ? null : homePhoto3.trim();
    }

    public String getHomePhoto4() {
        return homePhoto4;
    }

    public void setHomePhoto4(String homePhoto4) {
        this.homePhoto4 = homePhoto4 == null ? null : homePhoto4.trim();
    }

    public String getHomePhoto5() {
        return homePhoto5;
    }

    public void setHomePhoto5(String homePhoto5) {
        this.homePhoto5 = homePhoto5 == null ? null : homePhoto5.trim();
    }

    public String getHomePhoto6() {
        return homePhoto6;
    }

    public void setHomePhoto6(String homePhoto6) {
        this.homePhoto6 = homePhoto6 == null ? null : homePhoto6.trim();
    }

    public String getHomePhoto7() {
        return homePhoto7;
    }

    public void setHomePhoto7(String homePhoto7) {
        this.homePhoto7 = homePhoto7 == null ? null : homePhoto7.trim();
    }

    public String getHomePhoto8() {
        return homePhoto8;
    }

    public void setHomePhoto8(String homePhoto8) {
        this.homePhoto8 = homePhoto8 == null ? null : homePhoto8.trim();
    }

    public String getHomePhoto9() {
        return homePhoto9;
    }

    public void setHomePhoto9(String homePhoto9) {
        this.homePhoto9 = homePhoto9 == null ? null : homePhoto9.trim();
    }

    public String getCoverNo() {
        return coverNo;
    }

    public void setCoverNo(String coverNo) {
        this.coverNo = coverNo == null ? null : coverNo.trim();
    }
}