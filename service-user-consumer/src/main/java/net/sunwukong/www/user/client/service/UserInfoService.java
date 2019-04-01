package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserInfo;
import net.sunwukong.www.user.input.RegisterInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:用户服务接口
 *
 * @author Mick
 * CreateDate 2018/6/9/009 22:08
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "user-server-1",path = "/user")
public interface UserInfoService {

    @RequestMapping(value = "/checkaccount",method = RequestMethod.POST)
    ResponseData checkAccount(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    ResponseData register(@RequestBody(required = false) RequestData<RegisterInput> requestData);

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    ResponseData login(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    @RequestMapping(value = "/otherlogin",method = RequestMethod.POST)
    ResponseData otherLogin(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    @RequestMapping(value = "/updatedata",method = RequestMethod.POST)
    ResponseData updateUserInfo(@RequestBody(required = false) RequestData<UserInfo> requestData);

    @RequestMapping(value = "/updateuserhead",method = RequestMethod.POST)
    ResponseData updateUserHead(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    @RequestMapping(value = "/resetpassword",method = RequestMethod.POST)
    ResponseData resetPassword(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    @RequestMapping(value = "/findbyiduserinfo",method = RequestMethod.POST)
    ResponseData findByIdUserInfo(RequestData<Map<String,String>> requestData);

    @RequestMapping(value = "/updateuserevaluate",method = RequestMethod.POST)
    ResponseData updateUserEvaluate(RequestData<Map<String,String>> requestData);

    @RequestMapping(value = "/wechatlogin",method = RequestMethod.POST)
    ResponseData weChatLogin(RequestData<Map<String,String>> requestData);

    @RequestMapping(value = "/userstates",method = RequestMethod.POST)
    ResponseData userStates(RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/getvolume",method = RequestMethod.POST)
    ResponseData getVolume(RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/adduserphoneno",method = RequestMethod.POST)
    ResponseData addUserPhoneNo(RequestData<Map<String, String>> requestData);
}
