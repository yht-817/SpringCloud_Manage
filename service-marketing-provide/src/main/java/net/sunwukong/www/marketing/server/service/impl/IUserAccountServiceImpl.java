package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.PlatformAccountLog;
import net.sunwukong.www.marketing.bean.UserAccount;
import net.sunwukong.www.marketing.bean.UserAccountLog;
import net.sunwukong.www.marketing.server.dao.UserAccountMapper;
import net.sunwukong.www.marketing.server.service.IUserAccountService;
import net.sunwukong.www.marketing.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 说明:用户账户服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/5 14:33
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserAccountServiceImpl extends BaseController implements IUserAccountService {

    @Autowired
    UserAccountMapper userAccountMapper;

    @Override
    public UserAccount getUserAccount(String userNo) {
        UserAccount byUserNoAccount = userAccountMapper.findByUserNoAccount(userNo);
        return byUserNoAccount;
    }

    @Override
    public ResponseData updateUserAccount(Map<String, String> data) {
        //获取用户账户信息
        UserAccount userAccount = getUserAccount(data.get("userNo"));
        if (userAccount==null){
            userAccount = new UserAccount();
            userAccount.setUserNo(data.get("userNo"));
            userAccount = addUserAccount(userAccount);
        }
        //1修改普通余额 2修改冻结金额
        if ("1".equals(data.get("type"))){
            userAccount.setAccountAmount(
                    new BigDecimal(
                            userAccount.getAccountAmount().intValue()+new BigDecimal(data.get("amount")).intValue()));
        }else {
            userAccount.setForzenAccountAmount(
                    new BigDecimal(userAccount.getForzenAccountAmount().intValue()+new BigDecimal(data.get("amount")).intValue()));
        }
        int i = userAccountMapper.updateByPrimaryKeySelective(userAccount);
        if (i<=0){
            throw new GrilException("更新账户余额失败");
        }
        //添加用户日志变动记录
        UserAccountLog userAccountLog = new UserAccountLog();
        userAccountLog.setUserNo(userAccount.getUserNo());
        userAccountLog.setChangeAmount(new BigDecimal(data.get("amount")));
        userAccountLog.setChangeMode(data.get("changeMode"));
        userAccountLog.setChangeNo(data.get("changeNo"));
        userAccountLog.setChangeRemark(data.get("changeRemark"));
        iUserAccountLogService.addUserAccountLog(userAccountLog);
        return BaseResp.getInstance();
    }

    @Override
    public boolean updateUserAccount(UserAccount userAccount) {
        int i = userAccountMapper.updateByPrimaryKey(userAccount);
        if (i<=0){
            throw new GrilException("修改用户账户失败");
        }
        return true;
    }

    /**
     * 分发用户蟠桃
     * @param userNo    用户编码
     * @param money     金额
     * @param isCash    是否可提现
     * @param sysCode   变动模式
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public boolean writePayPeach(String userNo, BigDecimal money, boolean isCash, SysCode sysCode) {
        //1.查询用户账户信息
        UserAccount demandAccount = iUserAccountService.getUserAccount(userNo);
        //2.修改用户账户的体现金额
        if (isCash){
            demandAccount.setCashAccountAmount(demandAccount.getCashAccountAmount().add(money));
        }else {
            demandAccount.setAccountAmount(demandAccount.getAccountAmount().add(money));
        }

        iUserAccountService.updateUserAccount(demandAccount);
        //3.更新平台日志
        PlatformAccountLog platformAccountLog = new PlatformAccountLog();
        platformAccountLog.setChangeNo(DataBaseTool.createNo("dh"));
        platformAccountLog.setChangeMode(sysCode.getCode());
        platformAccountLog.setChangeRemark(sysCode.getMsg());
        platformAccountLog.setChangeAmount(money.negate());
        platformAccountLog.setUserNo(userNo);
        platformAccountLog.setChangeDate(new Date());
        iPlatformAccountLogService.insertPlatformAccountLog(platformAccountLog);
        /*if (isCash){
            //4.更新用户可提现账户日志
            UserCashAccountLog userCashAccountLog = new UserCashAccountLog();
            userCashAccountLog.setChangeNo("SQTX");
            userCashAccountLog.setChangeDate(new Date());
            userCashAccountLog.setChangeMode(sysCode.getCode());
            userCashAccountLog.setChangeRemark(sysCode.getMsg());
            userCashAccountLog.setChangeAmount(money);
            userCashAccountLog.setUserNo(userNo);
            iUserCashAccountLogService.addUserCashAccountLog(userCashAccountLog);
        }else {
            UserAccountLog userAccountLog = new UserAccountLog();
            userAccountLog.setChangeNo("dh");
            userAccountLog.setChangeDate(new Date());
            userAccountLog.setChangeMode(sysCode.getCode());
            userAccountLog.setChangeRemark(sysCode.getMsg());
            userAccountLog.setChangeAmount(money);
            userAccountLog.setUserNo(userNo);
        }*/
        UserAccountLog userAccountLog = new UserAccountLog();
        userAccountLog.setChangeNo("dh");
        userAccountLog.setChangeDate(new Date());
        userAccountLog.setChangeMode(sysCode.getCode());
        userAccountLog.setChangeRemark(sysCode.getMsg());
        userAccountLog.setChangeAmount(money);
        userAccountLog.setUserNo(userNo);
        iUserAccountLogService.addUserAccountLog(userAccountLog);
        return true;

    }

    @Override
    public UserAccount addUserAccount(UserAccount userAccount) {
        userAccount.setId(DataBaseTool.createId());
        userAccount.setForzenAccountAmount(new BigDecimal(0));
        userAccount.setAccountAmount(new BigDecimal(0));
        int insert = userAccountMapper.insert(userAccount);
        if (insert<=0){
            throw new GrilException("添加用户账户信息失败");
        }
        return userAccount;
    }
}
