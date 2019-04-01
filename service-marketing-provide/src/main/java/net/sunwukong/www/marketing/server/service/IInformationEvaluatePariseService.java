package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.InformationEvaluateParise;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/25.
 */
public interface IInformationEvaluatePariseService {

    /**
     * 设置文章评论点赞
     * @param informationNo 资讯编码
     * @param evaluateNo    评论编码
     * @param userNo        用户编码
     * @param liked         是否点赞（true：点赞 false：未点赞）
     * @return
     */
    ResponseData setEvaluateParise(String informationNo,String evaluateNo,String userNo,String liked);

    /**
     * 保存评论点赞信息
     * @param informationEvaluateParise
     * @return
     */
    ResponseData addInformationEvaluateParise(InformationEvaluateParise informationEvaluateParise);
}
