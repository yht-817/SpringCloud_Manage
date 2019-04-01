package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:发布需求服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/4 17:11
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "marketing-server-1",path = "/releasedemand")
public interface ReleaseDemandService {
    /**
     * 获取所在城市的城市运营官
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getcityofficerlist",method = RequestMethod.POST)
    ResponseData getCityOfficerList(RequestData<Map<String, String>> requestData);
}
