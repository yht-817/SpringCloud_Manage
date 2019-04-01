package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.server.dao.UserCityMapper;
import net.sunwukong.www.marketing.server.service.IUserCityService;
import net.sunwukong.www.marketing.server.web.BaseController;
import net.sunwukong.www.user.bean.UserCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 说明:用户服务城市服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/23 13:50
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserCityServiceImpl implements IUserCityService {

    @Autowired
    UserCityMapper userCityMapper;

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserCity(UserCity userCity) {
        ResponseData responseData = new ResponseData();
        userCity.setId(DataBaseTool.createId());
        int i = userCityMapper.insert(userCity);
        if (i<=0){
            throw new GrilException("添加用户服务城市失败");
        }
        return responseData;
    }
}
