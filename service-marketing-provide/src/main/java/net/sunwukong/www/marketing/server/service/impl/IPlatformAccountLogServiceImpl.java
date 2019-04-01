package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.PlatformAccount;
import net.sunwukong.www.marketing.bean.PlatformAccountLog;
import net.sunwukong.www.marketing.server.dao.PlatformAccountLogMapper;
import net.sunwukong.www.marketing.server.dao.PlatformAccountMapper;
import net.sunwukong.www.marketing.server.service.IPlatformAccountLogService;
import net.sunwukong.www.marketing.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IPlatformAccountLogServiceImpl
 * @Author: kangdong
 * @Date: 2018/6/22 下午2:02
 * @Description: 平台账户变更日志信息实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class IPlatformAccountLogServiceImpl extends BaseController implements IPlatformAccountLogService {

    @Autowired
    PlatformAccountLogMapper platformAccountLogMapper;

    @Autowired
    PlatformAccountMapper platformAccountMapper;

    /**
     * 新增平台账户变更日志
     * @param platformAccountLog
     * @return
     */
    @Override
    public ResponseData insertPlatformAccountLog(PlatformAccountLog platformAccountLog) {
        ResponseData responseData = new ResponseData();
        try {
            platformAccountLog.setId(DataBaseTool.createId());
            platformAccountLog.setChangeDate(new Date());
            int i = platformAccountLogMapper.insert(platformAccountLog);
            if (i<=0){
                throw new GrilException("添加平台账户日志失败");
            }
            //查询平台金额表，获取平台总金额
            PlatformAccount pAccount = platformAccountMapper.getPlatformAccount();
            //判断余额支出是否充足
            if(pAccount.getAccountAmount().add(platformAccountLog.getChangeAmount()).intValue()<0) {
                throw new GrilException("平台金额余额不足");
            }
            pAccount.setAccountAmount(pAccount.getAccountAmount().add(platformAccountLog.getChangeAmount()));
            platformAccountMapper.updateByPrimaryKeySelective(pAccount);
        } catch (Exception e) {
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }

    @Override
    public ResponseData updatePlatformAccountLog(PlatformAccountLog platformAccountLog) {
        ResponseData responseData = new ResponseData();
        platformAccountLog.setChangeDate(new Date());
        int i = platformAccountLogMapper.updateByPrimaryKeySelective(platformAccountLog);
        if (i<=0){
            throw new GrilException("修改平台账户日志失败");
        }
        return responseData;
    }
}
