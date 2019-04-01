package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.util.Map;

/**
 * Created by tungkang on 2018/6/16.
 */
public interface IMarketingSendSetService {

    /**
     * 根据时间和类型获取营销赠送设置信息
     * @return
     */
    ResponseData getMarketingSendSetByDateAndType(Map<String,Object> map);


    /**
     * 注册赠送
     * @return
     */
    ResponseData getNowMarketingSendSet();
}
