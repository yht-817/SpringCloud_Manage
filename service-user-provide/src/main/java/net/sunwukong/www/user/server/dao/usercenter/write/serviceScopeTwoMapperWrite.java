package net.sunwukong.www.user.server.dao.usercenter.write;

import net.sunwukong.www.user.bean.ServiceScopeTwo;

public interface serviceScopeTwoMapperWrite {
    int deleteByPrimaryKey(String id);

    int insert(ServiceScopeTwo record);

    int insertSelective(ServiceScopeTwo record);

    ServiceScopeTwo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ServiceScopeTwo record);

    int updateByPrimaryKey(ServiceScopeTwo record);
}