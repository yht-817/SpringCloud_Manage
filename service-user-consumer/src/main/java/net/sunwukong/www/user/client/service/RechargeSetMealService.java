package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:充值套餐服务接口
 *
 * @author Mick
 * CreateDate 2018/6/19/019 17:42
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "user-server-1")
@RequestMapping(value = "/rechargesetmeal")
public interface RechargeSetMealService {

    @RequestMapping(value = "/getlist",method = RequestMethod.POST)
    ResponseData getList(@RequestBody(required = false) RequestData<Map<String, String>> requestData);
}
