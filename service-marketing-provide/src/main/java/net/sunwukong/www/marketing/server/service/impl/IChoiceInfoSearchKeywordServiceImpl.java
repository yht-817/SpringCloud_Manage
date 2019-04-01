package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.marketing.bean.ChoiceInfoSearchKeyword;
import net.sunwukong.www.marketing.server.dao.ChoiceInfoSearchKeywordMapper;
import net.sunwukong.www.marketing.server.service.IChoiceInfoSearchKeywordService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IChoiceInfoSearchKeywordServiceImpl
 * @Author: kangdong
 * @Date: 2018/7/23 下午5:13
 * @Description: 关键词实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class IChoiceInfoSearchKeywordServiceImpl implements IChoiceInfoSearchKeywordService {

    private static final Logger log = LoggerFactory.getLogger(IChoiceInfoSearchKeywordServiceImpl.class);

    @Autowired
    ChoiceInfoSearchKeywordMapper choiceInfoSearchKeywordMapper;

    /**
     * 推荐热门关键词列表
     * @return
     */
    @Override
    public ResponseData getHotList() {
        ResponseData responseData = new ResponseData();
        try {
            List<ChoiceInfoSearchKeyword> list = choiceInfoSearchKeywordMapper.getHotList();
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(list);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }
}
