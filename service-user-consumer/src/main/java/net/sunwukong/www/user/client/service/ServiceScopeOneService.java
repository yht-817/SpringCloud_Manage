package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 岳虹廷 on  2018/7/3
 * <p>
 * 城市服务范围消费者接口类
 */
@FeignClient(value = "user-server-1", path = "/servicecity")
public interface ServiceScopeOneService {
    /**
     * 获取当前的服务范围的消费接口
     *
     * @return
     */
    @RequestMapping(value = "/citylist", method = RequestMethod.POST)
    ResponseData getCityList();

    @RequestMapping(value = "/release", method = RequestMethod.POST)
    ResponseData getreleaseMores();
}
