package net.sunwukong.www.user.server.service.impl;

import com.sdkinfo.www.log.StaticLog;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.marketing.bean.MarketingSendSet;
import net.sunwukong.www.marketing.bean.PlatformAccount;
import net.sunwukong.www.marketing.bean.PlatformAccountLog;
import net.sunwukong.www.user.bean.UserAccount;
import net.sunwukong.www.user.bean.UserAccountLog;
import net.sunwukong.www.user.server.dao.marketing.read.PlatformAccountMapperRead;
import net.sunwukong.www.user.server.dao.marketing.write.PlatformAccountMapperWrite;
import net.sunwukong.www.user.server.service.IPlatformAccountService;
import net.sunwukong.www.user.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 说明:平台账户金额服务接口实现
 *
 * @author Mick
 * CreateDate 2018/7/4/004 0:56
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IPlatformAccountServiceImpl extends BaseController implements IPlatformAccountService {
    @Autowired
    PlatformAccountMapperRead platformAccountMapperRead;

    @Autowired
    PlatformAccountMapperWrite platformAccountMapperWrite;

    /**
     * 更新平台金额信息
     * @param platformAccount
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updatePlatformAccount(PlatformAccount platformAccount) {
        int i = platformAccountMapperWrite.updateByPrimaryKeySelective(platformAccount);
        if (i<=0){
            throw new GrilException("更新平台金额失败");
        }
        return BaseResp.getInstance();
    }

    /**
     * 赠送蟠桃
     * @param userNo        用户编码
     * @param sendType      赠送类型
     * @param changeMode    资金变动方式
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData sendPeento(String userNo, SysCode sendType, SysCode changeMode) {
        ResponseData responseData = new ResponseData();
        //一.查询平台总金额
        List<PlatformAccount> platformAccounts = platformAccountMapperRead.queryAll();
        if (platformAccounts.size()<=0){
            StaticLog.error("未查询到平台金额");
            return responseData;
        }
        PlatformAccount platformAccount = platformAccounts.get(0);

        //二.获取营销表当前时间段赠送金额
        MarketingSendSet marketingSendSet = iMarketingSendSetService.findBySendTypeBetweenDate(new Date(),sendType.getCode());
        if (marketingSendSet==null){
            StaticLog.error("未查询到当前时间段内的营销套餐");
            return responseData;
        }
        //平台金额
        BigDecimal accountAmount = platformAccount.getAccountAmount();
        //赠送金额
        BigDecimal sendAmount = marketingSendSet.getSendAmount();
        //平台金额 < 赠送金额
        if(accountAmount.compareTo(sendAmount) == -1) {
            StaticLog.error("平台赠送金额不足");
            return responseData;
        }
        //变动平台金额
        platformAccount.setAccountAmount(accountAmount.subtract(sendAmount));
        updatePlatformAccount(platformAccount);

        //添加平台账户变动日志
        PlatformAccountLog platformAccountLog = new PlatformAccountLog();
        platformAccountLog.setUserNo(userNo);
        platformAccountLog.setChangeAmount(sendAmount.negate());
        platformAccountLog.setChangeMode(changeMode.getCode());
        platformAccountLog.setChangeNo(marketingSendSet.getSendNo());
        platformAccountLog.setChangeRemark(changeMode.getMsg());
        iPlatformAccountLogService.addPlatformAccountLog(platformAccountLog);

        //更新个人账户余额
        //1.查询个人账户余额
        UserAccount userAccount = iUserAccountService.getUserAccount(userNo);
        //2.赋值新的余额
        userAccount.setAccountAmount(userAccount.getAccountAmount().add(sendAmount));
        //3.执行修改语句
        iUserAccountService.updateUserAccount(userAccount);

        //添加个人账户变动日志
        UserAccountLog userAccountLog = new UserAccountLog();
        userAccountLog.setUserNo(userNo);
        userAccountLog.setChangeAmount(sendAmount);
        userAccountLog.setChangeMode(changeMode.getCode());
        userAccountLog.setChangeRemark(changeMode.getMsg());
        userAccountLog.setChangeNo(marketingSendSet.getSendNo());
        iUserAccountLogService.addUserAccountLog(userAccountLog);

        return responseData;
    }
}
