package net.sunwukong.www.chat.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.bean.FriendGroup;

import java.util.List;
import java.util.Map;

/**
 * 说明:好友群信息服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/9 15:01
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IFriendGroupService {

    /**
     * 创建群聊
     * @param data  群聊对象
     * @return
     */
    ResponseData addFriendGroup(FriendGroup data);

    /**
     * 解散群聊
     * @param groupNo   群编码
     * @return
     */
    ResponseData writeDissolve(String groupNo);

    /**
     * 退出群聊
     * @param userNo    用户编码
     * @param groupNo   群聊编码
     * @return
     */
    ResponseData writeExit(String userNo,String groupNo);

    /**
     * 邀请加入群聊
     * @param userNo            邀请方的用户编码
     * @param userNos           用户编码
     * @param groupNo           群聊编码
     * @return
     */
    ResponseData writeInviteJoin(String userNo, List<Map<String,String>> userNos, String groupNo);

    /**
     * 申请加入群聊
     * @param userNo            用户编码
     * @param groupNo           群聊编码
     * @return
     */
    ResponseData writeApplyJoin(String userNo,String groupNo);

    /**
     * 修改群资料
     * @param groupNo   群编码
     * @param groupName 群名称
     * @param groupNode 群公告
     * @param groupHead 群头像
     * @return
     */
    ResponseData updateFriendGroup(String groupNo,String groupName,String groupNode,String groupHead);

    /**
     * 设置群是否收费
     * @param groupNo       群编码
     * @param ifPayGroup    是否收费
     * @param payAmount     收费金额
     * @return
     */
    ResponseData updateGroupSetting(String groupNo,String ifPayGroup,String payAmount);

    /**
     * 分页获取群用户list
     * @param groupNo   群编码
     * @param content   检索内容
     * @param start     当前页
     * @param size      页面长度
     * @return
     */
    ResponseData queryPageGroupUsers(String groupNo,String content,int start,int size);

    /**
     * 根据群编码查询群信息
     * @param groupNo   群编码
     * @return
     */
    FriendGroup findByGroupNo(String groupNo);

    /**
     * 分页获取用户群列表
     * @param userNo    用户编码
     * @param start     当前页
     * @param size      页面长度
     * @return
     */
    ResponseData queryPageUserGroups(String userNo,int start,int size);

    /**
     * 获取标签列表
     * @return
     */
    ResponseData queryLabels();

    /**
     * 分页检索群
     * @param userNo    用户编码
     * @param content   检索内容
     * @param start     开始位置
     * @param size      索取长度
     * @return
     */
    ResponseData queryPageSearchGroup(String userNo,String content,int start,int size);

    /**
     * 分页检索
     * @param userNo    用户编码
     * @param content   检索内容
     * @param type      检索类型
     * @param start     开始位置
     * @param size      页面长度
     * @return
     */
    ResponseData queryPageSearch(String userNo,String content, String type,int start,int size);

    /**
     * 获取群详情
     * @param groupNo   群编码
     * @param userNo    用户编码
     * @return
     */
    ResponseData queryGroupDetails(String groupNo,String userNo);

    /**
     * 判断是否是群主
     * @param groupNo   群编码
     * @param userNo    用户编码
     * @return
     */
    boolean queryJudgeIsokOwner(String groupNo,String userNo);

    /**
     * 分页检索邀请群成员列表
     * @param userNo    用户编码
     * @param groupNo   群编码
     * @param content   检索内容
     * @param pageNo    开始位置
     * @param pageSize  页面长度
     * @return
     */
    ResponseData queryPageSearchInviteUser(String userNo, String groupNo, String content, int pageNo, int pageSize);

    /**
     * 删除群成员
     * @param userNo    群主编码
     * @param groupNo   群聊编码
     * @param userNos   用户编码对象
     * @return
     */
    ResponseData writeDelGroupUser(String userNo, String groupNo, List<Map<String,String>> userNos);
}
