package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.ChoiceUserPay;
import net.sunwukong.www.marketing.server.dao.ChoiceUserPayMapper;
import net.sunwukong.www.marketing.server.service.IChoiceUserPayService;
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
 * @FileName: IChoiceUserPayServiceImpl
 * @Author: kangdong
 * @Date: 2018/7/13 下午4:12
 * @Description: 用户购买券时纪录实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class IChoiceUserPayServiceImpl implements IChoiceUserPayService {

    private static final Logger log = LoggerFactory.getLogger(IChoiceUserPayServiceImpl.class);

    @Autowired
    ChoiceUserPayMapper choiceUserPayMapper;


    /**
     * 添加用户购买券的支付纪录
     * @param data
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addChoiceUserPay(Map<String,String> data) {
        ResponseData responseData = new ResponseData();
        try {
            ChoiceUserPay choiceUserPay = new ChoiceUserPay();
            choiceUserPay.setId(DataBaseTool.createId());
            choiceUserPay.setResourceNo(data.get("resourceNo"));
            choiceUserPay.setUserNo(data.get("userNo"));
            choiceUserPay.setPayNo(data.get("payNo"));
            choiceUserPay.setPayAmount(new BigDecimal(data.get("payAmount")));
            choiceUserPay.setChoiceType("");
            choiceUserPay.setPayState(data.get("payState"));
            choiceUserPay.setPayDate(new Date());

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(choiceUserPayMapper.insertSelective(choiceUserPay));
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }
}