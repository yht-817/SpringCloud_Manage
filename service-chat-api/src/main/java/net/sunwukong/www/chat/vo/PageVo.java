package net.sunwukong.www.chat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sunwukong.www.api.util.ApiTool;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 说明:分页查询对象
 *
 * @author Mick
 * CreateDate 2018/7/11/011 22:12
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@ApiModel(value = "PageVo",description = "分页查询对象")
public class PageVo {
    @ApiModelProperty(dataType = "int",name = "pageNo",value = "当前页")
    @NotNull(message = "当前页不能为空")
    @Range(min = 0, max = 99999, message = "当前页在1-99999之间 by range")
    private int pageNo;

    @ApiModelProperty(dataType = "int",name = "pageSize",value = "页面长度")
    @NotNull(message = "页面长度不能为空")
    @Range(min = 0, max = 50, message = "页面长度在1-50之间 by range")
    private int pageSize;

    @ApiModelProperty(dataType = "String",name = "content",value = "检索内容")
    private String content;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群编码")
    private String groupNo;

    private int index;

    public int getPageNo() {
        index +=1;
        if (index%2==0){
            return ApiTool.getStartPage(pageNo,pageSize);
        }
        return pageNo;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
}
