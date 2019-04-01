package net.sunwukong.www.chat.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.bean.FriendInvite;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:用户服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/20 23:47
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "chat-server-1",path = "/userinfo")
public interface IUserInfoService {

    /**
     * 根据用户编码获取用户ID
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getuserid",method = RequestMethod.POST)
    ResponseData getUserId(@RequestBody(required = false)RequestData<Map<String,String>> requestData);
}
