package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.UserApplyCity;
import net.sunwukong.www.marketing.server.dao.UserApplyCityMapper;
import net.sunwukong.www.marketing.server.service.IUserApplyCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 说明:个体/机构申请城市服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/28 17:01
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserApplyCityServiceImpl implements IUserApplyCityService {
    @Autowired
    UserApplyCityMapper userApplyCityMapper;

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserApplyCity(UserApplyCity userApplyCity) {
        ResponseData responseData = new ResponseData();
        userApplyCity.setId(DataBaseTool.createId());
        int i = userApplyCityMapper.insert(userApplyCity);
        if (i<=0){
            throw new GrilException("添加用户服务城市失败");
        }
        return responseData;
    }

    @Override
    public ResponseData getUserApplyCity(String applyNo) {
        ResponseData responseData = new ResponseData();
        List<UserApplyCity> byApplyNo = userApplyCityMapper.findByApplyNo(applyNo);
        responseData.setData(byApplyNo);
        return responseData;
    }

    @Override
    public ResponseData<List<UserApplyCity>> getApplyNoAfterAuditCityList(Map<String, String> map) {
        ResponseData responseData = new ResponseData();
        List<UserApplyCity> byApplyNo = userApplyCityMapper.findApplyNoAfterAuditCityList(map);
        responseData.setData(byApplyNo);
        return responseData;
    }
}
