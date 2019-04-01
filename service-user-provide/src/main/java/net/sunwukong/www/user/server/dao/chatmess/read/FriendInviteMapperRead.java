package net.sunwukong.www.user.server.dao.chatmess.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.FriendInvite;
import org.apache.ibatis.annotations.Param;

public interface FriendInviteMapperRead extends BaseMapper<FriendInvite>{

    int countByUserNoAndFriendUserNoAndinviteState(@Param("userNo") String userNo,
                                                   @Param("friendUserNo") String friendUserNo,
                                                   @Param("inviteState") String inviteState);
}