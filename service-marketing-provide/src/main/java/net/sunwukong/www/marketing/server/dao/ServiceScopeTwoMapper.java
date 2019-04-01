package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.marketing.bean.ServiceScopeTwo;

public interface ServiceScopeTwoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ServiceScopeTwo record);

    int insertSelective(ServiceScopeTwo record);

    ServiceScopeTwo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ServiceScopeTwo record);

    int updateByPrimaryKey(ServiceScopeTwo record);
}