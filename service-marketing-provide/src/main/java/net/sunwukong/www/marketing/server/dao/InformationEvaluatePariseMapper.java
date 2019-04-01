package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.marketing.bean.InformationEvaluateParise;
import org.apache.ibatis.annotations.Param;

public interface InformationEvaluatePariseMapper {
    int deleteByPrimaryKey(String id);

    int insert(InformationEvaluateParise record);

    int insertSelective(InformationEvaluateParise record);

    InformationEvaluateParise selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InformationEvaluateParise record);

    int updateByPrimaryKey(InformationEvaluateParise record);


    /**
     * 判断当前查看用户是否点赞该评论
     * @param userNo
     * @param evaluateNo
     * @param informationNo
     * @return
     */
    InformationEvaluateParise findByUserNoAndEvaluateNoAndInformationNo(@Param(value = "userNo") String userNo,
                                     @Param(value = "evaluateNo") String evaluateNo,
                                     @Param(value = "informationNo") String informationNo);


    /**
     * 点赞或取消点赞
     * @param evaluateNo
     * @param informationNo
     * @param userNo
     * @return
     */
    int deleteByEvaluateNoAndUserNo(@Param(value = "evaluateNo")String evaluateNo,
                                    @Param(value = "informationNo")String informationNo,
                                    @Param(value = "userNo")String userNo);
}