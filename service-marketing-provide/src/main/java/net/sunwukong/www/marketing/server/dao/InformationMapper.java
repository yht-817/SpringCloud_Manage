package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.Information;
import net.sunwukong.www.marketing.vo.InformationDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InformationMapper extends BaseMapper<Information> {

    /**
     * 根据资讯类型分页查询
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> findByInformationClassAndLimit(Map<String, Object> map);


    /**
     * 根据资讯编码增加资讯的评论数
     *
     * @param informationNo
     * @return
     */
    int updateEvaluateNumPlus(String informationNo);

    /**
     * 根据资讯编码增加资讯的点击数
     *
     * @param informationNo
     * @return
     */
    int updateClickNumPlus(String informationNo);

    /**
     * 获取资讯详情
     */
    InformationDetailVo getDetails(@Param(value = "informationNo") String informationNo, @Param("userNo") String userNo);

    /**
     * 根据资讯编码查询资讯对象
     * @param informationNo 资讯编码
     * @return
     */
    Information findByInformationNo(String informationNo);

    /**
     * 设置点击数量
     * @param configVal     点击数量
     * @param informationNo 资讯编码
     * @return
     */
    boolean setClickNum(@Param("clickNum") Integer configVal, @Param("informationNo") String informationNo);
}