package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.PlatformAccountLog;

import java.util.Map;

/**
 * Created by tungkang on 2018/6/22.
 */
public interface IPlatformAccountLogService {

    /**
     * 新增账户变更日志表
     * @return
     */
    ResponseData insertPlatformAccountLog(PlatformAccountLog platformAccountLog);

    /**
     * 更新平台日志
     * @param platformAccountLog
     * @return
     */
    ResponseData updatePlatformAccountLog(PlatformAccountLog platformAccountLog);
}
