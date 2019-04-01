package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserDataPhoto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:用户资料图片服务接口
 *
 * @author Mick
 *         CreateDate 2018/6/15 17:34
 *         Email ：ideacoding@163.com
 *         Version 1.0
 **/
@FeignClient(value = "user-server-1",path = "/userdataphoto")
public interface UserDataPhotoService {
    /**
     * 添加用户资料图片
     * @param requestData 资料图片对象
     * @return
     */
    @RequestMapping(value = "/adduserdataphoto",method = RequestMethod.POST)
    ResponseData addUserDataPhoto(RequestData<UserDataPhoto> requestData);

    /**
     * 根据用户编码获取用户资料
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getuserdataphoto",method = RequestMethod.POST)
    ResponseData getUserDataPhoto(RequestData<Map<String,String>> requestData);
}
