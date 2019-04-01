package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserOtherLogin;

import java.util.Map;

/**
 * 说明:第三方登录服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/29 16:27
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IUserOtherLoginService {
    /**
     * 根据用户编码获取第三方绑定信息
     * @param userNo
     * @return
     */
    ResponseData getUserOtherBindList(String userNo);

    /**
     * 设置第三方绑定
     *
     * @param userOtherLogin
     * @return
     */
    ResponseData addUserOtherLogin(UserOtherLogin userOtherLogin);

    /**
     * 解除第三方绑定
     * @param id    第三方绑定ID
     * @return
     */
    ResponseData removeUserOtherBind(String id);

    /**
     * 根据第三方ID查询绑定信息
     * @param otherAppId    第三方ID
     * @return
     */
    ResponseData findByOtherAppId(String otherAppId);

    /**
     * 根据第三方ID和类型查询第三方信息
     * @param otherAppId
     * @param otherAppType
     * @return
     */
    UserOtherLogin findByOtherAppIdAndOtherAppType(String otherAppId, String otherAppType);
}
