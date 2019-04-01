package net.sunwukong.www.marketing.server.dao;


import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.DemandServer;
import net.sunwukong.www.marketing.server.domain.DemandParamPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DemandServerMapper extends BaseMapper<DemandServer> {

    /**
     * 通过用户编码和状态获取服务中的需求分页列表
     * @param demandParamPo 请求参数
     */
    List<DemandServer> getDemandServerListPageByUserNoAndState(DemandParamPo demandParamPo);

    /**
     * 通过服务机构编码和状态获取服务列表
     * @param userNo
     * @param demandState
     * @return
     */
    List<DemandServer> getDemandServerListByUserNoAndState(@Param(value = "userNo") String userNo,
                                                           @Param(value = "demandState") String demandState);

    /**
     * 通过单号和已结单状态查询服务需求表，获取结单服务者
     * @param demandNo
     * @param demandState
     * @return
     */
    DemandServer getDemandServerByDemandNoAndState(@Param(value = "demandNo") String demandNo,
                                                   @Param(value = "demandState") String demandState);

    DemandServer findByDemandNoOrderByDate(String demandNo);

    List<Map<String,Object>> findByUserNoAndDemandStateList(Map<String, String> map);
}