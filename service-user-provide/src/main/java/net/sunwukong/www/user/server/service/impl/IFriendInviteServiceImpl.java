package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.user.server.dao.chatmess.read.FriendInviteMapperRead;
import net.sunwukong.www.user.server.service.IFriendInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明:用户好友服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/18 18:11
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IFriendInviteServiceImpl implements IFriendInviteService {

    @Autowired
    FriendInviteMapperRead friendInviteMapperRead;

    @Override
    public boolean judgeIsExistFriend(String userNo, String visitorNo) {
        if (userNo.equals(visitorNo)){
            return true;
        }
        int i = friendInviteMapperRead.countByUserNoAndFriendUserNoAndinviteState(userNo, visitorNo, SysCode.CODE_10150002.getCode());
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
}
