package net.sunwukong.www.marketing.server.web;

import net.sunwukong.www.marketing.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 说明:公用controller
 *
 * @author Mick
 * CreateDate 2018/6/13/013 14:39
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class BaseController {

    @Autowired
    public IUserInfoServer iUserInfoServer;

    @Autowired
    public IInformationClickLogService iInformationClickLogService;

    @Autowired
    public IDemandServerService iDemandServerService;

    @Autowired
    public IDemandInfoService iDemandInfoService;

    @Autowired
    ICityMangerApplyService iCityMangerApplyService;

    @Autowired
    IPlatformAccountService iPlatformAccountService;

    @Autowired
    IMarketingSendSetService iMarketingSendSetService;

    @Autowired
    ICollectionInfoService iCollectionInfoService;

    @Autowired
    public IDemandEvaluateService iDemandEvaluateService;

    @Autowired
    public ISingleDemandServerService iSingleDemandServerService;

    @Autowired
    public IUserApplyService iUserApplyService;

    @Autowired
    public IUserApplyCategoryService iUserApplyCategoryService;

    @Autowired
    public IUserApplyCityService iUserApplyCityService;

    @Autowired
    public IUserApplyPhotoService iUserApplyPhotoService;

    @Autowired
    public IUserApplyScopeService iUserApplyScopeService;

    @Autowired
    public IReleaseDemandService iReleaseDemandService;

    @Autowired
    public ISysUserGradeService iSysUserGradeService;

    @Autowired
    public IUserScopeService iUserScopeService;

    @Autowired
    public IUserCityService iUserCityService;

    @Autowired
    public IUserCategoryService iUserCategoryService;

    @Autowired
    public IUserAccountService iUserAccountService;

    @Autowired
    public IUserAccountLogService iUserAccountLogService;

    @Autowired
    public ISysDemandDivideService iSysDemandDivideService;

    @Autowired
    public IPlatformAccountLogService iPlatformAccountLogService;

    @Autowired
    IChoiceInfoService iChoiceInfoService;

    @Autowired
    IChoiceApplyService iChoiceApplyService;

    @Autowired
    IChoiceUserService iChoiceUserService;

    @Autowired
    IInformationService iInformationService;

    @Autowired
    IInformationEvaluateService iInformationEvaluateService;

    @Autowired
    public IInformationReplyService iInformationReplyService;

    @Autowired
    IInformationEvaluatePariseService iInformationEvaluatePariseService;

    @Autowired
    public IChatServiceMessageService iChatServiceMessageService;

    @Autowired
    IChoiceInfoSearchKeywordService iChoiceInfoSearchKeywordService;

    @Autowired
    public ISysCodeService iSysCodeService;

    @Autowired
    public IUserCashAccountLogService iUserCashAccountLogService;

    @Autowired
    public ISysConfigService iSysConfigService;
}
