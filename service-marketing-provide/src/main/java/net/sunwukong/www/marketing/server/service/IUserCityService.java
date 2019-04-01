package net.sunwukong.www.marketing.server.service;

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
}
