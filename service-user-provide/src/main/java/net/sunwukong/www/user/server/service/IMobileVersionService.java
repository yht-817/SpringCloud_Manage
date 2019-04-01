package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;

/**
 * 说明:APP版本升级接口
 *
 * @author swk
 * @CreateDate 2018/8/20 17:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IMobileVersionService {

    /**
     * 获取APP版本信息
     * @param appName   app名称（ios android）
     * @param appType   app类型（测试 正式）
     * @param version   当前版本
     * @return
     */
    ResponseData getAppVersion(String appName,String appType,String version);
}
