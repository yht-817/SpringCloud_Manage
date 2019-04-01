package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserCity;

/**
 * 说明:用户服务城市服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/23 13:50
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IUserCityService {

    /**
     * 添加用户服务城市信息
     * @param userCity
     * @return
     */
    ResponseData addUserCity(UserCity userCity);

    /**
     * 通过用户编码获取服务城市
     * @param userNo
     * @return
     */
    ResponseData getUserCity(String userNo);

    /**
     * 根据用户编码获取服务城市
     * @param userNo
     * @return
     */
    ResponseData findByUserNo(String userNo);
}
