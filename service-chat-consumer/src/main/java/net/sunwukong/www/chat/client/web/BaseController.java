package net.sunwukong.www.chat.client.web;

import net.sunwukong.www.chat.client.service.IChatServiceMessageService;
import net.sunwukong.www.chat.client.service.IFriendGroupService;
import net.sunwukong.www.chat.client.service.IFriendInviteService;
import net.sunwukong.www.chat.client.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 说明:公用Base
 *
 * @author Mick
 * @CreateDate 2018/7/9 14:47
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
public class BaseController {

    @Autowired
    public IFriendGroupService iFriendGroupService;

    @Autowired
    public IFriendInviteService iFriendInviteService;

    @Autowired
    public IChatServiceMessageService iChatServiceMessageService;

    @Autowired
    public IUserInfoService iUserInfoService;
}
