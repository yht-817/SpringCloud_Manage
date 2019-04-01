package net.sunwukong.www.user.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 二级服务范围的实体类是一级服务的子类
 */
@ApiModel(value = "ServiceScopeOne", description = "城市服务范围二级目录")
public class ServiceScopeTwo {
    @ApiModelProperty(dataType = "String", name = "id", value = "插入数据时的ID")
    private String id;
    @ApiModelProperty(dataType = "String", name = "scopeTwoNo", value = "二级范围的编码")
    private String scopeTwoNo;
    @ApiModelProperty(dataType = "String", name = "scopeTwoName", value = "二级范围的城市名")
    private String scopeTwoName;
    @ApiModelProperty(dataType = "String", name = "scopeTwoIcon", value = "城市对应的图标")
    private String scopeTwoIcon;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getScopeTwoIcon() {
        return scopeTwoIcon;
    }

    public void setScopeTwoIcon(String scopeTwoIcon) {
        if (!scopeTwoIcon.equals("")) {
            this.scopeTwoIcon = scopeTwoIcon;
        } else {
            this.scopeTwoIcon = "http://47.98.139.112:8080/static_img/userinfo/head/20180720141408865717";
        }
    }
}