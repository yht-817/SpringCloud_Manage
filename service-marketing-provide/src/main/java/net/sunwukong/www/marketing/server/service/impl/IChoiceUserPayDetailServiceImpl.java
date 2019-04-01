package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.ChoiceUserPayDetail;
import net.sunwukong.www.marketing.server.dao.ChoiceUserPayDetailMapper;
import net.sunwukong.www.marketing.server.service.IChoiceUserPayDetailService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IChoiceUserPayDetailServiceImpl
 * @Author: kangdong
 * @Date: 2018/7/16 上午10:32
 * @Description: 支付记录详情信息
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class IChoiceUserPayDetailServiceImpl implements IChoiceUserPayDetailService {
    private static final Logger log = LoggerFactory.getLogger(IChoiceUserPayDetailServiceImpl.class);

    @Autowired
    ChoiceUserPayDetailMapper choiceUserPayDetailMapper;

    /**
     * 新增精选支付详情
     * @param data
     * @return
     */
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addChoiceUserPayDetail(Map<String,String> data) {
        ResponseData responseData = new ResponseData();
        try {
            ChoiceUserPayDetail info = new ChoiceUserPayDetail();
            info.setId(DataBaseTool.createId());
            info.setUserNo(data.get("userNo"));
            info.setPayNo(data.get("payNo"));
            info.setPayModeNo(data.get("payModeNo"));
            info.setPayAmount(new BigDecimal(data.get("payAmount")));

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(choiceUserPayDetailMapper.insertSelective(info));
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }
}
