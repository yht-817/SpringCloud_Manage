package net.sunwukong.www.user.server.service;

/**
 * 说明:用户好友服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/18 18:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IFriendInviteService {

    /**
     * 判断是否是好友关系
     * @param userNo    被访问者的编码
     * @param visitorNo 访问者的编码
     * @return
     */
    boolean judgeIsExistFriend(String userNo, String visitorNo);
}
