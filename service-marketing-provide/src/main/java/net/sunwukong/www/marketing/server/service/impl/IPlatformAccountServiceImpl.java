package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.PlatformAccount;
import net.sunwukong.www.marketing.bean.PlatformAccountLog;
import net.sunwukong.www.marketing.server.dao.MarketingSendSetMapper;
import net.sunwukong.www.marketing.server.dao.PlatformAccountLogMapper;
import net.sunwukong.www.marketing.server.dao.PlatformAccountMapper;
import net.sunwukong.www.marketing.server.service.IPlatformAccountLogService;
import net.sunwukong.www.marketing.server.service.IPlatformAccountService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: PlatformAccountServiceImpl
 * @Author: kangdong
 * @Date: 2018/6/20 下午2:32
 * @Description: 平台账户金额控制类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class IPlatformAccountServiceImpl implements IPlatformAccountService {

    private static final Logger log = LoggerFactory.getLogger(IPlatformAccountServiceImpl.class);

    @Autowired
    PlatformAccountMapper platformAccountMapper;

    @Autowired
    PlatformAccountLogMapper platformAccountLogMapper;

    @Autowired
    MarketingSendSetMapper marketingSendSetMapper;

    @Autowired
    IPlatformAccountLogService iPlatformAccountLogService;

    @Autowired
    IPlatformAccountService iPlatformAccountService;


    /**
     * 获取平台账户金额
     * @return
     */
    @Override
    public ResponseData getPlatformAccount() {
        ResponseData responseData = new ResponseData();
        try {
            PlatformAccount platformAccount = platformAccountMapper.getPlatformAccount();
            if(null != platformAccount) {
                responseData.setData(platformAccount.getAccountAmount());
            }
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    /**
     * 更新平台账户信息表
     * @param platformAccount
     * @return
     */
    @Override
    public ResponseData updatePlatformAccount(PlatformAccount platformAccount) {
        ResponseData responseData = new ResponseData();
        try {
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(platformAccountMapper.updateByPrimaryKey(platformAccount));
        } catch (Exception e) {
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    /**
     * 平台减金额时，判断余额是否不足
     * @param changeAmount
     * @return
     */
    public boolean isNotSufficient(BigDecimal changeAmount) {
        //查询平台金额表，获取平台总金额
        PlatformAccount platformAccount = platformAccountMapper.getPlatformAccount();
        if(null == platformAccount) {//表示没有设置平台金额
            return false;
        }
        //平台金额 < 赠送金额
        BigDecimal accountAmount =  platformAccount.getAccountAmount();
        if(accountAmount.compareTo(changeAmount) == -1) {
            return false;
        }
        return true;
    }




    /**
     * 平台账户入账(+)
     * 变更平台账户及账户日志纪录
     * @param map
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData updatePlatformAccountIncome(Map<String,String> map) {
        ResponseData responseData = new ResponseData();
        try {
            //查询平台金额表，获取平台总金额
            PlatformAccount pAccount = platformAccountMapper.getPlatformAccount();
            //获取变动金额
            String amount = map.get("amount");
            //变动平台账户日志纪录（-）
            PlatformAccountLog platformLog = new PlatformAccountLog();
            platformLog.setId(DataBaseTool.createId());
            platformLog.setUserNo(map.get("userNo"));
            platformLog.setChangeNo(map.get("changeNo"));//变更单号
            platformLog.setChangeMode(map.get("changeMode"));
            platformLog.setChangeRemark(map.get("changeRemark"));
            platformLog.setChangeAmount(new BigDecimal(amount));
            platformLog.setChangeDate(new Date());
            int i = platformAccountLogMapper.insert(platformLog);
            if (i<=0){
                throw new GrilException("新增纪录失败");
            }

            //变动平台账户金额
            pAccount.setAccountAmount(pAccount.getAccountAmount().add(new BigDecimal(amount)));

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(platformAccountMapper.updateByPrimaryKey(pAccount));
        }catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }



    /**
     * 平台账户支出(-)
     * 变更平台账户及账户日志纪录
     * @param map
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData updatePlatformAccountPay(Map<String,String> map) {
        ResponseData responseData = new ResponseData();
        try {
            //查询平台金额表，获取平台总金额
            PlatformAccount pAccount = platformAccountMapper.getPlatformAccount();

            //获取变动金额
            String amount = map.get("amount");
            //判断余额支出是否充足
            if(pAccount.getAccountAmount().compareTo(new BigDecimal(amount)) == -1) {
                responseData.setCode(StatusEnum.FAIL.getCode());
                responseData.setMessage("平台金额余额不足");
                responseData.setData(0);
                return responseData;
            }

            //变动平台账户日志纪录（-）
            PlatformAccountLog platformLog = new PlatformAccountLog();
            platformLog.setId(DataBaseTool.createId());
            platformLog.setUserNo(map.get("userNo"));
            platformLog.setChangeNo(map.get("changeNo"));//变更单号
            platformLog.setChangeMode(map.get("changeMode"));
            platformLog.setChangeRemark(map.get("changeRemark"));
            BigDecimal acount = new BigDecimal(amount);
            platformLog.setChangeAmount(new BigDecimal(-acount.intValue()));
            platformLog.setChangeDate(new Date());
            int i = platformAccountLogMapper.insert(platformLog);
            if (i<=0){
                throw new GrilException("新增纪录失败");
            }

            //变动平台账户金额
            pAccount.setAccountAmount(pAccount.getAccountAmount().subtract(new BigDecimal(amount)));

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(platformAccountMapper.updateByPrimaryKey(pAccount));
        }catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }
}
