package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserOtherLogin;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:用户设置
 *
 * @author Mick
 * @CreateDate 2018/6/24 14:06
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "user-server-1", path = "/usersetting")
public interface UserSettingService {

    /**
     * 绑定账号
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/bindaccount",method = RequestMethod.POST)
    ResponseData bindAccount(RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/removebindaccount",method = RequestMethod.POST)
    ResponseData removeBindAccount(RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/bindotherlogin",method = RequestMethod.POST)
    ResponseData bindOtherLogin(RequestData<UserOtherLogin> requestData);

    @RequestMapping(value = "/removeotherlogin",method = RequestMethod.POST)
    ResponseData removeOtherLogin(RequestData<Map<String, String>> requestData);
}
