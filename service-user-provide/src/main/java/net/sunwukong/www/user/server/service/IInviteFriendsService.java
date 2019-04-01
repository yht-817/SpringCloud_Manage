package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.util.Map;

/**
 * 说明:邀请好友服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/23 17:13
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
public interface IInviteFriendsService {

    /**
     * 短信邀请好友
     * @param map
     * @return
     */
    ResponseData smsInvite(Map<String,String> map);

    /**
     * 接受邀请
     * @param userNo        邀请者的用户编码
     * @param account       接受邀请者的账号
     * @param accountType   账号类型
     * @return
     */
    ResponseData acceptInvitation(String userNo, String account,String accountType);


}
