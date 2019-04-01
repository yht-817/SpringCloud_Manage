package net.sunwukong.www.user.server.dao.usercenter.write;

import net.sunwukong.www.user.bean.ServiceScopeOne;

public interface ServiceScopeOneMapperWrite {
    int deleteByPrimaryKey(String id);

    int insert(ServiceScopeOne record);

    int insertSelective(ServiceScopeOne record);

    ServiceScopeOne selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ServiceScopeOne record);

    int updateByPrimaryKey(ServiceScopeOne record);
}