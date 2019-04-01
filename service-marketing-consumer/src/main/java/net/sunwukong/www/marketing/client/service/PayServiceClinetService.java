package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 支付消费者
 */
@FeignClient(value = "marketing-server-1", path = "/pay")
public interface PayServiceClinetService {
    // 蟠桃充值业务 App进行支付
    @RequestMapping(value = "/peachalipay", method = RequestMethod.POST)
    ResponseData addPayDataInfo(@RequestBody(required = false) RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/appnotifyurl")
    String getAlipayInfo(@RequestBody(required = false) RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/wxappcoinurl")
    String getWeixinPayInfo(@RequestBody(required = false) RequestData<Map<String, String>> requestDataMap);

    @RequestMapping(value = "/siftpay")
    ResponseData payTick(@RequestBody(required = false) RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/siftpayinfoa")
    String getAlipaySiftInfo(RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/siftpayinfow")
    String getWeixinPayInfoSfit(RequestData<Map<String, String>> requestDataMap);

    @RequestMapping(value = "/webpay")
    ResponseData payTickWeb(RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/webpayinfo")
    String getAlipayWeb(RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/wxweburl")
    String getWeixinWeb(RequestData<Map<String, String>> requestDataMap);
}
