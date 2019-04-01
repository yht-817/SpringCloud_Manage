package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.ChoiceApply;
import net.sunwukong.www.marketing.server.dao.ChoiceApplyMapper;
import net.sunwukong.www.marketing.server.service.IChoiceApplyService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IChoiceApplyServiceImpl
 * @Author: kangdong
 * @Date: 2018/7/12 下午5:13
 * @Description: 申请精选实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class IChoiceApplyServiceImpl implements IChoiceApplyService {

    private static final Logger log = LoggerFactory.getLogger(IChoiceInfoServiceImpl.class);

    @Autowired
    ChoiceApplyMapper choiceApplyMapper;



    /**
     * 申请我要上精选
     * @param data
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addApply(Map<String,String> data) {
        ResponseData responseData = new ResponseData();
        try {
            ChoiceApply choiceApply = new ChoiceApply();
            choiceApply.setId(DataBaseTool.createId());
            choiceApply.setApplyNo(DataBaseTool.createNo("SQJX"));
            choiceApply.setUserNo(data.get("userNo"));
            choiceApply.setMerchantContract(data.get("name"));
            choiceApply.setMerchantPhone(data.get("phone"));
            choiceApply.setApplyDate(new Date());

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(choiceApplyMapper.insert(choiceApply));
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }
}
