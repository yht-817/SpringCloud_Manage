package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.marketing.bean.DemandEvaluate;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DemandEvaluateMapper {

    int deleteByPrimaryKey(String id);

    int insert(DemandEvaluate record);

    int insertSelective(DemandEvaluate record);

    DemandEvaluate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DemandEvaluate record);

    int updateByPrimaryKey(DemandEvaluate record);

    /**
     * 通过服务机构编码获取评价为投诉的需求单
     * @param userNo
     */
    List<DemandEvaluate> getEvaluateByMerchanNo(@Param(value = "userNo") String userNo,
                                                @Param(value = "complainState") String complainState);

    /**
     * 通过需求单号获取该需求的评价信息
     * @param demandNo
     * @return
     */
    DemandEvaluate getEvaluateByDemandNo(String demandNo);

    /**
     * 计算评价服务机构每项评分总和
     * @param userNo
     * @return
     */
    HashMap<String,Object> getStarSumByUserNo(String userNo);
}