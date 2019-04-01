package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:用户服务城市服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/24 11:48
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "user-server-1", path = "/usercity")
public interface UserCityService {
    @RequestMapping(value = "/getusercity",method = RequestMethod.POST)
    ResponseData getUserCity(RequestData<Map<String,String>> requestData);
}
