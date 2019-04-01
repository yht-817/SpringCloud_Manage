package net.sunwukong.www.marketing.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "UserApplyPhoto",description = "个体/机构申请图片")
public class UserApplyPhoto {
    @ApiModelProperty(dataType = "String",name = "id",value = "ID")
    @JsonIgnore
    private String id;

    @ApiModelProperty(dataType = "String",name = "applyNo",value = "申请编码")
    @JsonIgnore
    private String applyNo;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @JsonIgnore
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "dataPhotoType",value = "图片类型(身份证正面:10310001 身份证反面:10310002 营业执照:10310003 资质证明:10310004)")
    @NotNull(message = "证件类型不能为空")
    private String dataPhotoType;

    @ApiModelProperty(dataType = "String",name = "dataPhoto",value = "图片地址")
    @NotNull(message = "证件地址不能为空")
    private String dataPhoto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getDataPhotoType() {
        return dataPhotoType;
    }

    public void setDataPhotoType(String dataPhotoType) {
        this.dataPhotoType = dataPhotoType == null ? null : dataPhotoType.trim();
    }

    public String getDataPhoto() {
        return dataPhoto;
    }

    public void setDataPhoto(String dataPhoto) {
        this.dataPhoto = dataPhoto == null ? null : dataPhoto.trim();
    }
}