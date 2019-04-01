package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.marketing.bean.SysConfig;

/**
 * 说明:运营配置接口
 *
 * @author swk
 * @CreateDate 2018/8/30 10:24
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface ISysConfigService {

    /**
     * 根据代码查询配置信息
     * @param sysCode   配置代码
     * @return
     */
    SysConfig findBySysCode(String sysCode);
}
