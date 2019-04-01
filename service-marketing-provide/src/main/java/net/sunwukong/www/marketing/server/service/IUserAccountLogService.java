package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.UserAccountLog;

/**
 * 说明:账户金额变动服务接口
 *
 * @author Mick
 * CreateDate 2018/6/15 17:34
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IUserAccountLogService {

    /**
     * 添加用户资金变动记录
     * @param userAccountLog    用户资金变动对象
     * @return
     */
    ResponseData addUserAccountLog(UserAccountLog userAccountLog);
}
