package net.sunwukong.www.chat.server.web;

import net.sunwukong.www.chat.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 说明:公用Base
 *
 * @author Mick
 * @CreateDate 2018/7/9 15:00
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Controller
public class BaseController {

    @Autowired
    public IFriendGroupService iFriendGroupService;

    @Autowired
    public IFriendGroupUserService iFriendGroupUserService;

    @Autowired
    public IGroupLabelService iGroupLabelService;

    @Autowired
    public IUserInfoService iUserInfoService;

    @Autowired
    public IFriendInviteService iFriendInviteService;

    @Autowired
    public IChatServiceMessageService iChatServiceMessageService;
}
