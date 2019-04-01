package net.sunwukong.www.marketing.client.web;

import net.sunwukong.www.marketing.client.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 说明:公用controller
 *
 * @author Mick
 * CreateDate 2018/6/13/013 14:39
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
public class BaseController {
    @Autowired
    InformationClickLogService informationClickLogService;

    @Autowired
    DemandServerService demandServerService;

    @Autowired
    DemandInfoService demandInfoService;

    @Autowired
    PlatformAccountService platformAccountService;

    @Autowired
    CityMangerApplyService cityMangerApplyService;

    @Autowired
    CollectionInfoService collectionInfoService;

    @Autowired
    DemandEvaluateService demandEvaluateService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    SingleDemandServerService singleDemandServerService;

    @Autowired
    UserApplyService userApplyService;

    @Autowired
    ReleaseDemandService releaseDemandService;

    @Autowired
    ChoiceApplyService choiceApplyService;

    @Autowired
    ChoiceInfoService choiceInfoService;

    @Autowired
    ChoiceUserService choiceUserService;

    @Autowired
    InformationService informationService;

    @Autowired
    InformationEvaluateService informationEvaluateService;

    @Autowired
    InformationEvaluatePariseService informationEvaluatePariseService;

    @Autowired
    InformationReplyService informationReplyService;

    @Autowired
    ChoiceInfoSearchKeywordService choiceInfoSearchKeywordService;
}
