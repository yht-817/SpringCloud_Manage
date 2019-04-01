package net.sunwukong.www.user.server.service;

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
    ResponseData addUserScope(UserScope userScope);
}
