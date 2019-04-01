package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/17.
 */
public interface IChoiceShareDetailService {

    /**
     * 分享后购买票券
     * @param data
     * @return
     */
    ResponseData addShareBuy(Map<String,String> data);
}
