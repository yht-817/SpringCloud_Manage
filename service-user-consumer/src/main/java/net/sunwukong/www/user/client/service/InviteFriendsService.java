package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:邀请好友服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/23 17:13
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "user-server-1",path = "/invitefriends")
public interface InviteFriendsService {

    /**
     * 短信邀请好友
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/smsinvite",method = RequestMethod.POST)
    ResponseData smsInvite(@RequestBody(required = false) RequestData<Map<String,Object>> requestData);

    /**
     * 判断是否为好友
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/judgeisexistfriend",method = RequestMethod.POST)
    ResponseData judgeIsExistFriend(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 接受APP邀请
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/acceptinvitation",method = RequestMethod.POST)
    ResponseData acceptInvitation(RequestData<Map<String, String>> requestData);
}
