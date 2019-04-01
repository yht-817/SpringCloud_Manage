package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserScope;

/**
 * 说明:用户服务范围服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/3 15:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IUserScopeService {
    /**
     * 添加用户服务范围
     * @param userScope
     * @return
     */
    ResponseData addUserScope(UserScope userScope);
}
