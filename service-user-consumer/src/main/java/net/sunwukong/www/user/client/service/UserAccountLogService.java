package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:账户金额变动服务接口
 *
 * @author Mick
 * CreateDate 2018/6/15 17:34
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "user-server-1")
@RequestMapping(value = "/useraccountlog")
public interface UserAccountLogService {

    /**
     * 获取昨日金币
     * @param userNo    用户编码
     * @return          返回响应数据
     */
    @RequestMapping(value = "/getyesterdaycoin",method = RequestMethod.POST)
    ResponseData getYesterDayCoin(@RequestBody(required = false) RequestData<Map<String, String>> requestData);

    /**
     * 获取资金变动明细
     * @param userNo    用户编码
     * @return          返回响应数据
     */
    @RequestMapping(value = "/getdetailspage",method = RequestMethod.POST)
    ResponseData getDetailsPage(@RequestBody(required = false) RequestData<Map<String, String>> requestData);
}
