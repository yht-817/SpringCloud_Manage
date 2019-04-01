package net.sunwukong.www.marketing.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "UserApplyScope",description = "个体/机构申请服务城市")
public class UserApplyScope {
    @ApiModelProperty(dataType = "String", name = "id", value = "ID")
    @JsonIgnore
    private String id;

    @ApiModelProperty(dataType = "String",name = "applyNo",value = "申请编码")
    @JsonIgnore
    private String applyNo;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @JsonIgnore
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "scopeOneNo",value = "服务范围一级编码")
    @NotNull(message = "服务范围一级编码不能为空")
    private String scopeOneNo;

    @ApiModelProperty(dataType = "String",name = "scopeOneName",value = "服务范围一级名称")
    @NotNull(message = "服务范围一级名称不能为空")
    private String scopeOneName;

    @ApiModelProperty(dataType = "String",name = "scopeTwoNo",value = "服务范围二级编码")
    @NotNull(message = "服务范围二级编码不能为空")
    private String scopeTwoNo;

    @ApiModelProperty(dataType = "String",name = "scopeTwoName",value = "服务范围二级名称")
    @NotNull(message = "服务范围二级名称不能为空")
    private String scopeTwoName;

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

    public String getScopeOneNo() {
        return scopeOneNo;
    }

    public void setScopeOneNo(String scopeOneNo) {
        this.scopeOneNo = scopeOneNo == null ? null : scopeOneNo.trim();
    }

    public String getScopeOneName() {
        return scopeOneName;
    }

    public void setScopeOneName(String scopeOneName) {
        this.scopeOneName = scopeOneName == null ? null : scopeOneName.trim();
    }

    public String getScopeTwoNo() {
        return scopeTwoNo;
    }

    public void setScopeTwoNo(String scopeTwoNo) {
        this.scopeTwoNo = scopeTwoNo == null ? null : scopeTwoNo.trim();
    }

    public String getScopeTwoName() {
        return scopeTwoName;
    }

    public void setScopeTwoName(String scopeTwoName) {
        this.scopeTwoName = scopeTwoName == null ? null : scopeTwoName.trim();
    }
}