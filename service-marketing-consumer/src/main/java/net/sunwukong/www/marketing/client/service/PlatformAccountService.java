package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tungkang on 2018/6/21.
 */
@FeignClient(value = "marketing-server-1", path = "/platformaccount")
public interface PlatformAccountService {

    /**
     * 获取平台账户金额
     * @return ResponseData
     */
    @RequestMapping(value = "/getPlatformAccount",method = RequestMethod.POST)
    ResponseData getPlatformAccount();
}
