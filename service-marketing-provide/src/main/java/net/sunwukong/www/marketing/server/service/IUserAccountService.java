package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.marketing.bean.UserAccount;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 说明:用户账户服务接口
 *
 * @author Mick
 * CreateDate 2018/6/15 15:06
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IUserAccountService {
    /**
     * 修改用户资金
     * userNo       用户编码
     * amount       变动金额
     * type         变动字段 1 普通金额 2 冻结金额
     * changeMode   变动方式 10050001 获得奖励 10050002
     * changeRemark 说明

     * @param data
     * @return
     */
    ResponseData updateUserAccount(Map<String, String> data);


    /**
     * 根据用户编码获取用户账户信息
     * @param userNo    用户编码
     * @return
     */
    UserAccount getUserAccount(String userNo);

    /**
     * 添加用户账户信息
     * @param userAccount
     * @return
     */
    UserAccount addUserAccount(UserAccount userAccount);

    /**
     * 更新用户账户信息
     * @param demandAccount
     * @return
     */
    boolean updateUserAccount(UserAccount demandAccount);

    /**
     * 分发用户蟠桃
     * @param userNo    用户编码
     * @param money     金额
     * @param isCash    是否可提现
     * @param sysCode   变动模式
     * @return
     */
    boolean writePayPeach(String userNo, BigDecimal money, boolean isCash, SysCode sysCode);
}
