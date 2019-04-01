package net.sunwukong.www.chat.server.service.impl;

import com.sdkinfo.www.im.easemob.dao.IMUser;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.chat.bean.ChatServiceMessage;
import net.sunwukong.www.chat.bean.FriendInvite;
import net.sunwukong.www.chat.bean.UserInfo;
import net.sunwukong.www.chat.server.dao.FriendInviteMapper;
import net.sunwukong.www.api.enums.MsgTitleEnum;
import net.sunwukong.www.chat.server.service.IFriendInviteService;
import net.sunwukong.www.chat.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 说明:好友邀请服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/9 17:28
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IFriendInviteServiceImpl extends BaseController implements IFriendInviteService {

    @Autowired
    FriendInviteMapper friendInviteMapper;

    /**
     * 添加邀请
     * @param friendInvite  好友关系对象
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addFriendInvite(FriendInvite friendInvite) {
        friendInvite.setId(DataBaseTool.createId());
        friendInvite.setInviteDate(new Date());
        int i = friendInviteMapper.insert(friendInvite);
        if (i<=0){
            throw new GrilException("邀请好友失败");
        }
        return BaseResp.getInstance();
    }

    /**
     * 添加邀请
     * @param friendInvite  好友关系对象
     * @return
     */
    @Override
    public ResponseData addInvite(FriendInvite friendInvite) {
        //判断是否已经发送邀请
        FriendInvite exist = friendInviteMapper.findByUserNoAndFriendUserNoAndInviteState(friendInvite.getUserNo(), friendInvite.getFriendUserNo(), SysCode.CODE_10150001.getCode());
        if (exist==null){
            friendInvite.setInviteNo(DataBaseTool.createNo("apply"));
            //设置状态邀请中
            friendInvite.setInviteState(SysCode.CODE_10150001.getCode());
            //添加好友申请记录
            addFriendInvite(friendInvite);
        }else {
            friendInvite = exist;
        }
        //添加邀请成功
        // TODO:发送通知
        //查询添加者的用户信息
        UserInfo byUserNo = iUserInfoService.findByUserNo(friendInvite.getUserNo());
        //保存通知消息
        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
        chatServiceMessage.setUserNo(friendInvite.getFriendUserNo());
        chatServiceMessage.setSendName(byUserNo.getNikeName());
        chatServiceMessage.setNoticeType(SysCode.CODE_10390002.getCode());
        chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10010.getMsg());
        chatServiceMessage.setSendText(ApiTool.StringToHtml("red",byUserNo.getNikeName())+" 申请添加您为好友");
        chatServiceMessage.setServiceNo(friendInvite.getInviteNo());
        iChatServiceMessageService.addChatServiceMessage(chatServiceMessage);
        return BaseResp.getInstance();
    }

    /**
     * 审核邀请
     * @param inviteNo      申请编码
     * @param inviteState   审核状态(同意:10150002 拒绝:10150003)
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData witerAuditFriendInvite(String inviteNo, String inviteState) {
        //1查询消息是否已处理
        FriendInvite byInviteNo = findByInviteNo(inviteNo);
        //2如果消息状态不为邀请中 说明已处理
        if (!byInviteNo.getInviteState().equals(SysCode.CODE_10150001.getCode())){
            throw new GrilException("已处理");
        }
        //3查询申请信息
        FriendInvite friendInvite = findByInviteNo(inviteNo);
        //4查询审核方的用户信息
        UserInfo byUserNo = iUserInfoService.findByUserNo(friendInvite.getFriendUserNo());
        friendInvite.setInviteState(inviteState);
        friendInvite.setChangeDate(new Date());
        friendInviteMapper.updateByPrimaryKey(friendInvite);
        if (inviteState.equals(SysCode.CODE_10150002.getCode())){
            boolean b = IMUser.addFriendSingle(friendInvite.getUserNo(), friendInvite.getFriendUserNo());
            if (!b){
                throw new GrilException("添加环信好友失败(可能原因-用户未在环信注册)");
            }
            //5同意 添加反向好友
            FriendInvite friend = new FriendInvite();
            friend.setUserNo(friendInvite.getFriendUserNo());
            friend.setFriendUserNo(friendInvite.getUserNo());
            //设置状态为已同意
            friend.setInviteState(inviteState);
            friend.setInviteNo(DataBaseTool.createNo("apply"));
            addFriendInvite(friend);
            //6保存通知消息
            ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setUserNo(friendInvite.getUserNo());
            chatServiceMessage.setSendName(byUserNo.getNikeName());
            chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10010.getMsg());
            chatServiceMessage.setSendText(byUserNo.getNikeName()+" 已同意你的好友请求");
            iChatServiceMessageService.addChatServiceMessage(chatServiceMessage);
            // TODO:同意加好友发送通知

        }else {
            //5拒绝
            ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setUserNo(friendInvite.getUserNo());
            chatServiceMessage.setSendName(byUserNo.getNikeName());
            chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10010.getMsg());
            chatServiceMessage.setSendText(byUserNo.getNikeName()+" 拒绝了你的好友请求");
            iChatServiceMessageService.addChatServiceMessage(chatServiceMessage);
            // TODO:拒绝加好友发送通知
        }
        return BaseResp.getInstance();
    }

    /**
     * 根据申请编码查询邀请信息
     * @param inviteNo    申请编码
     * @return
     */
    @Override
    public FriendInvite findByInviteNo(String inviteNo) {
        FriendInvite friendInvite = friendInviteMapper.findByInviteNo(inviteNo);
        if (friendInvite==null){
            throw new GrilException("未查询到好友邀请信息");
        }
        return friendInvite;
    }

    /**
     * 分页查询好友列表
     * @param userNo    用户编码
     * @param start     开始位置
     * @param size      页面长度
     * @return
     */
    @Override
    public ResponseData queryPageFriends(String userNo, int start, int size) {
        ResponseData responseData = new ResponseData();
        List<Map<String,String>> queryUsers = friendInviteMapper.queryPageFriends(userNo, start, size,SysCode.CODE_10150002.getCode());
        responseData.setData(queryUsers);
        //获取总的好友人数
        responseData.setTotalCount(friendInviteMapper.countByUserNoAndInvite(userNo,SysCode.CODE_10150002.getCode()));
        return responseData;
    }

    /**
     * 根据用户编码和好友编码和状态查询数据
     * @param userNo        用户编码
     * @param friendUserNo  好友编码
     * @param inviteState   状态
     * @return
     */
    @Override
    public FriendInvite findByUserNoAndFriendUserNoAndInviteState(String userNo, String friendUserNo, String inviteState) {
        FriendInvite byUserNoAndFriendUserNoAndInviteState = friendInviteMapper.findByUserNoAndFriendUserNoAndInviteState(userNo, friendUserNo, inviteState);
        return byUserNoAndFriendUserNoAndInviteState;
    }

    /**
     * 分页检索好友用户
     * @param userNo    用户编码
     * @param content   检索内容
     * @param start     开始位置
     * @param size      索取长度
     * @return
     */
    @Override
    public ResponseData queryPageSearchFriendUser(String userNo, String content, int start, int size) {
        ResponseData responseData = new ResponseData();
        List<Map<String,Object>> queryUsers = friendInviteMapper.queryPageSearchFriendUser(userNo, content, start, size, SysCode.CODE_10150002.getCode());
        //查询当前检索者与检索用户是否是好友
        queryUsers.forEach(queryUser -> {
            //如果搜索到自己 默认为false 因为不能加自己为好友
            if (queryUser.get("userNo").equals(userNo)) {
                queryUser.put("isokFriend",true);
            } else {
                FriendInvite friendInvite = iFriendInviteService.findByUserNoAndFriendUserNoAndInviteState(userNo, (String) queryUser.get("userNo"), SysCode.CODE_10150002.getCode());
                if (friendInvite != null) {
                    queryUser.put("isokFriend",true);
                } else {
                    queryUser.put("isokFriend",false);
                }
            }
        });
        responseData.setData(queryUsers);
        return responseData;
    }

    /**
     * 删除好友
     *
     * @param inviteNo 申请编码
     * @return
     */
    @Override
    public ResponseData writeDelFriend(String inviteNo) {
        //获取好友申请信息
        FriendInvite byInviteNo = findByInviteNo(inviteNo);
        FriendInvite exists = friendInviteMapper.findByUserNoAndFriendUserNoAndInviteState(byInviteNo.getFriendUserNo(), byInviteNo.getUserNo(), SysCode.CODE_10150002.getCode());
        if (exists!=null){
            friendInviteMapper.delByInviteNo(byInviteNo.getInviteNo());
            friendInviteMapper.delByInviteNo(exists.getInviteNo());
        }
        //删除环信好友信息
        boolean deleteFriendSingle = IMUser.deleteFriendSingle(byInviteNo.getUserNo(), byInviteNo.getFriendUserNo());
        if (!deleteFriendSingle){
            throw new GrilException("删除环信好友失败");
        }
        return BaseResp.getInstance();
    }
}
