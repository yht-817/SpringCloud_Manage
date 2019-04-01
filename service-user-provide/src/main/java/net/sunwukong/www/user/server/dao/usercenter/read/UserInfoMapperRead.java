package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author mick
 * @since 2018-06-06
 */
@Mapper
public interface UserInfoMapperRead extends BaseMapper<UserInfo> {
    /**
     * 根据账号查询对象
     * @param account   用户账号
     * @return          用户对象
     */
    UserInfo findByAccount(@Param(value = "account") String account);

    /**
     * 根据账号、密码查询用户对象
     * @param account   用户账号
     * @param password  用户密码
     * @return          用户对象
     */
    UserInfo findByAccountAndPassword(@Param(value = "account") String account,
                                      @Param(value = "password") String password);

    /**
     * 根据第三方ID查询用户对象
     * @param otherAppId
     * @return
     */
    UserInfo findByOtherAppId(@Param(value = "otherAppId") String otherAppId);

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    UserInfo findByIdInfo(String id);

    /**
     * 根据用户编码查询用户信息
     * @param userNo
     * @return
     */
    UserInfo findByUserNo(String userNo);

    /**
     * 根据用户userNo查询用户信息
     * @param userNo    用户编码
     * @return
     */
    UserInfo findByUserNoInfo(String userNo);
}
