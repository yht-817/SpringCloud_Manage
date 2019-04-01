package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserCategory;

/**
 * 说明:用户服务类目服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/23 14:38
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IUserCategoryService {
    /**
     * 添加用户服务类目
     * @param userCategory
     * @return
     */
    ResponseData addUserCategory(UserCategory userCategory);

    /**
     * 根据编码获取用户服务类目
     * @param userNo
     * @return
     */
    ResponseData getUserCategory(String userNo);

    /**
     * 根据用户编码获取服务类目
     * @param userNo
     * @return
     */
    ResponseData findByUserNo(String userNo);
}
