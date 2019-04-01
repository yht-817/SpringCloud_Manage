package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;

/**
 * 说明:充值套餐服务接口
 *
 * @author Mick
 * CreateDate 2018/6/19/019 17:35
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IRechargeSetMealService {

    /**
     * 获取充值套餐列表
     * @return  返回响应数据
     */
    ResponseData getList();
}
