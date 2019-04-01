package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.UserApplyCategory;

import java.util.List;
import java.util.Map;

public interface UserApplyCategoryMapper extends BaseMapper<UserApplyCategory>{
    List<UserApplyCategory> findByApplyNo(String applyNo);

    List<UserApplyCategory> findApplyNoAfterAuditCategoryList(Map<String, String> map);
}