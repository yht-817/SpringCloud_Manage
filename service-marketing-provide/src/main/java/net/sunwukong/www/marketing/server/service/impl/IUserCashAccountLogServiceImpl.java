package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.marketing.bean.UserCashAccountLog;
import net.sunwukong.www.marketing.server.dao.UserCashAccountLogMapper;
import net.sunwukong.www.marketing.server.service.IUserCashAccountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明:用户提现账户日志实现
 *
 * @author swk
 * @CreateDate 2018/8/29 17:14
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Service
public class IUserCashAccountLogServiceImpl implements IUserCashAccountLogService {

    @Autowired
    UserCashAccountLogMapper userCashAccountLogMapper;

    @Override
    public boolean addUserCashAccountLog(UserCashAccountLog userCashAccountLog) {
        userCashAccountLogMapper.insert(userCashAccountLog);
        return true;
    }
}
