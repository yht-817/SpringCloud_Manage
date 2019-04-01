package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.UserApplyCity;

import java.util.List;
import java.util.Map;

public interface UserApplyCityMapper extends BaseMapper<UserApplyCity>{
    List<UserApplyCity> findByApplyNo(String applyNo);

    List<UserApplyCity> findApplyNoAfterAuditCityList(Map<String, String> map);
}