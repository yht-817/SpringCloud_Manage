package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.bean.UserOtherLogin;
import net.sunwukong.www.user.server.dao.usercenter.read.UserOtherLoginMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserOtherLoginMapperWrite;
import net.sunwukong.www.user.server.service.IUserOtherLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 说明:第三方登录服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/29 16:30
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserOtherLoginServiceImpl implements IUserOtherLoginService {

    @Autowired
    UserOtherLoginMapperRead userOtherLoginMapperRead;

    @Autowired
    UserOtherLoginMapperWrite userOtherLoginMapperWrite;

    @Override
    public ResponseData getUserOtherBindList(String userNo) {
        ResponseData responseData = new ResponseData();
        List<UserOtherLogin> byUserNo = userOtherLoginMapperRead.findByUserNo(userNo);
        responseData.setData(byUserNo);
        return responseData;
    }

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserOtherLogin(UserOtherLogin userOtherLogin) {
        ResponseData responseData = new ResponseData();
        userOtherLogin.setId(DataBaseTool.createId());
        userOtherLogin.setOtherAppDate(new Date());
        int i = userOtherLoginMapperWrite.insert(userOtherLogin);
        if (i<=0){
            throw new GrilException("添加第三方绑定失败");
        }
        return responseData;
    }

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData removeUserOtherBind(String id) {
        ResponseData responseData = new ResponseData();
        //通过绑定ID查询绑定信息
        UserOtherLogin userOtherLogin = userOtherLoginMapperRead.selectByPrimaryKey(id);
        if (userOtherLogin!=null){
            int i = userOtherLoginMapperWrite.deleteByPrimaryKey(id);
            if (i<=0){
                throw new GrilException("解除绑定失败");
            }
        }else {
            throw new GrilException("未查询到相关绑定信息");
        }

        return responseData;
    }

    @Override
    public ResponseData findByOtherAppId(String otherAppId) {
        ResponseData responseData = new ResponseData();
        UserOtherLogin byOtherAppId = userOtherLoginMapperRead.findByOtherAppId(otherAppId);
        /*if (byOtherAppId==null){
            throw new GrilException("当前ID,未查询到第三方绑定信息");
        }*/
        responseData.setData(byOtherAppId);
        return responseData;
    }

    @Override
    public UserOtherLogin findByOtherAppIdAndOtherAppType(String otherAppId, String otherAppType) {
        UserOtherLogin byOtherAppIdAndOtherAppType = userOtherLoginMapperRead.findByOtherAppIdAndOtherAppType(otherAppId, otherAppType);
        return byOtherAppIdAndOtherAppType;
    }
}
