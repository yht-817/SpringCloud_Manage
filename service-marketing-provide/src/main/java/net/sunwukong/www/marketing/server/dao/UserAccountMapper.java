package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.UserAccount;

public interface UserAccountMapper extends BaseMapper<UserAccount> {

    /**
     * 根据用户编码获取用户账户信息
     * @param userNo    用户编码
     * @return
     */
    UserAccount findByUserNoAccount(String userNo);
}