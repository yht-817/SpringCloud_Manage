package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:用户服务接口
 *
 * @author Mick
 * CreateDate 2018/6/25/025 21:37
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "marketing-server-1", path = "/user")
public interface UserInfoService {

    @RequestMapping(value = "/getusermsgisokperfect",method = RequestMethod.POST)
    ResponseData getUserMsgIsokPerfect(@RequestBody(required = false) RequestData<Map<String,String>> requestData);
}
