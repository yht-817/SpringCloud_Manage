package net.sunwukong.www.marketing.vo;

import io.swagger.annotations.ApiModel;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ReplyVo
 * @Author: kangdong
 * @Date: 2018/7/24 下午8:24
 * @Description: 评论回复详情
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@ApiModel(value = "ReplyVo",description = "评论回复详情")
public class ReplyVo {
    //回复用户编码
    private String userNo;
    //回复用户头像
    private String userHead;
    //回复用户昵称
    private String userNikeName;
    //回复内容
    private String replyContent;
    //回复时间
    private String replyDate;
    //当前用户是否已点赞该回复
    private Boolean replyLiked;

    //页码
    //总数


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

    public String getUserNikeName() {
        return userNikeName;
    }

    public void setUserNikeName(String userNikeName) {
        this.userNikeName = userNikeName;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public Boolean getReplyLiked() {
        return replyLiked;
    }

    public void setReplyLiked(Boolean replyLiked) {
        this.replyLiked = replyLiked;
    }
}
