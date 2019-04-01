package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.SysCode;
import net.sunwukong.www.marketing.server.dao.SysCodeMapper;
import net.sunwukong.www.marketing.server.service.ISysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISysCodeServiceImpl implements ISysCodeService {
    @Autowired
    SysCodeMapper sysCodeMapper;

    @Override
    public ResponseData getInformationClass() {
        ResponseData responseData = new ResponseData();
        List<SysCode> byCodeType = sysCodeMapper.findByCodeType("1022");
        responseData.setData(byCodeType);
        return responseData;
    }
}
