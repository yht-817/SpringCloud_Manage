package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserDataPhoto;

import java.util.Map;

/**
 * 说明:用户资料图片服务接口
 *
 * @author Mick
 *         CreateDate 2018/6/15 17:34
 *         Email ：ideacoding@163.com
 *         Version 1.0
 **/
public interface IUserDataPhotoService {
    /**
     * 添加用户资料图片
     * @param userDataPhoto 资料图片对象
     * @return
     */
    ResponseData addUserDataPhoto(UserDataPhoto userDataPhoto);

    /**
     * 根据用户编码获取用户证件
     * @param map
     * @return
     */
    ResponseData getUserDataPhoto(Map<String,String> map);
}
