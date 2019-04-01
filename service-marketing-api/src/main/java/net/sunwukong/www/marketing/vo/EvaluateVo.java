package net.sunwukong.www.marketing.vo;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: EvaluateVo
 * @Author: kangdong
 * @Date: 2018/7/24 下午8:24
 * @Description: 全球新闻资讯评论详情
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@ApiModel(value = "EvaluateVo",description = "资讯评论详情")
public class EvaluateVo {

    //评论用户编码
    private String userNo;
    //评论用户头像
    private String userHead;
    //评论用户昵称
    private String userNikeName;
    //评论内容
    private String evaluateContent;
    //评论时间
    private String evaluateDate;
    //当前用户是否已点赞该评论
    private Boolean liked;

    //页码
    //总数

    //=======================回复分页列表=======================
    private List<ReplyVo> replyVoList;


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

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public List<ReplyVo> getReplyVoList() {
        return replyVoList;
    }

    public void setReplyVoList(List<ReplyVo> replyVoList) {
        this.replyVoList = replyVoList;
    }
}
