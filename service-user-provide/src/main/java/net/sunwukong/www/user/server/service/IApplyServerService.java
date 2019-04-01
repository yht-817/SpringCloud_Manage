package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.vo.ServerDetails;

/**
 * 说明:我要抢单服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/23 13:32
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
public interface IApplyServerService {

    /**
     * 保存服务数据详情
     * @param data
     * @return
     */
    ResponseData saveServerDetails(ServerDetails data);

    /**
     * 根据用户编码获取服务详情
     * @param userNo
     * @return
     */
    ResponseData getServerDetails(String userNo);
}
