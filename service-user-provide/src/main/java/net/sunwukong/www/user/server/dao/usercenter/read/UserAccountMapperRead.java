package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.UserAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountMapperRead extends BaseMapper<UserAccount> {

    /**
     * 根据用户编码获取用户账户信息
     * @param userNo    用户编码
     * @return          返回账户信息
     */
    UserAccount findByUserNoAccount(String userNo);
}