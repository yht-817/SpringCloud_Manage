package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.marketing.bean.MarketingSendSet;
import net.sunwukong.www.marketing.server.dao.MarketingSendSetMapper;
import net.sunwukong.www.marketing.server.service.IMarketingSendSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: MarketingSendSetServiceImpl
 * @Author: kangdong
 * @Date: 2018/6/20 下午4:02
 * @Description: 营销赠送设置实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class IMarketingSendSetServiceImpl implements IMarketingSendSetService {

    @Autowired
    MarketingSendSetMapper marketingSendSetMapper;

    /**
     * 根据时间和类型获取所在时间段的赠送设置
     * @return
     */
    @Override
    public ResponseData getMarketingSendSetByDateAndType(Map<String,Object> map) {
        ResponseData responseData = new ResponseData();
        try {
            MarketingSendSet marketingSendSet = marketingSendSetMapper.getMarketingSendSetByDateAndType(map);
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(marketingSendSet);
        } catch (Exception e) {
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    /**
     * 注册赠送
     * @return
     */
    @Override
    public ResponseData getNowMarketingSendSet() {
        ResponseData responseData = new ResponseData();
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("nowDate",new Date());
            map.put("sendType",SysCode.CODE_10050003.getCode());

            MarketingSendSet marketingSendSet = marketingSendSetMapper.getMarketingSendSetByDateAndType(map);
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(marketingSendSet);
        } catch (Exception e) {
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }
}
