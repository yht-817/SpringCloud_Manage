package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.ServiceScopeOne;
import net.sunwukong.www.user.server.dao.usercenter.read.ServiceScopeOneMapperRead;
import net.sunwukong.www.user.server.service.IServiceScopeOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 岳虹廷 on  2018/7/3
 * <p>
 * 地点服务范围的一对多的实现
 */
@Service
public class IServiceScopeOneServiceImpl implements IServiceScopeOneService {

    @Autowired
    ServiceScopeOneMapperRead serviceScopeOneMapperRead;

    /**
     * 到数据库进行全部的数据进行查询
     */
    public ResponseData getCityMore() {
        ResponseData responseData = new ResponseData();
        List<ServiceScopeOne> citylist = serviceScopeOneMapperRead.getAllList();
        responseData.setData(citylist);
        return responseData;
    }

    /**
     * 发布城市列表
     */
    public ResponseData getreleaseMore() {
        ResponseData responseData = new ResponseData();
        List<ServiceScopeOne> citylist = serviceScopeOneMapperRead.getreleaseAllList();
        responseData.setData(citylist);
        return responseData;
    }
}
