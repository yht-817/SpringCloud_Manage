package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/12.
 */
@FeignClient(value = "marketing-server-1",path = "/choiceapply")
public interface ChoiceApplyService {

    /**
     * 申请精选
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    ResponseData apply(@RequestBody(required = false) RequestData<Map<String,String>> requestData);
}
