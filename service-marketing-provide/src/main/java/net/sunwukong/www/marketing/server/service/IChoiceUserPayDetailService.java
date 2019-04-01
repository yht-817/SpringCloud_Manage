package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/16.
 */
public interface IChoiceUserPayDetailService {

    /**
     * 新增精选支付详情
     * @param data
     * @return
     */
    ResponseData addChoiceUserPayDetail(Map<String,String> data);
}
