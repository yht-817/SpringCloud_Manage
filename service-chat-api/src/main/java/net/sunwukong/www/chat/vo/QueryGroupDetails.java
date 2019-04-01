package net.sunwukong.www.chat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel(value = "QueryGroupDetails",description = "群详情对象")
public class QueryGroupDetails {

    @ApiModelProperty(dataType = "String",name = "id",value = "群ID")
    private String id;

    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群编码")
    private String groupNo;

    @ApiModelProperty(dataType = "String",name = "groupName",value = "群名称")
    private String groupName;

    @ApiModelProperty(dataType = "String",name = "createDate",value = "创建时间")
    private Date createDate;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "创建人编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "groupNode",value = "群公告")
    private String groupNode;

    @ApiModelProperty(dataType = "String",name = "groupState",value = "群状态(正常:10160001 解散:10160002)")
    private String groupState;

    @ApiModelProperty(dataType = "String",name = "ifPayGroup",value = "是否付费(公开群:10340001 收费群:10340002)")
    private String ifPayGroup;

    @ApiModelProperty(dataType = "String",name = "groupHead",value = "群头像")
    private String groupHead;

    @ApiModelProperty(dataType = "String",name = "payAmount",value = "付费金额")
    private BigDecimal payAmount;

    @ApiModelProperty(dataType = "String",name = "labelNo",value = "标签编码")
    private String labelNo;

    @ApiModelProperty(dataType = "String",name = "labelName",value = "标签名称")
    private String labelName;

    @ApiModelProperty(dataType = "String",name = "isJoin",value = "当前访问用户是否加入")
    private boolean isokJoin;

    @ApiModelProperty(dataType = "String",name = "isokOwner",value = "是否是群主")
    private boolean isokOwner;

    @ApiModelProperty(dataType = "List",name = "queryUsers",value = "详情显示的8个群成员列表")
    private List<QueryUser> queryUsers;

    @ApiModelProperty(dataType = "int",name = "groupCount",value = "群人数")
    private int groupCount;

    public int getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(int groupCount) {
        this.groupCount = groupCount;
    }

    public List<QueryUser> getQueryUsers() {
        return queryUsers;
    }

    public void setQueryUsers(List<QueryUser> queryUsers) {
        this.queryUsers = queryUsers;
    }

    public boolean isIsokOwner() {
        return isokOwner;
    }

    public void setIsokOwner(boolean isokOwner) {
        this.isokOwner = isokOwner;
    }

    public boolean isIsokJoin() {
        return isokJoin;
    }

    public void setIsokJoin(boolean isokJoin) {
        this.isokJoin = isokJoin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo == null ? null : groupNo.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getGroupNode() {
        return groupNode;
    }

    public void setGroupNode(String groupNode) {
        this.groupNode = groupNode == null ? null : groupNode.trim();
    }

    public String getGroupState() {
        return groupState;
    }

    public void setGroupState(String groupState) {
        this.groupState = groupState == null ? null : groupState.trim();
    }

    public String getIfPayGroup() {
        return ifPayGroup;
    }

    public void setIfPayGroup(String ifPayGroup) {
        this.ifPayGroup = ifPayGroup == null ? null : ifPayGroup.trim();
    }

    public String getGroupHead() {
        return groupHead;
    }

    public void setGroupHead(String groupHead) {
        this.groupHead = groupHead == null ? null : groupHead.trim();
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getLabelNo() {
        return labelNo;
    }

    public void setLabelNo(String labelNo) {
        this.labelNo = labelNo;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}