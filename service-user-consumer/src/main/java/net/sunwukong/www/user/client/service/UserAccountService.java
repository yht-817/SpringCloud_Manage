package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:验证码服务接口
 *
 * @author Mick
 * CreateDate 2018/6/10/010 9:18
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "user-server-1")
@RequestMapping(value = "/useraccount")
public interface UserAccountService {

    @RequestMapping(value = "/getallcoin",method = RequestMethod.POST)
    ResponseData getallCoin(@RequestBody(required = false) RequestData<Map<String, String>> requestData);
}
