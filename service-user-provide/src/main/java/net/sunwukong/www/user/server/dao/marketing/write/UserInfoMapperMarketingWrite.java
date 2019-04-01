package net.sunwukong.www.user.server.dao.marketing.write;

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
public interface UserInfoMapperMarketingWrite extends BaseMapper<UserInfo> {

    /**
     * 更新头像
     * @param id        用户ID
     * @param userHead  用户头像地址
     * @return          返回受影响行
     */
    int updateUserHead(@Param("id")String id, @Param("userHead")String userHead);

    /**
     * 根据用户账号更新用户密码
     * @param account   用户账号（手机号、邮箱）
     * @param password  用户密码
     */
    int updaetByAccount(@Param("account") String account,@Param("password") String password);

    /**
     * 删除账号绑定
     * @param id        用户ID
     * @param fieldName 字段名称
     * @param fieldVal  字段值
     * @return
     */
    int removeBindAccount(@Param("id")String id,@Param("fieldName") String fieldName,@Param("fieldVal") String fieldVal);
}
