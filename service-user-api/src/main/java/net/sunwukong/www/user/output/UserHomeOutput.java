/**
 * Copyright 2018 bejson.com
 */
package net.sunwukong.www.user.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sunwukong.www.user.bean.UserHomePage;

/**
 * Auto-generated: 2018-08-09 15:47:1
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@ApiModel(value = "UserHomeOutput",description = "用户主页")
public class UserHomeOutput {
    @ApiModelProperty(dataType = "String",name = "isokFriend",value = "是否是好友")
    private boolean isokFriend;
    @ApiModelProperty(dataType = "String",name = "serverNo",value = "累计服务单数")
    private int serverNo;
    @ApiModelProperty(dataType = "String",name = "fansNo",value = "粉丝数量")
    private int fansNo;
    @ApiModelProperty(dataType = "String",name = "pageData",value = "个人主页数据")
    private UserHomePage pageData;
    @ApiModelProperty(dataType = "String",name = "visit",value = "访问数据")
    private Visit visit;
    public void setIsokFriend(boolean isokFriend) {
        this.isokFriend = isokFriend;
    }
    public boolean getIsokFriend() {
        return isokFriend;
    }

    public void setServerNo(int serverNo) {
        this.serverNo = serverNo;
    }
    public int getServerNo() {
        return serverNo;
    }

    public void setFansNo(int fansNo) {
        this.fansNo = fansNo;
    }
    public int getFansNo() {
        return fansNo;
    }

    public boolean isIsokFriend() {
        return isokFriend;
    }

    public UserHomePage getPageData() {
        return pageData;
    }

    public void setPageData(UserHomePage pageData) {
        this.pageData = pageData;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }
    public Visit getVisit() {
        return visit;
    }

}