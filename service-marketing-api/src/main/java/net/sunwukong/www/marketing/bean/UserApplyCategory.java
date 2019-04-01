package net.sunwukong.www.marketing.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "UserApplyCategory",description = "个体/机构申请类目")
public class UserApplyCategory {
    @ApiModelProperty(dataType = "String",name = "id",value = "ID")
    @JsonIgnore
    private String id;

    @ApiModelProperty(dataType = "String",name = "applyNo",value = "申请编码")
    @JsonIgnore
    private String applyNo;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @JsonIgnore
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "categoryOneNo",value = "一级类目编码")
    @NotNull(message = "一级类目编码不能为空")
    private String categoryOneNo;

    @ApiModelProperty(dataType = "String",name = "categoryOneName",value = "一级类目名称")
    @NotNull(message = "一级类目名称不能为空")
    private String categoryOneName;

    @ApiModelProperty(dataType = "String",name = "categoryTwoNo",value = "二级类目编码")
    @NotNull(message = "二级类目编码不能为空")
    private String categoryTwoNo;

    @ApiModelProperty(dataType = "String",name = "categoryTwoName",value = "二级类目名称")
    @NotNull(message = "二级类目名称不能为空")
    private String categoryTwoName;

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

    public String getCategoryOneNo() {
        return categoryOneNo;
    }

    public void setCategoryOneNo(String categoryOneNo) {
        this.categoryOneNo = categoryOneNo == null ? null : categoryOneNo.trim();
    }

    public String getCategoryOneName() {
        return categoryOneName;
    }

    public void setCategoryOneName(String categoryOneName) {
        this.categoryOneName = categoryOneName == null ? null : categoryOneName.trim();
    }

    public String getCategoryTwoNo() {
        return categoryTwoNo;
    }

    public void setCategoryTwoNo(String categoryTwoNo) {
        this.categoryTwoNo = categoryTwoNo == null ? null : categoryTwoNo.trim();
    }

    public String getCategoryTwoName() {
        return categoryTwoName;
    }

    public void setCategoryTwoName(String categoryTwoName) {
        this.categoryTwoName = categoryTwoName == null ? null : categoryTwoName.trim();
    }
}