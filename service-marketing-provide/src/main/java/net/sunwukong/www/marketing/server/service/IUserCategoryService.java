package net.sunwukong.www.marketing.server.service;

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
}
