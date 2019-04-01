package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.InformationClickLog;
import net.sunwukong.www.marketing.server.dao.InformationClickLogMapper;
import net.sunwukong.www.marketing.server.service.IInformationClickLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 说明:资讯信息点击服务实现
 *
 * @author Mick
 * CreateDate 2018/6/13/013 14:24
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IInformationClickLogServiceImpl implements IInformationClickLogService {

    @Autowired
    InformationClickLogMapper informationClickLogMapper;

    @Override
    public ResponseData addUserFans(InformationClickLog informationClickLog) {
        ResponseData responseData = new ResponseData();
        informationClickLog.setId(DataBaseTool.createId());
        informationClickLog.setClickDate(new Date());
        int i = informationClickLogMapper.insertSelective(informationClickLog);
        if (i<=0){
            responseData.setStatus(StatusEnum.FAIL);
        }
        return responseData;
    }
}
