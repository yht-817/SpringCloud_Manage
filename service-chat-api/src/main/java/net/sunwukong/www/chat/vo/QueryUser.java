package net.sunwukong.www.chat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:查询用户对象
 *
 * @author Mick
 * CreateDate 2018/7/11/011 22:16
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@ApiModel(value = "PageVo",description = "分页查询对象")
public class QueryUser {
    @ApiModelProperty(dataType = "String",name = "id",value = "关系ID")
    private String id;
    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;
    @ApiModelProperty(dataType = "String",name = "userHead",value = "用户头像")
    private String userHead;
    @ApiModelProperty(dataType = "String",name = "nikeName",value = "用户昵称")
    private String nikeName;
    @ApiModelProperty(dataType = "boolean",name = "isokFriend",value = "是否是好友")
    private boolean isokFriend = false;

    @ApiModelProperty(dataType = "boolean",name = "isokInvite",value = "是否已经邀请(默认可以邀请)")
    private boolean isokInvite = false;

    public boolean isIsokInvite() {
        return isokInvite;
    }

    public void setIsokInvite(boolean isokInvite) {
        this.isokInvite = isokInvite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public boolean isIsokFriend() {
        return isokFriend;
    }

    public void setIsokFriend(boolean isokFriend) {
        this.isokFriend = isokFriend;
    }
}
