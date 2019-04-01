package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.vo.ServerDetails;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:
 *
 * @author Mick
 * @CreateDate 2018/6/23 13:32
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "user-server-1")
@RequestMapping(value = "/applyserver")
public interface ApplyServerService {

    @RequestMapping(value = "/saveserverdetails",method = RequestMethod.POST)
    ResponseData saveServerDetails(RequestData<ServerDetails> saveServerDetails);

    @RequestMapping(value = "/getserverdetails",method = RequestMethod.POST)
    ResponseData getServerDetails(RequestData<Map<String,String>> requestData);
}
