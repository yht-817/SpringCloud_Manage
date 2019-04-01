package net.sunwukong.www.chat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:检索群聊对象
 *
 * @author Mick
 * @CreateDate 2018/7/10 12:02
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "SearchGroups",description = "检索群聊对象")
public class SearchGroups {
    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群编码")
    private String groupNo;
    @ApiModelProperty(dataType = "String",name = "groupName",value = "群名称")
    private String groupName;
    @ApiModelProperty(dataType = "String",name = "groupHead",value = "群头像")
    private String groupHead;
    @ApiModelProperty(dataType = "int",name = "groupPersonNo",value = "群人数")
    private int groupPersonNo;
    @ApiModelProperty(dataType = "String",name = "groupNode",value = "群公告")
    private String groupNode;

    public String getGroupNode() {
        return groupNode;
    }

    public void setGroupNode(String groupNode) {
        this.groupNode = groupNode;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupHead() {
        return groupHead;
    }

    public void setGroupHead(String groupHead) {
        this.groupHead = groupHead;
    }

    public int getGroupPersonNo() {
        return groupPersonNo;
    }

    public void setGroupPersonNo(int groupPersonNo) {
        this.groupPersonNo = groupPersonNo;
    }
}
