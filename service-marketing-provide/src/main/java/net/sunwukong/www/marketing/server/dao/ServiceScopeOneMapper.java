package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.ServiceScopeOne;

import java.util.List;

public interface ServiceScopeOneMapper extends BaseMapper<ServiceScopeOne>{

    List<ServiceScopeOne> findServiceScopeList();
}