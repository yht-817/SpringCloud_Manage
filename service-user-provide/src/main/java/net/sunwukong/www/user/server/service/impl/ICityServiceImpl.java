package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.City;
import net.sunwukong.www.user.server.dao.usercenter.read.CityMapperRead;
import net.sunwukong.www.user.server.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明:城市服务接口实现
 *
 * @author Mick
 * CreateDate 2018/6/22/022 23:22
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class ICityServiceImpl implements ICityService {

    @Autowired
    CityMapperRead cityMapperRead;

    @Override
    public ResponseData getCityList() {
        ResponseData responseData = new ResponseData();
        List<City> cities = cityMapperRead.queryAll();
        responseData.setData(cities);
        return responseData;
    }

    @Override
    public ResponseData getCity(String cityNo) {
        ResponseData responseData = new ResponseData();
        City byCity = cityMapperRead.findByCity(cityNo);
        responseData.setData(byCity);
        return responseData;
    }
}
