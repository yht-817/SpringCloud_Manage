package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;

/**
 * Created by 岳虹廷 on  2018/7/3
 */
public interface IServiceScopeOneService {
    // 查询当前的地址服务范围的全部一对多的数据
    ResponseData getCityMore();

    ResponseData getreleaseMore();
}
