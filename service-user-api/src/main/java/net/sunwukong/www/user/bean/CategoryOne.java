package net.sunwukong.www.user.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "CategoryOne",description = "一级目录实体类")
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class CategoryOne {
    @ApiModelProperty(dataType = "String",name = "id",value = "一级目录ID")
    private String id;
    @ApiModelProperty(dataType = "String",name = "categoryOneIcon",value = "一级目录Icon")
    private String categoryOneIcon;
    @ApiModelProperty(dataType = "String",name = "categoryOneNo",value = "一级目录编码")
    private String categoryOneNo;
    @ApiModelProperty(dataType = "String",name = "categoryOneName",value = "一级目录名称")
    private String categoryOneName;
    @ApiModelProperty(dataType = "List<CategoryTwo>",name = "categoryTwos",value = "二级目录对象")
    private List<CategoryTwo> categoryTwos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCategoryOneIcon() {
        return categoryOneIcon;
    }

    public void setCategoryOneIcon(String categoryOneIcon) {
        this.categoryOneIcon = categoryOneIcon;
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

    public List<CategoryTwo> getCategoryTwos() {
        return categoryTwos;
    }

    public void setCategoryTwos(List<CategoryTwo> categoryTwos) {
        this.categoryTwos = categoryTwos;
    }

    @Override
    public String toString() {
        return "CategoryOne{" +
                "id='" + id + '\'' +
                ", categoryOneIcon='" + categoryOneIcon + '\'' +
                ", categoryOneNo='" + categoryOneNo + '\'' +
                ", categoryOneName='" + categoryOneName + '\'' +
                ", categoryTwos=" + categoryTwos +
                '}';
    }
}