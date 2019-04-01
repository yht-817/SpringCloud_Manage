package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.CollectionInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CollectionInfoMapper extends BaseMapper<CollectionInfo>{

    /**
     * 分页查询用户收藏精选
     * @param userNo    用户编码
     * @param start     开始位置
     * @param size      检索长度
     * @return
     */
    List<Map<String,String>> findByUserNoPageChoice(@Param(value = "userNo")String userNo,
                                                    @Param(value = "start")int start,
                                                    @Param(value = "size")int size);

    /**
     * 分页查询用户收藏资讯
     * @param userNo    用户编码
     * @param start     开始位置
     * @param size      检索长度
     * @return
     */
    List<Map<String,String>> findByUserNoPageInformation(@Param(value = "userNo")String userNo,
                                                         @Param(value = "start")int start,
                                                         @Param(value = "size")int size);

    /**
     * 根据用户编码和内容编码查询用户收藏信息
     * @param userNo    用户编码
     * @param contentNo 内容编码
     * @return
     */
    CollectionInfo findByUserNoAndContentNo(@Param(value = "userNo")String userNo,
                                            @Param(value = "contentNo")String contentNo);

    /**
     * 查询我是否收藏该条精选或资讯
     * @param userNo 用户编码
     * @param contentNo 内容编码
     * @param collectionType 收藏类型 10110001资讯收藏、10110002精选收藏
     * @return
     */
    boolean getCollectionCount(@Param(value = "userNo") String userNo,
                               @Param(value = "contentNo") String contentNo,
                               @Param(value = "collectionType") String collectionType);



    int deleteByResourceNoAndUserNo(@Param(value = "contentNo") String contentNo,
                                    @Param(value = "userNo") String userNo);
}