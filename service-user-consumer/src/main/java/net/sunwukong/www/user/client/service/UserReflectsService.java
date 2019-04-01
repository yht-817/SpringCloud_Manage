package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "user-server-1", path = "/reflect")
public interface UserReflectsService {
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseData addUserTxInfo(RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/countuserinfo", method = RequestMethod.POST)
    ResponseData userInfoRs(RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/addorder", method = RequestMethod.POST)
    ResponseData addOrders(RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/removeaccount", method = RequestMethod.POST)
    ResponseData removeAccounts(RequestData<Map<String, String>> requestData);
}
