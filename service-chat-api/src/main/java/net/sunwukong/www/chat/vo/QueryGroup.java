package net.sunwukong.www.chat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:用户群列表对象
 *
 * @author Mick
 * @CreateDate 2018/7/10 12:02
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryGroup",description = "用户群列表对象")
public class QueryGroup {
    @ApiModelProperty(dataType = "String",name = "id",value = "关系ID")
    private String id;
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
    @ApiModelProperty(dataType = "String",name = "isokOwner",value = "是否是群主")
    private boolean isokOwner = false;
    @ApiModelProperty(dataType = "String",name = "isokJoin",value = "是否已加入该群")
    private boolean isokJoin = false;

    public boolean isIsokJoin() {
        return isokJoin;
    }

    public void setIsokJoin(boolean isokJoin) {
        this.isokJoin = isokJoin;
    }

    public boolean isIsokOwner() {
        return isokOwner;
    }

    public void setIsokOwner(boolean isokOwner) {
        this.isokOwner = isokOwner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGroupNode() {
        return groupNode;
    }

    public void setGroupNode(String groupNode) {
        this.groupNode = groupNode;
    }
}
