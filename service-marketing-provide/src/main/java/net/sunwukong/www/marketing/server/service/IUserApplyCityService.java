package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.UserApplyCity;

import java.util.List;
import java.util.Map;

/**
 * 说明:个体/机构申请城市服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/28 17:00
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IUserApplyCityService {

    /**
     * 添加用户服务城市
     * @param userApplyCity
     * @return
     */
    ResponseData addUserApplyCity(UserApplyCity userApplyCity);

    /**
     * 根据申请编码获取服务城市
     * @param applyNo
     * @return
     */
    ResponseData getUserApplyCity(String applyNo);

    /**
     * 获取审核后的用户所在城市列表
     * @param map
     * @return
     */
    ResponseData<List<UserApplyCity>> getApplyNoAfterAuditCityList(Map<String, String> map);
}
