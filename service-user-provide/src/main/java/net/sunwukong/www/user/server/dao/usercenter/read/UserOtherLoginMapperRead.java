package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.UserOtherLogin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOtherLoginMapperRead extends BaseMapper<UserOtherLogin>{
    List<UserOtherLogin> findByUserNo(String userNo);

    UserOtherLogin findByOtherAppId(String otherAppId);

    /**
     * 根据第三方ID和第三方类型查询绑定信息
     * @param otherAppId    第三方ID
     * @param otherAppType  第三方类型
     * @return
     */
    UserOtherLogin findByOtherAppIdAndOtherAppType(@Param("otherAppId") String otherAppId,
                                                   @Param("otherAppType") String otherAppType);
}