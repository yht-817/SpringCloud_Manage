package net.sunwukong.www.chat.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.bean.FriendInvite;
import net.sunwukong.www.chat.vo.AuditVo;
import net.sunwukong.www.chat.vo.PageVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:好友邀请服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/11 17:56
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "chat-server-1",path = "/friendinvite")
public interface IFriendInviteService {

    /**
     * 添加好友邀请信息
     * @param data
     * @return
     */
    @RequestMapping(value = "/addfriendinvite",method = RequestMethod.POST)
    ResponseData addFriendInvite(FriendInvite data);

    /**
     * 审核好友邀请信息
     * @param data
     * @return
     */
    @RequestMapping(value = "/auditfriendinvite",method = RequestMethod.POST)
    ResponseData auditFriendInvite(AuditVo data);

    /**
     * 分页获取好友列表
     * @param data
     * @return
     */
    @RequestMapping(value = "/querypagefriends",method = RequestMethod.POST)
    ResponseData queryPageFriends(PageVo data);

    /**
     * 删除好友
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/delfriend",method = RequestMethod.POST)
    ResponseData delFriend(RequestData<Map<String,String>> requestData);
}
