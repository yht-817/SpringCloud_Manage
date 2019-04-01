package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.marketing.bean.UserCashAccountLog;

/**
 * 说明:用户体现账户日志
 *
 * @author swk
 * @CreateDate 2018/8/29 17:10
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IUserCashAccountLogService {

    /**
     * 保存用户可提现账户日志记录
     * @param userCashAccountLog
     * @return
     */
    boolean addUserCashAccountLog(UserCashAccountLog userCashAccountLog);
}
