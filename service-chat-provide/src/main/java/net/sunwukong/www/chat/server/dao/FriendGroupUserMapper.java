package net.sunwukong.www.chat.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.chat.bean.FriendGroupUser;
import net.sunwukong.www.chat.vo.GroupUsers;
import net.sunwukong.www.chat.vo.QueryGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendGroupUserMapper extends BaseMapper<FriendGroupUser>{
    /**
     * 根据用户编码和群编码 查询成员记录
     * @param userNo    用户编码
     * @param groupNo   群编码
     * @return
     */
    FriendGroupUser findByUserNoAndGroupNo(@Param("userNo") String userNo,
                                           @Param("groupNo") String groupNo);

    /**
     * 根据群编码查询群成员信息
     * @param groupNo   群编码
     * @return
     */
    List<FriendGroupUser> findByGroupNo(String groupNo);

    /**
     * 获取用户群列表
     * @param userNo            群编码
     * @param groupUserState    在群状态
     * @param groupState        群状态
     * @param start             开始位置
     * @param size              检索长度
     * @return
     */
    List<QueryGroup> queryPageUserGroups(@Param("userNo")String userNo,
                                         @Param("groupUserState")String groupUserState,
                                         @Param("groupState")String groupState,
                                         @Param("start")int start,
                                         @Param("size")int size);

    /**
     * 根据群编码分页查询群成员信息
     * @param groupNo           群编码
     * @param groupUserState    在群状态
     * @param content           检索内容
     * @param start             开始位置
     * @param size              检索长度
     * @return
     */
    List<GroupUsers> queryPageGroupUsers(@Param("groupNo")String groupNo,
                                         @Param("groupUserState")String groupUserState,
                                         @Param("content")String content,
                                         @Param("start")int start,
                                         @Param("size")int size);

    /**
     * 根据在群状态获取用户是否在群里面
     * @param groupNo           群编码
     * @param userNo            用户编码
     * @param groupUserState    在群状态
     * @return
     */
    FriendGroupUser findByGroupNoAndUserNoExists(@Param("groupNo")String groupNo,
                                                 @Param("userNo")String userNo,
                                                 @Param("groupUserState")String groupUserState);

    int countByGroupNoAndGroupUserState(@Param("groupNo")String groupNo,
                                        @Param("groupUserState")String groupUserState);


    /**
     * 通过申请编码查询申请信息
     * @param inviteNo  申请编码
     * @return
     */
    FriendGroupUser findByInviteNo(String inviteNo);

    /**
     * 删除群成员
     * @param groupNo   群编码
     * @param userNos   用户编码列表
     * @return
     */
    int delByGroupNoAndUserNos(@Param("groupNo")String groupNo,
                               @Param("list") List<String> userNos);
}