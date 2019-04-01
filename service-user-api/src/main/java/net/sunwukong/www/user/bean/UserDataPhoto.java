package net.sunwukong.www.user.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserDataPhoto",description = "用户资料图片")
public class UserDataPhoto {
    @ApiModelProperty(dataType = "String",name = "id",value = "资料ID")
    private String id;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "dataPhoto1",value = "身份证正面")
    private String dataPhoto1;

    @ApiModelProperty(dataType = "String",name = "dataPhoto2",value = "身份证反面")
    private String dataPhoto2;

    @ApiModelProperty(dataType = "String",name = "dataPhoto3",value = "营业执照")
    private String dataPhoto3;

    @ApiModelProperty(dataType = "String",name = "dataPhoto4",value = "服务资质1")
    private String dataPhoto4;

    @ApiModelProperty(dataType = "String",name = "dataPhoto5",value = "服务资质2")
    private String dataPhoto5;

    @ApiModelProperty(dataType = "String",name = "dataPhoto6",value = "服务资质3")
    private String dataPhoto6;

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

    public String getDataPhoto1() {
        return dataPhoto1;
    }

    public void setDataPhoto1(String dataPhoto1) {
        this.dataPhoto1 = dataPhoto1 == null ? null : dataPhoto1.trim();
    }

    public String getDataPhoto2() {
        return dataPhoto2;
    }

    public void setDataPhoto2(String dataPhoto2) {
        this.dataPhoto2 = dataPhoto2 == null ? null : dataPhoto2.trim();
    }

    public String getDataPhoto3() {
        return dataPhoto3;
    }

    public void setDataPhoto3(String dataPhoto3) {
        this.dataPhoto3 = dataPhoto3 == null ? null : dataPhoto3.trim();
    }

    public String getDataPhoto4() {
        return dataPhoto4;
    }

    public void setDataPhoto4(String dataPhoto4) {
        this.dataPhoto4 = dataPhoto4 == null ? null : dataPhoto4.trim();
    }

    public String getDataPhoto5() {
        return dataPhoto5;
    }

    public void setDataPhoto5(String dataPhoto5) {
        this.dataPhoto5 = dataPhoto5 == null ? null : dataPhoto5.trim();
    }

    public String getDataPhoto6() {
        return dataPhoto6;
    }

    public void setDataPhoto6(String dataPhoto6) {
        this.dataPhoto6 = dataPhoto6 == null ? null : dataPhoto6.trim();
    }
}