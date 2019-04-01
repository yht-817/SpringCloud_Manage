package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

/**
 * 说明:发布需求服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/4 15:27
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IReleaseDemandService {
    /**
     * 根据城市编码获取城市运营官列表
     * @param cityNo
     * @return
     */
    ResponseData getCityOfficerList(String cityNo);
}
