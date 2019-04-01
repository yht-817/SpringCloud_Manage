package net.sunwukong.www.user.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;

@ApiModel(value = "CategoryTwo",description = "二级目录实体类")
public class CategoryTwo {
    @ApiModelProperty(dataType = "String",name = "id",value = "二级目录ID")
    private String id;

    @ApiModelProperty(dataType = "String",name = "categoryOneNo",value = "一级目录编码")
    @JsonIgnore
    private String categoryOneNo;

    @ApiModelProperty(dataType = "String",name = "categoryTwoIcon",value = "二级目录Icon")
    private String categoryTwoIcon;

    @ApiModelProperty(dataType = "String",name = "categoryTwoNo",value = "二级目录编码")
    private String categoryTwoNo;

    @ApiModelProperty(dataType = "String",name = "categoryTwoName",value = "二级目录名称")
    private String categoryTwoName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCategoryOneNo() {
        return categoryOneNo;
    }

    public void setCategoryOneNo(String categoryOneNo) {
        this.categoryOneNo = categoryOneNo == null ? null : categoryOneNo.trim();
    }

    public String getCategoryTwoIcon() {
        return categoryTwoIcon;
    }

    public void setCategoryTwoIcon(String categoryTwoIcon) {
        this.categoryTwoIcon = categoryTwoIcon;
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

    @Override
    public String toString() {
        return "CategoryTwo{" +
                "id='" + id + '\'' +
                ", categoryOneNo='" + categoryOneNo + '\'' +
                ", categoryTwoIcon='" + categoryTwoIcon + '\'' +
                ", categoryTwoNo='" + categoryTwoNo + '\'' +
                ", categoryTwoName='" + categoryTwoName + '\'' +
                '}';
    }
}