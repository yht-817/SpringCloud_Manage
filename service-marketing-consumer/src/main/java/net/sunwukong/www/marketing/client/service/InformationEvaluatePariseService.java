package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/25.
 */
@FeignClient(value = "marketing-server-1",path = "/informationevaluateparise")
public interface InformationEvaluatePariseService {

    /**
     * 点赞或取消点赞
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/addOrCancel",method = RequestMethod.POST)
    ResponseData addOrCancel(@RequestBody(required = false) RequestData<Map<String,String>> requestData);
}
