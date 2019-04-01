package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.bean.City;
import net.sunwukong.www.user.bean.UserCity;
import net.sunwukong.www.user.server.dao.usercenter.read.UserCityMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserCityMapperWrite;
import net.sunwukong.www.user.server.service.IUserCityService;
import net.sunwukong.www.user.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明:用户服务城市服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/23 13:50
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserCityServiceImpl extends BaseController implements IUserCityService {

    @Autowired
    UserCityMapperWrite userCityMapperWrite;

    @Autowired
    UserCityMapperRead userCityMapperRead;

    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserCity(UserCity userCity) {
        ResponseData responseData = new ResponseData();
        userCity.setId(DataBaseTool.createId());
        int i = userCityMapperWrite.insert(userCity);
        if (i<=0){
            throw new GrilException("添加用户服务城市失败");
        }
        return responseData;
    }

    public ResponseData getUserCity(String userNo) {
        ResponseData responseData = new ResponseData();
        List<UserCity> userCityList = userCityMapperRead.findByUserNo(userNo);
        List<City> cities = new ArrayList<>();
        userCityList.forEach(userCity -> {
            cities.add((City) iCityService.getCity(userCity.getCityNo()).getData());
        });
        responseData.setData(cities);
        return responseData;
    }

    public ResponseData findByUserNo(String userNo) {
        ResponseData responseData = new ResponseData();
        List<UserCity> byUserNo = userCityMapperRead.findByUserNo(userNo);
        responseData.setData(byUserNo);
        return responseData;
    }
}
