package net.sunwukong.www.chat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sunwukong.www.api.util.ApiTool;

/**
 * 说明:检索群成员邀请对象
 *
 * @author Mick
 * @CreateDate 2018/7/12 14:27
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@ApiModel(value = "SearchInviteUserVo",description = "检索群成员邀请对象")
public class SearchInviteUserVo {
    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;
    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群聊编码")
    private String groupNo;
    @ApiModelProperty(dataType = "String",name = "content",value = "检索内容")
    private String content;
    @ApiModelProperty(dataType = "int",name = "pageNo",value = "当前页")
    private int pageNo;
    @ApiModelProperty(dataType = "int",name = "pageSize",value = "页面长度")
    private int pageSize;

    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPageNo() {
        index +=1;
        if (index%2==0){
            return ApiTool.getStartPage(pageNo,pageSize);
        }
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "SearchInviteUserVo{" +
                "userNo='" + userNo + '\'' +
                ", groupNo='" + groupNo + '\'' +
                ", content='" + content + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", index=" + index +
                '}';
    }
}
