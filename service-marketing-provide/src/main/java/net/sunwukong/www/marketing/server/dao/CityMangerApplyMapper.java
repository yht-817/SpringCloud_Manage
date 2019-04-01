package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.CityMangerApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CityMangerApplyMapper extends BaseMapper<CityMangerApply>{


    /**
     * 通过用户编码(运营官编码)获取信息
     * @param userNo
     * @return
     */
    CityMangerApply getCityMangerApplyByUserNo(String userNo);

    /**
     * 获取城市运营官列表
     * @param map
     * @return
     */
    List<Map<String,Object>> getCityOfficerList(Map<String,String> map);

    /**
     * 根据用户编码和审核状态获取申请信息
     * @param userNo        用户编码
     * @param auditState    审核状态
     * @return
     */
    CityMangerApply findByUserNoAndAuditState(@Param("userNo") String userNo, @Param("auditState") String auditState);
}