package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:APP版本升级接口
 *
 * @author swk
 * @CreateDate 2018/8/20 17:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "user-server-1")
@RequestMapping(value = "/mobileversion")
public interface MobileVersionService {

    /**
     * 获取APP版本信息
     * @param appName   app名称（ios android）
     * @param appType   app类型（测试 正式）
     * @param version   当前版本
     * @return
     */
    @RequestMapping(value = "/getappversion",method = RequestMethod.POST)
    ResponseData getAppVersion(@RequestBody(required = false) RequestData<Map<String, String>> requestData);
}
