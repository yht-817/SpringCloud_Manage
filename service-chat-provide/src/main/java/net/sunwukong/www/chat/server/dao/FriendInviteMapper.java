package net.sunwukong.www.chat.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.chat.bean.FriendInvite;
import net.sunwukong.www.chat.vo.QueryUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FriendInviteMapper extends BaseMapper<FriendInvite> {

    /**
     * 分页查询用户好友列表
     * @param userNo        用户编码
     * @param start         开始位置
     * @param size          检索长度
     * @param inviteState   检索长度
     * @return
     */
    List<Map<String,String>> queryPageFriends(@Param("userNo") String userNo,
                               @Param("start")int start,
                               @Param("size")int size,
                               @Param("inviteState")String inviteState);

    FriendInvite findByUserNoAndFriendUserNoAndInviteState(@Param("userNo") String userNo,
                                                           @Param("friendUserNo") String friendUserNo,
                                                           @Param("inviteState") String inviteState);
    /**
     * 分页检索好友用户
     * @param userNo        用户编码
     * @param content       检索内容
     * @param start         开始位置
     * @param size          检索长度
     * @param inviteState   好友状态
     * @return  a.id,b.user_no,b.user_head,b.nike_name
     */
    List<Map<String,Object>> queryPageSearchFriendUser(@Param("userNo")String userNo,
                                              @Param("content")String content,
                                              @Param("start")int start,
                                              @Param("size")int size,
                                              @Param("inviteState")String inviteState);

    /**
     * 根据申请编码查询申请信息
     * @param inviteNo
     * @return
     */
    FriendInvite findByInviteNo(String inviteNo);

    /**
     * 根据申请编码删除好友信息
     * @param inviteNo
     * @return
     */
    int delByInviteNo(String inviteNo);

    /**
     * 获取总的好友人数
     * @param userNo        用户编码
     * @param inviteState   好友状态
     * @return
     */
    int countByUserNoAndInvite(@Param("userNo")String userNo,
                               @Param("inviteState")String inviteState);
}