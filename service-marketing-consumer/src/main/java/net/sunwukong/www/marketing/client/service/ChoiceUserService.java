package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/16.
 */
@FeignClient(value = "marketing-server-1",path = "/choiceuser")
public interface ChoiceUserService {

    /**
     * 获取我使用过的优惠券列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getUsedList",method = RequestMethod.POST)
    ResponseData getUsedList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 获取我的未使用的优惠券列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getNotUsedList",method = RequestMethod.POST)
    ResponseData getNotUsedList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 我的已过期的票券列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getOverdueList",method = RequestMethod.POST)
    ResponseData getOverdueList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * 显示二维码
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/show",method = RequestMethod.POST)
    ResponseData show(RequestData<Map<String,String>> requestData);


    /**
     * 消费优惠券
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/used",method = RequestMethod.POST)
    ResponseData used(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 未支付消息，跳转支付页面
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/goPay",method = RequestMethod.POST)
    ResponseData goPay(@RequestBody(required = false) RequestData<Map<String,String>> requestData);
}
