package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.UserApplyScope;

import java.util.List;
import java.util.Map;

public interface UserApplyScopeMapper extends BaseMapper<UserApplyScope>{

    List<UserApplyScope> findApplyNoAfterAuditScopeList(Map<String, String> map);
}