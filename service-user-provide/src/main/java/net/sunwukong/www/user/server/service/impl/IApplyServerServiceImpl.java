package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.user.bean.*;
import net.sunwukong.www.user.server.service.IApplyServerService;
import net.sunwukong.www.user.server.web.BaseController;
import net.sunwukong.www.user.vo.ServerDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明:我要抢单服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/23 13:36
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IApplyServerServiceImpl extends BaseController implements IApplyServerService {

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData saveServerDetails(ServerDetails data) {
        ResponseData responseData = new ResponseData();
        //保存服务类目
        List<UserCategory> userCategories = data.getUserCategories();
        userCategories.forEach(userCategory -> {
            iUserCategoryService.addUserCategory(userCategory);
        });
        //保存所在城市
        List<UserCity> userCitys = data.getUserCitys();
        userCitys.forEach(userCity -> {
            iUserCityService.addUserCity(userCity);
        });
        //保存服务城市
        List<UserScope> userScopes = data.getUserScopes();
        userScopes.forEach(userScope -> {
            iUserScopeService.addUserScope(userScope);
        });
        //修改用户身份信息
        UserInfo userInfo = iUserInfoService.findByUserNo(data.getUserNo());
        userInfo.setUserType(data.getUserType());
        iUserInfoService.updateUserInfo(userInfo);
        return responseData;
    }

    @Override
    public ResponseData getServerDetails(String userNo) {
        ResponseData responseData = new ResponseData();
        //获取用户服务类目信息
        ResponseData<List<UserCategory>> userCategoryServiceByUserNo = iUserCategoryService.findByUserNo(userNo);
        //获取服务城市信息
        ResponseData<List<UserCity>> cityServiceByUserNo = iUserCityService.findByUserNo(userNo);
        ServerDetails serverDetails = new ServerDetails();
        serverDetails.setUserCategories(userCategoryServiceByUserNo.getData());
        serverDetails.setUserCitys(cityServiceByUserNo.getData());
        responseData.setData(serverDetails);
        return responseData;
    }
}
