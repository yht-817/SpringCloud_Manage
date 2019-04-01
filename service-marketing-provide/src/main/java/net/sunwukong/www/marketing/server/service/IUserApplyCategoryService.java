package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.UserApplyCategory;

import java.util.List;
import java.util.Map;

/**
 * 说明:个体/机构申请类目服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/28 16:53
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IUserApplyCategoryService {

    /**
     * 添加用户服务类目信息
     * @param userApplyCategory
     * @return
     */
    ResponseData addUserApplyCategory(UserApplyCategory userApplyCategory);

    /**
     * 根据申请编码获取服务类目
     * @param applyNo
     * @return
     */
    ResponseData getUserApplyCategory(String applyNo);

    /**
     * 获取审核后的用户服务类目列表(按照二级服务类目分组)
     * @param map
     * @return
     */
    ResponseData<List<UserApplyCategory>> getApplyNoAfterAuditCategoryList(Map<String, String> map);
}
