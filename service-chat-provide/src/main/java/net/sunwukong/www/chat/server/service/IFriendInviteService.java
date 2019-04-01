package net.sunwukong.www.chat.server.service;


import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.bean.FriendInvite;

/**
 * 说明:好友邀请服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/9 16:57
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IFriendInviteService {
    /**
     * 添加邀请
     * @param friendInvite  好友关系对象
     * @return
     */
    ResponseData addInvite(FriendInvite friendInvite);

    /**
     * 添加好友关系信息
     * @param friendInvite  好友关系对象
     * @return
     */
    ResponseData addFriendInvite(FriendInvite friendInvite);

    /**
     * 审核邀请
     * @param inviteNo      申请编码
     * @param inviteState   审核状态(邀请中:10150001 同意:10150002 拒绝:10150003)
     * @return
     */
    ResponseData witerAuditFriendInvite(String inviteNo,String inviteState);

    /**
     * 根据申请编码查询邀请信息
     * @param inviteNo    申请编码
     * @return
     */
    FriendInvite findByInviteNo(String inviteNo);

    /**
     * 分页查询好友列表
     * @param userNo    用户编码
     * @param start     开始位置
     * @param size      页面长度
     * @return
     */
    ResponseData queryPageFriends(String userNo,int start,int size);

    /**
     * 根据用户编码和好友编码和状态查询数据
     * @param userNo        用户编码
     * @param friendUserNo  好友编码
     * @param inviteState   状态
     * @return
     */
    FriendInvite findByUserNoAndFriendUserNoAndInviteState(String userNo, String friendUserNo, String inviteState);

    /**
     * 分页检索好友用户
     * @param userNo    用户编码
     * @param content   检索内容
     * @param start     开始位置
     * @param size      索取长度
     * @return
     */
    ResponseData queryPageSearchFriendUser(String userNo, String content, int start, int size);

    /**
     * 删除好友信息
     * @param inviteNo  申请编码
     * @return
     */
    ResponseData writeDelFriend(String inviteNo);
}
