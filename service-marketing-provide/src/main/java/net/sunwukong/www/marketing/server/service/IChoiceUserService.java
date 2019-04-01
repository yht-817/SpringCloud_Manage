package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/13.
 */
public interface IChoiceUserService {

    /**
     * 添加用户精选券信息(用户购买券)
     * @param data
     * @return
     */
    ResponseData addChoiceUser(Map<String,String> data);


    /**
     * 我未使用的券列表
     * @param map
     * @return
     */
    ResponseData getNotUsedList(Map<String,String> map);


    /**
     * 我已使用的券列表
     * @param map
     * @return
     */
    ResponseData getUsedList(Map<String,String> map);


    /**
     * 我的已过期的票券列表
     * @param map
     * @return
     */
    ResponseData getOverdueList(Map<String,String> map);


    /**
     * 获取二维码
     * @param data
     * @return
     */
    ResponseData getShowCode(Map<String,String> data);


    /**
     * 消费券（使用券）
     * @param map
     * @return
     */
    ResponseData  updateUseState(Map<String,String> map);


    /**
     * 去支付,跳转支付页面
     * @param data
     * @return
     */
    ResponseData goPay(Map<String,String> data);
}
