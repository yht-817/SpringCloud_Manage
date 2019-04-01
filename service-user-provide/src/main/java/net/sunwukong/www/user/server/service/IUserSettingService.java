package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserOtherLogin;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:用户设置
 *
 * @author Mick
 * @CreateDate 2018/6/24 14:06
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
public interface IUserSettingService {

    /**
     * 绑定账号
     * @param map
     * @return
     */
    ResponseData bindAccount(Map<String,String> map);

    /**
     * 删除绑定
     * @param data
     * @return
     */
    ResponseData removeBindAccount(Map<String, String> data);

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
    ResponseData bindOtherLogin(UserOtherLogin userOtherLogin);

    /**
     * 解除第三方绑定
     * @param id    第三方绑定ID
     * @return
     */
    ResponseData removeOtherLogin(String id);
}
