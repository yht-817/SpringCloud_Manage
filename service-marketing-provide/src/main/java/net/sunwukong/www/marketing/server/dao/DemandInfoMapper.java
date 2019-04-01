package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.DemandInfo;
import net.sunwukong.www.marketing.server.domain.DemandParamPo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DemandInfoMapper extends BaseMapper<DemandInfo> {
    /**
     * 根据用户编码和需求状态获取需求列表
     *
     * @param demandParamPo
     * @return
     */
    List<DemandInfo> getDemandListByUserNoAndState(DemandParamPo demandParamPo);

    /**
     * 获取24小时未接单的需求信息
     *
     * @return
     */
    List<DemandInfo> getNotStartedDemandListByTimeLength(@Param(value = "timeLength") int timeLength);


    /**
     * 通过城市编码，需求状态获取该城市下需求信息
     *
     * @param cityNo
     * @param demandState
     */
    List<DemandInfo> getDemandListByCityNoAndState(@Param(value = "cityNo") String cityNo,
                                                   @Param(value = "demandState") String demandState);

    /**
     * 通过订单号查询需求信息
     *
     * @param demandNo
     * @return
     */
    DemandInfo getDemandInfoByDemandNo(@Param(value = "demandNo") String demandNo);

    /**
     * 查询首页需求列表
     *
     * @param data
     * @return
     */
    List<DemandInfo> findHomeDemandinfo(Map<String, Object> data);


    /**
     * 通过单号更新需求表信息
     *
     * @param demandInfo
     * @return
     */
    int updateByDemandNoSelective(DemandInfo demandInfo);


    /**
     * 根据用户编码和服务状态获取需求列表
     *
     * @param demandParamPo
     * @return
     */
    List<DemandInfo> getDemandListByUserNoAndServerState(DemandParamPo demandParamPo);


    /**
     * 查询我的未开始的需求列表(待接单、已抢单)
     *
     * @param demandParamPo
     * @return
     */
    List<DemandInfo> getNotStartListByUserNo(DemandParamPo demandParamPo);


    /**
     * 计算服务用户(机构、个人)各个阶段服务的数量
     *
     * @param serviceUser
     * @param demandState
     * @return
     */
    HashMap<String, Object> getCountsByServerNoAndState(@Param(value = "serviceUser") String serviceUser,
                                                        @Param(value = "demandState") String demandState);

    /**
     * 获取服务机构下面的累积完成单数
     *
     * @param map1
     * @return
     */
    int countByUserNoAndServerStateAndDemandState(Map<String, String> map1);

    /**
     * 根据城市编码和需求状态获取需求列表
     * @param cityNo        城市编码
     * @param demandState   需求状态
     * @param start         开始位置
     * @param size          页面长度
     * @return
     */
    List<Map<String,Object>> findByCityNoAndDemandState(@Param("cityNo") String cityNo,
                                                        @Param("demandState") String demandState,
                                                        @Param("start") int start,
                                                        @Param("size") int size);

    /**
     * 根据需求编码获取需求详情
     * @param demandNo  需求编码
     * @return
     */
    Map<String,Object> getNotStartDemandDetail(String demandNo);
}