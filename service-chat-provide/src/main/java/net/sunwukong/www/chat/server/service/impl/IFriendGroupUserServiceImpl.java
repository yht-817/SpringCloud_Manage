package net.sunwukong.www.chat.server.service.impl;

import com.sdkinfo.www.im.easemob.dao.IMChatGroup;
import com.sdkinfo.www.log.StaticLog;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.MsgTitleEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.chat.bean.ChatServiceMessage;
import net.sunwukong.www.chat.bean.FriendGroup;
import net.sunwukong.www.chat.bean.FriendGroupUser;
import net.sunwukong.www.chat.bean.UserInfo;
import net.sunwukong.www.chat.server.dao.FriendGroupUserMapper;
import net.sunwukong.www.chat.server.service.IFriendGroupUserService;
import net.sunwukong.www.chat.server.web.BaseController;
import net.sunwukong.www.chat.vo.GroupUsers;
import net.sunwukong.www.chat.vo.QueryGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 说明:好友群信息服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/9 16:14
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IFriendGroupUserServiceImpl extends BaseController implements IFriendGroupUserService {

    @Autowired
    FriendGroupUserMapper friendGroupUserMapper;

    /**
     * 退出群聊
     * @param userNo    用户编码
     * @param groupNo   群聊编码
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData writeExit(String userNo, String groupNo) {
        ResponseData responseData = new ResponseData();
        //查询群成员记录
        FriendGroupUser byUserNoAndGroupNo = friendGroupUserMapper.findByUserNoAndGroupNo(userNo, groupNo);
        if (byUserNoAndGroupNo==null){
            throw new GrilException("未在群中查询到用户记录");
        }
        //删除本地群成员记录
        friendGroupUserMapper.deleteByPrimaryKey(byUserNoAndGroupNo.getId());
        //删除环信群成员
        boolean removeUser = IMChatGroup.removeUsers(groupNo, userNo);
        if (!removeUser){
            throw new GrilException("环信-删除群成员失败");
        }
        responseData.setData(byUserNoAndGroupNo);
        return responseData;
    }

    /**
     * 获取群用户列表
     * @param groupNo   群编码
     * @param content   检索内容
     * @param start     开始位置
     * @param end
     * @return
     */
    @Override
    public ResponseData queryPageGroupUsers(String groupNo,String content,int start,int end) {
        ResponseData responseData = new ResponseData();
        List<GroupUsers> byGroupNo = friendGroupUserMapper.queryPageGroupUsers(groupNo,SysCode.CODE_10170001.getCode(),content,start,end);
        FriendGroup friendGroup = iFriendGroupService.findByGroupNo(groupNo);
        byGroupNo.forEach(groupUsers -> {
            if (groupUsers.getUserNo().equals(friendGroup.getUserNo())){
                groupUsers.setIsokOwner(true);
            }
        });
        responseData.setData(byGroupNo);
        return responseData;
    }

    /**
     * 获取用户群列表
     * @param userNo    用户编码
     * @param start     开始位置
     * @param size      检索长度
     * @return
     */
    @Override
    public ResponseData queryPageUserGroups(String userNo,int start,int size) {
        ResponseData responseData = new ResponseData();
        List<QueryGroup> byUserNoAndGroupUserStateList = friendGroupUserMapper.queryPageUserGroups(userNo, SysCode.CODE_10170001.getCode(),SysCode.CODE_10160001.getCode(),start,size);
        byUserNoAndGroupUserStateList.forEach(queryGroup -> {
            //查询群内所有正常用户信息
            List<GroupUsers> groupUsers = friendGroupUserMapper.queryPageGroupUsers(queryGroup.getGroupNo(), SysCode.CODE_10170001.getCode(),null,0,0);
            //获取群内所有正常用户数量
            queryGroup.setGroupPersonNo(groupUsers.size());
            //查询是否已经加入该群
            queryGroup.setIsokJoin(iFriendGroupUserService.findByGroupNoAndUserNoExists(queryGroup.getGroupNo(),userNo,SysCode.CODE_10170001.getCode()));
            if (queryGroup.isIsokJoin()){
                //查询当前用户是否是群主
                queryGroup.setIsokOwner(iFriendGroupService.queryJudgeIsokOwner(queryGroup.getGroupNo(),userNo));
            }
        });
        responseData.setData(byUserNoAndGroupUserStateList);
        return responseData;
    }

    /**
     * 添加邀请信息
     * @param friendGroupUser   邀请对象
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addFriendGroupUser(FriendGroupUser friendGroupUser) {
        //获取群聊信息
        FriendGroup byGroupNo = iFriendGroupService.findByGroupNo(friendGroupUser.getGroupNo());
        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
        //判断是邀请还是申请
        if (friendGroupUser.getGroupUserState().equals(SysCode.CODE_10170003.getCode())){
            // TODO:邀请成功发送推送消息给被邀请者
            //获取邀请者的信息
            UserInfo byUserNo = iUserInfoService.findByUserNo(friendGroupUser.getFriendUserNo());
            //获取消息接受者信息
            chatServiceMessage.setUserNo(friendGroupUser.getUserNo());
            chatServiceMessage.setSendName(byUserNo.getNikeName());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10008.getMsg());
            chatServiceMessage.setSendProject1(MsgTitleEnum.CODE_10008.getMsg());
            chatServiceMessage.setSendText(byUserNo.getNikeName() + " 邀请您加入" + ApiTool.StringToHtml("red", byGroupNo.getGroupName()));
            //设置服务通知类型
            chatServiceMessage.setNoticeType(SysCode.CODE_10390003.getCode());
            chatServiceMessage.setServiceNo(friendGroupUser.getInviteNo());
        }else if (friendGroupUser.getGroupUserState().equals(SysCode.CODE_10170005.getCode())){
            // TODO:申请成功发送推送消息给群主
            //获取申请者用户消息
            UserInfo byUserNo = iUserInfoService.findByUserNo(friendGroupUser.getUserNo());
            //设置消息接收者信息
            chatServiceMessage.setUserNo(byGroupNo.getUserNo());
            chatServiceMessage.setSendName(byUserNo.getNikeName());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10007.getMsg());
            //chatServiceMessage.setSendProject1(MsgTitleEnum.CODE_10007.getMsg());
            chatServiceMessage.setSendText(ApiTool.StringToHtml("red",byUserNo.getNikeName())+" 申请加入"+byGroupNo.getGroupName());
            //设置服务通知类型
            chatServiceMessage.setNoticeType(SysCode.CODE_10390003.getCode());
            chatServiceMessage.setServiceNo(friendGroupUser.getInviteNo());
        }else if (friendGroupUser.getGroupUserState().equals(SysCode.CODE_10170001.getCode())){
            // TODO:群主添加自己的聊天记录
            //获取群主用户消息
            UserInfo byUserNo = iUserInfoService.findByUserNo(friendGroupUser.getUserNo());
            //设置消息接收者信息
            chatServiceMessage.setUserNo(byGroupNo.getUserNo());
            chatServiceMessage.setSendName(byUserNo.getNikeName());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
            //chatServiceMessage.setSendProject1(MsgTitleEnum.CODE_10009.getMsg());
            chatServiceMessage.setSendText("你已成功创建 "+ApiTool.StringToHtml("red",byGroupNo.getGroupName()));
            //设置服务通知类型
            chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
        }else {
            throw new GrilException("请输入正确的在群状态!");
        }
        friendGroupUser.setId(DataBaseTool.createId());
        friendGroupUser.setJoinDate(new Date());
        int i = friendGroupUserMapper.insert(friendGroupUser);
        if (i<=0){
            throw new GrilException("邀请好友失败");
        }
        iChatServiceMessageService.addChatServiceMessage(chatServiceMessage);
        return BaseResp.getInstance();
    }

    /**
     * 审核
     * @param inviteNo      申请编码
     * @param auditState    审核状态
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData writeAudit(String inviteNo, boolean auditState) {
        //查询消息是否已处理
        FriendGroupUser byInviteNo = friendGroupUserMapper.findByInviteNo(inviteNo);
        //如果消息状态不等于邀请中或申请中 说明已处理
        if (!byInviteNo.getGroupUserState().equals(SysCode.CODE_10170003.getCode())&&!byInviteNo.getGroupUserState().equals(SysCode.CODE_10170005.getCode())){
            throw new GrilException("已处理");
        }
        //获取好友用户群信息
        FriendGroupUser friendGroupUser = friendGroupUserMapper.findByInviteNo(inviteNo);
        //获取群聊信息
        FriendGroup byGroupNo = iFriendGroupService.findByGroupNo(friendGroupUser.getGroupNo());
        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
        //判断是否通过
        if (auditState){
            //判断是邀请还是申请
            if (friendGroupUser.getGroupUserState().equals(SysCode.CODE_10170003.getCode())){
                //判断邀请者是否是群主
                boolean judgeIsokOwner = iFriendGroupService.queryJudgeIsokOwner(friendGroupUser.getGroupNo(), friendGroupUser.getFriendUserNo());
                if (judgeIsokOwner){
                    //如果状态为邀请并且邀请者还是群主且自己还同意了邀请,那么状态就为正常
                    friendGroupUser.setGroupUserState(SysCode.CODE_10170001.getCode());
                    //添加环信群成员
                    boolean addUsers = IMChatGroup.addUsers(friendGroupUser.getGroupNo(), friendGroupUser.getUserNo());
                    if (!addUsers){
                        throw new GrilException("添加环信群成员失败(原因:用户在环信里面不存在或群在环信里面不存在)");
                    }
                    // TODO:发送成功消息给群主
                    //查询被邀请者的信息
                    UserInfo byUserNo = iUserInfoService.findByUserNo(friendGroupUser.getUserNo());
                    chatServiceMessage.setUserNo(byGroupNo.getUserNo());
                    chatServiceMessage.setSendName(byUserNo.getNikeName());
                    chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
                    chatServiceMessage.setSendText(byUserNo.getNikeName()+" 已成功加入"+ApiTool.StringToHtml("red",byGroupNo.getGroupName()));
                    chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
                }else {
                    //如果状态为邀请并且邀请者不是群主且自己还同意了邀请,那么状态为群主待审核
                    friendGroupUser.setGroupUserState(SysCode.CODE_10170006.getCode());
                    //获取被邀请者的用户信息
                    UserInfo byUserNo = iUserInfoService.findByUserNo(friendGroupUser.getUserNo());
                    //发送申请通知给群主
                    chatServiceMessage.setUserNo(byGroupNo.getUserNo());
                    chatServiceMessage.setSendName(byUserNo.getNikeName());
                    chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10007.getMsg());
                    chatServiceMessage.setSendText(byUserNo.getUserNo()+" 申请加入"+ApiTool.StringToHtml("red",byGroupNo.getGroupName()));
                    chatServiceMessage.setNoticeType(SysCode.CODE_10390003.getCode());
                    chatServiceMessage.setServiceNo(friendGroupUser.getInviteNo());
                }
                // TODO:审核成功发送推送消息给群主
                StaticLog.info("审核成功发送推送消息给群主");
            }else {
                //如果审核通过 并且状态为申请中 那么状态就更新为正常
                friendGroupUser.setGroupUserState(SysCode.CODE_10170001.getCode());
                //添加环信群成员
                boolean addUsers = IMChatGroup.addUsers(friendGroupUser.getGroupNo(), friendGroupUser.getUserNo());
                if (!addUsers){
                    throw new GrilException("添加环信群成员失败(原因:用户在环信里面不存在或群在环信里面不存在)");
                }
                // TODO:审核成功发送推送消息给申请者
                StaticLog.info("审核成功发送推送消息给申请者");

                // TODO:发送成功消息给被邀请者
                //查询群主的信息
                UserInfo byUserNo = iUserInfoService.findByUserNo(byGroupNo.getUserNo());
                chatServiceMessage.setUserNo(friendGroupUser.getUserNo());
                chatServiceMessage.setSendName(byUserNo.getNikeName());
                chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
                chatServiceMessage.setSendText("您已成功加入"+ApiTool.StringToHtml("red",byGroupNo.getGroupName()));
                chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
            }
        }else {
            //如果是拒绝 并且状态为邀请中 那么状态就更新为拒绝
            friendGroupUser.setGroupUserState(SysCode.CODE_10170004.getCode());
            //判断是邀请还是申请
            if (friendGroupUser.getGroupUserState().equals(SysCode.CODE_10170003.getCode())){
                //查询被邀请者的信息
                UserInfo byUserNo = iUserInfoService.findByUserNo(friendGroupUser.getUserNo());
                // TODO:发送拒绝通知给群主
                StaticLog.info("审核拒绝发送推送消息给群主");
                chatServiceMessage.setUserNo(friendGroupUser.getFriendUserNo());
                chatServiceMessage.setSendName(byUserNo.getNikeName());
                chatServiceMessage.setSendText(byUserNo.getNikeName()+" 已拒绝加入 "+ ApiTool.StringToHtml("red",byGroupNo.getGroupName()));

            }else {
                //查询群主信息
                UserInfo byUserNo = iUserInfoService.findByUserNo(byGroupNo.getUserNo());
                // TODO:发送拒绝通知给申请者
                StaticLog.info("审核拒绝发送推送消息给申请者");
                chatServiceMessage.setUserNo(friendGroupUser.getUserNo());
                chatServiceMessage.setSendName(byUserNo.getNikeName());
                chatServiceMessage.setSendText(byUserNo.getNikeName()+" 拒绝您加入 "+ApiTool.StringToHtml("red",byGroupNo.getGroupName()));
            }
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
            chatServiceMessage.setNoticeType(SysCode.CODE_10390003.getCode());
        }
        //添加通知信息
        iChatServiceMessageService.addChatServiceMessage(chatServiceMessage);
        //修改信息
        friendGroupUserMapper.updateByPrimaryKey(friendGroupUser);
        return BaseResp.getInstance();
    }

    /**
     * 根据在群状态获取用户是否在群里面
     * @param groupNo           群编码
     * @param userNo            用户编码
     * @param groupUserState    在群状态
     * @return
     */
    @Override
    public boolean findByGroupNoAndUserNoExists(String groupNo, String userNo, String groupUserState) {
        FriendGroupUser byGroupNoAndUserNoExists = friendGroupUserMapper.findByGroupNoAndUserNoExists(groupNo, userNo, groupUserState);
        if (byGroupNoAndUserNoExists==null){
            return false;
        }
        return true;
    }

    /**
     * 获取群成员数量
     * @param groupNo   群编码
     * @return
     */
    @Override
    public int readCountByGroupNo(String groupNo) {
        return friendGroupUserMapper.countByGroupNoAndGroupUserState(groupNo,SysCode.CODE_10170001.getCode());
    }

    /**
     * 删除群成员
     *
     * @param userNo  群主编码
     * @param groupNo 群编码
     * @param userNos 用户编码列表
     * @return
     */
    @Override
    public ResponseData writeDelGroupUser(String userNo, String groupNo, List<String> userNos) {
        //查询群主信息
        UserInfo byUserNo = iUserInfoService.findByUserNo(userNo);
        //查询群聊信息
        FriendGroup byGroupNo = iFriendGroupService.findByGroupNo(groupNo);
        int i = friendGroupUserMapper.delByGroupNoAndUserNos(groupNo, userNos);
        if (i <= 0) {
            throw new GrilException("删除群成员失败");
        }
        userNos.forEach(user->{
            //删除环信群成员
            boolean removeUser = IMChatGroup.removeUsers(groupNo, user);
            if (!removeUser){
                throw new GrilException("环信-删除群成员失败");
            }

            // TODO:推送消息
            ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setSendName(byUserNo.getNikeName());
            chatServiceMessage.setSendText("你已被 " + byUserNo.getNikeName() + " 从 " + byGroupNo.getGroupName() + " 移除");
            chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
            chatServiceMessage.setUserNo(user);
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10009.getMsg());
            iChatServiceMessageService.addChatServiceMessage(chatServiceMessage);
        });


        return BaseResp.getInstance();
    }
}
