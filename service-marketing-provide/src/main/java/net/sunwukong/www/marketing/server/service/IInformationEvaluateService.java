package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.InformationEvaluate;
import net.sunwukong.www.marketing.bean.InformationReply;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/25.
 */
public interface IInformationEvaluateService {

    /**
     * 分页获取评论信息
     *
     * @param data
     * @return
     */
    ResponseData evaluateList(Map<String, String> data);


    /**
     * 获取一条评论
     *
     * @param data
     * @return
     */
    ResponseData getOneEvaluate(Map<String, String> data);


    /**
     * 评论资讯
     * @param userNo
     * @param informationNo
     * @param evaluateContent
     * @return
     */
    ResponseData evaluate(String userNo,String informationNo,String evaluateContent);

    /**
     * 添加评论回复
     * @param informationReply
     * @return
     */
    ResponseData addCommentsReply(InformationReply informationReply);

    /**
     * 保存文章评论数据
     * @param informationEvaluate
     * @return
     */
    ResponseData addInformationEvaluate(InformationEvaluate informationEvaluate);
}
