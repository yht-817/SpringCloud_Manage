package net.sunwukong.www.chat.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.bean.FriendGroupUser;

import java.util.List;

/**
 * 说明:好友群用户信息服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/9 16:12
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IFriendGroupUserService {
    /**
     * 退出群聊
     * @param userNo    用户编码
     * @param groupNo   群聊编码
     * @return
     */
    ResponseData writeExit(String userNo,String groupNo);

    /**
     * 分页获取群用户列表
     * @param groupNo   群编码
     * @param content   检索内容
     * @param start     开始位置
     * @param size      检索长度
     * @return
     */
    ResponseData queryPageGroupUsers(String groupNo,String content,int start,int size);

    /**
     * 分页获取用户群列表
     * @param userNo    用户编码
     * @param start     开始位置
     * @param size      检索长度
     * @return
     */
    ResponseData queryPageUserGroups(String userNo,int start,int size);

    /**
     * 添加邀请信息
     * @param friendGroupUser   邀请对象
     * @return
     */
    ResponseData addFriendGroupUser(FriendGroupUser friendGroupUser);

    /**
     * 审核
     * @param inviteNo      申请编码
     * @param auditState    审核状态
     * @return
     */
    ResponseData writeAudit(String inviteNo,boolean auditState);

    /**
     * 根据在群状态获取用户是否在群里面
     * @param groupNo           群编码
     * @param userNo            用户编码
     * @param groupUserState    在群状态
     * @return
     */
    boolean findByGroupNoAndUserNoExists(String groupNo, String userNo, String groupUserState);

    /**
     * 获取群成员数量
     * @param groupNo   群编码
     * @return
     */
    int readCountByGroupNo(String groupNo);

    /**
     * 删除群成员
     * @param userNo    群主编码
     * @param groupNo   群编码
     * @param userNos   用户编码列表
     * @return
     */
    ResponseData writeDelGroupUser(String userNo, String groupNo, List<String> userNos);
}
