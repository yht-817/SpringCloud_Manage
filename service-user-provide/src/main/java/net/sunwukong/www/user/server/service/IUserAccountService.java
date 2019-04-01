package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserAccount;

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
     * 根据用户编码获取账户信息
     * @param userNo    用户编码
     * @return
     */
    ResponseData getallCoin(String userNo);

    /**
     * 添加用户账户信息
     * @param userAccount
     * @return
     */
    ResponseData addUserAccount(UserAccount userAccount);

    /**
     * 获取个人账户余额信息
     * @param userNo    用户编码
     * @return
     */
    UserAccount getUserAccount(String userNo);

    /**
     * 更新用户账户信息
     * @param userAccount
     * @return
     */
    ResponseData updateUserAccount(UserAccount userAccount);
}
