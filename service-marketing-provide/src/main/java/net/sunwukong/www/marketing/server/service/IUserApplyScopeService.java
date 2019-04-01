package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.UserApplyScope;

import java.util.Map;

/**
 * 说明:个体/机构申请服务城市服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/3 13:05
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IUserApplyScopeService {
    /**
     * 添加用户服务城市
     * @param userApplyScope
     * @return
     */
    ResponseData addUserApplyScope(UserApplyScope userApplyScope);

    /**
     * 更新用户服务城市
     * @param userApplyScope
     * @return
     */
    ResponseData updateUserApplyScope(UserApplyScope userApplyScope);

    /**
     * 获取审核后的用户服务城市列表
     * @param map
     * @return
     */
    ResponseData getApplyNoAfterAuditScopeList(Map<String, String> map);
}
