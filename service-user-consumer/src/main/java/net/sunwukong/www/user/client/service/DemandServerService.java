package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:需求服务接口服务
 *
 * @author Mick
 * CreateDate 2018/6/13/013 15:17
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "marketing-server-1")
@RequestMapping(value = "/demandserver")
public interface DemandServerService {
    /**
     * 查询用户累计服务单数
     * @param userNo        用户编码
     * @return              返回响应对象
     */
    @RequestMapping(value = "/getuserserverno",method = RequestMethod.POST)
    ResponseData getUserServerNo(@RequestBody(required = false) RequestData<Map<String, String>> requestData);
}
