package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.user.bean.ServiceScopeOne;

import java.util.List;

public interface ServiceScopeOneMapperRead {
    // 获取服务范围的一对多的关系
    List<ServiceScopeOne> getAllList();

    List<ServiceScopeOne> getreleaseAllList();
}