package net.sunwukong.www.marketing.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "UserApplyCategory",description = "个体/机构申请城市")
public class UserApplyCity {
    @ApiModelProperty(dataType = "String",name = "id",value = "ID")
    @JsonIgnore
    private String id;

    @ApiModelProperty(dataType = "String",name = "applyNo",value = "申请编码")
    @JsonIgnore
    private String applyNo;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @JsonIgnore
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "cityNo",value = "城市编码")
    @NotNull(message = "城市编码不能为空")
    private String cityNo;

    @ApiModelProperty(dataType = "String",name = "cityName",value = "城市名称")
    @NotNull(message = "城市名称不能为空")
    private String cityName;

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

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo == null ? null : cityNo.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }
}