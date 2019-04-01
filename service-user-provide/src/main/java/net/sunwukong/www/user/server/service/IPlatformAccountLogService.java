package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.marketing.bean.PlatformAccountLog;

/**
 * 说明:平台账户变动日志服务接口
 *
 * @author Mick
 * CreateDate 2018/7/4/004 0:52
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IPlatformAccountLogService {
    /**
     * 添加平台账户变动日志
     * @param platformAccountLog
     * @return
     */
    RequestData addPlatformAccountLog(PlatformAccountLog platformAccountLog);
}
