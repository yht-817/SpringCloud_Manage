package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/13.
 */
public interface IChoiceUserPayService {

    /**
     * 添加用户购买券的支付纪录
     * @param data
     * @return
     */
    ResponseData addChoiceUserPay(Map<String,String> data);

}
