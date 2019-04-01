package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:个体服务控制服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/26 15:40
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "marketing-server-1", path = "/singledemandserver")
public interface SingleDemandServerService {

    @RequestMapping(value = "/getserverlist",method = RequestMethod.POST)
    ResponseData getServerList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    @RequestMapping(value = "/completeserver",method = RequestMethod.POST)
    ResponseData completeServer(@RequestBody(required = false) RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/getserverdetails",method = RequestMethod.POST)
    ResponseData getServerDetails(RequestData<Map<String, String>> requestData);
}
