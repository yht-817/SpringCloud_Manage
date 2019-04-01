package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.DemandEvaluate;

/**
 * 说明:需求评价服务接口
 *
 * @author Mick
 * CreateDate 2018/6/25/025 18:16
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IDemandEvaluateService {
    /**
     * 添加服务评价
     * @param demandEvaluate
     * @return
     */
    ResponseData addDemandEvaluate(DemandEvaluate demandEvaluate);
}
