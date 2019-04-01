package net.sunwukong.www.user.server.service;

import net.sunwukong.www.marketing.bean.MarketingSendSet;

import java.util.Date;

/**
 * 说明:营销赠送设置服务接口
 *
 * @author Mick
 * CreateDate 2018/7/4/004 0:52
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IMarketingSendSetService {
    /**
     * 根据当前时间和赠送类型查询当前可以赠送的套餐
     * @param nowDate   当前时间
     * @param sendType  赠送类型
     * @return
     */
    MarketingSendSet findBySendTypeBetweenDate(Date nowDate, String sendType);
}
