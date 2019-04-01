package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/12.
 */
public interface IChoiceApplyService {


    /**
     * 申请我要上精选
     * @param data
     * @return
     */
    ResponseData addApply(Map<String,String> data);

}
