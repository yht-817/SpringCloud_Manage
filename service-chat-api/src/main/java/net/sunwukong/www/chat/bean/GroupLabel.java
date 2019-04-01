package net.sunwukong.www.chat.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "GroupLabel",description = "标签")
public class GroupLabel {
    @ApiModelProperty(dataType = "String",name = "id",value = "群ID")
    private String id;
    @ApiModelProperty(dataType = "String",name = "labelNo",value = "标签编码")
    private String labelNo;
    @ApiModelProperty(dataType = "String",name = "labelName",value = "标签名称")
    private String labelName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLabelNo() {
        return labelNo;
    }

    public void setLabelNo(String labelNo) {
        this.labelNo = labelNo == null ? null : labelNo.trim();
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }
}