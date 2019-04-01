package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.marketing.bean.InformationEvaluate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InformationEvaluateMapper {
    int deleteByPrimaryKey(String id);

    int insert(InformationEvaluate record);

    int insertSelective(InformationEvaluate record);

    InformationEvaluate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InformationEvaluate record);

    int updateByPrimaryKey(InformationEvaluate record);

    /**
     * 分页获取评论列表
     */
    List<Map<String, Object>> getPageList(@Param(value = "userno") String userno, @Param(value = "informationNo") String informationNo,
                                          @Param(value = "start") int start, @Param(value = "end") int end);

    void updateReplyNum(Map<String, String> map);

    /**
     * 通过评论编码和资讯编码查询评论信息
     */
    InformationEvaluate getByEvaluateNoAndInformationNo(@Param(value = "informationNo") String informationNo, @Param(value = "evaluateNo") String evaluateNo);

    // 插入回复资讯文章的评论
    int add_Zxhf(@Param(value = "id") String id, @Param(value = "evaluateNo") String evaluateNo, @Param(value = "userNo") String userNo,
                 @Param(value = "informationNo") String informationNo, @Param(value = "evaluateContent") String evaluateContent, @Param(value = "datatime") String datatime);


    // 评论文章成功后把评论的总数加一
    int updateComments(@Param(value = "informationNo") String informationNo);

    // 对用户的评论进行评论数加一
    int updateUserComments(@Param(value = "evaluateNo") String evaluateNo);

    // 查询用户信息
    Map<String, Object> queryUserInfo(@Param(value = "userNo") String userNo);

    // 删除点攒
    int deleteCancel(@Param(value = "informationNo") String informationNo, @Param(value = "evaluateNo") String evaluateNo, @Param(value = "userNo") String userNo);

    // 点赞
    int addCancel(@Param(value = "id") String id, @Param(value = "informationNo") String informationNo,
                  @Param(value = "evaluateNo") String evaluateNo, @Param(value = "userNo") String userNo, @Param(value = "datatime") String datatime);

    // 获取咨询子评论的主评论
    List<Map<String, Object>> getByEvaluateNo(@Param(value = "informationNo") String informationNo,
                                              @Param(value = "evaluateNo") String evaluateNo,
                                              @Param(value = "userNo") String userNo);

    /**
     * 评论点赞加一
     * @param evaluateNo 评论编码
     * @return
     */
    int updateLikePlus(String evaluateNo);

    /**
     * 评论点赞加一
     * @param evaluateNo 评论编码
     * @return
     */
    int updateLikeMinus(String evaluateNo);
}