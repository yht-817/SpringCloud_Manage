package net.sunwukong.www.chat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:群用户列表对象
 *
 * @author Mick
 * @CreateDate 2018/7/10 11:51
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "GroupUsers",description = "群用户列表对象")
public class GroupUsers {
    @ApiModelProperty(dataType = "String",name = "id",value = "好友群用户ID")
    private String id;
    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;
    @ApiModelProperty(dataType = "String",name = "userHead",value = "用户头像")
    private String userHead;
    @ApiModelProperty(dataType = "String",name = "nikeName",value = "用户昵称")
    private String nikeName;
    @ApiModelProperty(dataType = "boolean",name = "isokOwner",value = "是否是群主")
    private boolean isokOwner = false;

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

    public boolean isIsokOwner() {
        return isokOwner;
    }

    public void setIsokOwner(boolean isokOwner) {
        this.isokOwner = isokOwner;
    }
}
