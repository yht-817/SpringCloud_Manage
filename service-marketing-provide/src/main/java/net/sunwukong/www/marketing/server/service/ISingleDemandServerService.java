package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.util.Map;

/**
 * 说明:个体服务控制服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/26 15:40
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
public interface ISingleDemandServerService {

    /**
     * 获取个体服务列表
     * @param map
     * @return
     */
    ResponseData getServerList(Map<String, String> map);

    /**
     * 我已完成服务
     * @param data
     * @return
     */
    ResponseData updateServerStatus(Map<String, String> data);

    /**
     * 获取服务列表详情
     * @param data
     * @return
     */
    ResponseData getServerDetails(Map<String, String> data);

    /**
     * 更新状态为待结单(我已完成服务)
     * @param data
     * @return
     */
    ResponseData updateServerStatusStayStatement(Map<String,String> data);
}
