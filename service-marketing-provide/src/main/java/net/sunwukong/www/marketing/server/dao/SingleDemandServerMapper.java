package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.DemandInfo;

import java.util.List;
import java.util.Map;

/**
 * 说明:个体服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/27 15:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface SingleDemandServerMapper extends BaseMapper<DemandInfo> {
    /**
     * 获取个体服务列表
     * @param map
     * @return
     */
    List<Map<String,Object>> findByUserNoAndServerStateList(Map<String, String> map);

    /**
     * 获取服务详情
     * @param data
     * @return
     */
    Map<String,Object> findByDemandServerDetails(Map<String, String> data);
}
