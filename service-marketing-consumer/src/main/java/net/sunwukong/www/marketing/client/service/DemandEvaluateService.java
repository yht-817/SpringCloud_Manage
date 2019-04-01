package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.DemandEvaluate;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 说明:需求评价服务接口
 *
 * @author Mick
 * CreateDate 2018/6/25/025 18:12
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "marketing-server-1", path = "/demandevaluate")
public interface DemandEvaluateService {

    @RequestMapping(value = "/adddemandevaluate",method = RequestMethod.POST)
    ResponseData addDemandEvaluate(RequestData<DemandEvaluate> requestData);
}
