package net.sunwukong.www.user.server.web;

import net.sunwukong.www.user.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 说明:公用controller
 *
 * @author Mick
 * CreateDate 2018/6/10/010 10:27
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Controller
public class BaseController {

    @Autowired
    public IUserInfoService iUserInfoService;

    @Autowired
    public ISecurityCodeService iSecurityCodeService;

    @Autowired
    public IUserDeviceService iUserDeviceService;

    @Autowired
    public IUserHomePageService iUserHomePageService;

    @Autowired
    public IUserHomePageVisitService iUserHomePageVisitService;

    @Autowired
    public IUserAccountService iUserAccountService;

    @Autowired
    public IUserAccountLogService iUserAccountLogService;

    @Autowired
    public IRechargeSetMealService iRechargeSetMealService;

    @Autowired
    public IPlatformAccountService iPlatformAccountService;

    @Autowired
    public IPlatformAccountLogService iPlatformAccountLogService;

    @Autowired
    public IUserDataPhotoService iUserDataPhotoService;

    @Autowired
    public ICategoryService iCategoryService;

    @Autowired
    public ICityService iCityService;

    @Autowired
    public IFileService iFileService;

    @Autowired
    public IApplyServerService iApplyServerService;

    @Autowired
    public IUserCityService iUserCityService;

    @Autowired
    public IUserCategoryService iUserCategoryService;

    @Autowired
    public IInviteFriendsService iInviteFriendsService;

    @Autowired
    public IUserSettingService iUserSettingService;

    @Autowired
    public ISysUserGradeService iSysUserGradeService;

    @Autowired
    public IUserOtherLoginService iUserOtherLoginService;
    @Autowired
    public IUserScopeService iUserScopeService;
	
	@Autowired
    public IServiceScopeOneService iServiceScopeOneService;

	@Autowired
    public IMarketingSendSetService iMarketingSendSetService;

	@Autowired
    public IFriendInviteService iFriendInviteService;

	@Autowired
    public InformationClickLogService informationClickLogService;

	@Autowired
    public IMobileVersionService iMobileVersionService;

    @Autowired
    public IUserReflects iUserReflects;
}
