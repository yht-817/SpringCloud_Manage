package net.sunwukong.www.chat.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.bean.FriendGroup;
import net.sunwukong.www.chat.vo.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:群聊服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/9 14:49
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "chat-server-1",path = "/friendgroup")
public interface IFriendGroupService {

    /**
     * 创建群聊
     * @param friendGroup
     * @return
     */
    @RequestMapping(value = "/addfriendgroup",method = RequestMethod.POST)
    ResponseData addFriendGroup(FriendGroup friendGroup);

    /**
     * 解散群聊
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/writedissolve",method = RequestMethod.POST)
    ResponseData writeDissolve(RequestData<Map<String, String>> requestData);

    /**
     * 退出群聊
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/writeexit",method = RequestMethod.POST)
    ResponseData writeExit(RequestData<Map<String, String>> requestData);

    /**
     * 删除群成员
     * @param vo
     * @return
     */
    @RequestMapping(value = "/delgroupuser",method = RequestMethod.POST)
    ResponseData delGroupUser(DelGroupUserVo vo);

    /**
     * 修改群名称
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/updatefriendgroup",method = RequestMethod.POST)
    ResponseData updateFriendGroup(RequestData<Map<String, String>> requestData);

    /**
     * 设置群是否收费
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/updategroupsetting",method = RequestMethod.POST)
    ResponseData updateGroupSetting(RequestData<Map<String, String>> requestData);

    /**
     * 获取群内所有用户
     * @param pageVo
     * @return
     */
    @RequestMapping(value = "/querypagegroupusers",method = RequestMethod.POST)
    ResponseData queryPageGroupUsers(PageVo pageVo);

    /**
     * 获取用户群列表
     * @param pageVo
     * @return
     */
    @RequestMapping(value = "/querypageusergroups",method = RequestMethod.POST)
    ResponseData queryPageUserGroups(PageVo pageVo);

    /**
     * 获取标签列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/querylabels",method = RequestMethod.POST)
    ResponseData queryLabels(RequestData<Map<String, String>> requestData);

    /**
     * 审核邀请与申请
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/auditinvitation",method = RequestMethod.POST)
    ResponseData auditInvitation(RequestData<Map<String,String>> requestData);

    /**
     * 分页检索
     * @param pageSearchVo
     * @return
     */
    @RequestMapping(value = "/querypagesearch",method = RequestMethod.POST)
    ResponseData queryPageSearch(PageSearchVo pageSearchVo);

    /**
     * 获取群详情
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/querygroupdetails",method = RequestMethod.POST)
    ResponseData queryGroupDetails(RequestData<Map<String, String>> requestData);

    /**
     * 分页检索邀请群成员列表
     * @param data
     * @return
     */
    @RequestMapping(value = "/querypagesearchinviteuser",method = RequestMethod.POST)
    ResponseData queryPageSearchInviteUser(SearchInviteUserVo data);

    /**
     * 邀请加入进群
     * @param data
     * @return
     */
    @RequestMapping(value = "/invitejoin",method = RequestMethod.POST)
    ResponseData inviteJoin(JoinGroupVo data);

    /**
     * 申请加入进群
     * @param data
     * @return
     */
    @RequestMapping(value = "/applyjoin",method = RequestMethod.POST)
    ResponseData applyJoin(JoinGroupVo data);
}
