package net.sunwukong.www.chat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sunwukong.www.api.util.ApiTool;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 说明:分页检索查询对象
 *
 * @author Mick
 * CreateDate 2018/7/11/011 22:56
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@ApiModel(value = "PageSearchVo",description = "分页检索查询对象")
public class PageSearchVo {
    @ApiModelProperty(dataType = "int",name = "pageNo",value = "当前页")
    @NotNull(message = "当前页不能为空")
    @Range(min = 0, max = 99999, message = "当前页在1-99999之间 by range")
    private int pageNo;

    @ApiModelProperty(dataType = "int",name = "pageSize",value = "页面长度")
    @NotNull(message = "页面长度不能为空")
    @Range(min = 0, max = 50, message = "页面长度在1-50之间 by range")
    private int pageSize;

    @ApiModelProperty(dataType = "String",name = "content",value = "检索内容")
    @NotNull(message = "检索内容不能为空")
    private String content;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @NotNull(message = "用户编码不能为空")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "type",value = "检索类型（用户：0 群聊：1）")
    @NotNull(message = "检索类型不能为空")
    private String type;

    private int index;

    public int getPageNo() {
        index +=1;
        if (index%2==0){
            return ApiTool.getStartPage(pageNo,pageSize);
        }
        return pageNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
