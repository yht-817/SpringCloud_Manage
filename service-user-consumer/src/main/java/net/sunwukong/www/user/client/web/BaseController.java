package net.sunwukong.www.user.client.web;

import net.sunwukong.www.user.client.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 说明:公用Controller
 *
 * @author Mick
 * CreateDate 2018/6/9/009 22:15
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Controller
@CrossOrigin
public class BaseController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    SecurityCodeService securityCodeService;

    @Autowired
    UserHomePageService userHomePageService;

    @Autowired
    InformationClickLogService informationClickLogService;

    @Autowired
    DemandServerService demandServerService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountLogService userAccountLogService;

    @Autowired
    RechargeSetMealService rechargeSetMealService;

    @Autowired
    UserDataPhotoService userDataPhotoService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CityService cityService;

    @Autowired
    ApplyServerService applyServerService;

    @Autowired
    InviteFriendsService inviteFriendsService;

    @Autowired
    UserCityService userCityService;

    @Autowired
    UserCategoryService userCategoryService;

    @Autowired
    UserSettingService userSettingService;

    @Autowired
    ServiceScopeOneService serviceScopeOneService;

    @Autowired
    FileService fileService;

    @Autowired
    MobileVersionService mobileVersionService;

    @Autowired
    UserReflectsService userReflectsService;

}
