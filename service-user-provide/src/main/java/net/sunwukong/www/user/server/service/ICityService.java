package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;

/**
 * 说明:城市服务接口
 *
 * @author Mick
 * CreateDate 2018/6/22/022 22:00
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface ICityService {

    /**
     * 获取服务城市列表
     * @return
     */
    ResponseData getCityList();

    ResponseData getCity(String cityNo);
}
