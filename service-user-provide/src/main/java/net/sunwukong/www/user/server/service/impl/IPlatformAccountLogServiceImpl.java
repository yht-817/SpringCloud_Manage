package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.PlatformAccountLog;
import net.sunwukong.www.user.server.dao.marketing.write.PlatformAccountLogMapperWrite;
import net.sunwukong.www.user.server.service.IPlatformAccountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 说明:平台账户变动日志服务接口实现
 *
 * @author Mick
 * CreateDate 2018/7/4/004 1:33
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IPlatformAccountLogServiceImpl implements IPlatformAccountLogService {

    @Autowired
    PlatformAccountLogMapperWrite platformAccountLogMapperWrite;

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public RequestData addPlatformAccountLog(PlatformAccountLog platformAccountLog) {
        RequestData requestData = new RequestData();
        platformAccountLog.setId(DataBaseTool.createId());
        platformAccountLog.setChangeDate(new Date());
        int i = platformAccountLogMapperWrite.insert(platformAccountLog);
        if (i<=0){
            throw new GrilException("添加平台账户变动日志失败");
        }
        return requestData;
    }
}
