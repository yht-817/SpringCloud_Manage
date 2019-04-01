package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.marketing.bean.SysDemandDivide;

/**
 * 说明:分成服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/8 13:59
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface ISysDemandDivideService {
    /**
     * 获取分成配置信息
     * @return
     */
    SysDemandDivide selectOne();
}
