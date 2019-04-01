package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.bean.UserDataPhoto;
import net.sunwukong.www.user.server.dao.usercenter.read.UserDataPhotoMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.read.UserDeviceMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserDataPhotoMapperWrite;
import net.sunwukong.www.user.server.service.IUserDataPhotoService;
import net.sunwukong.www.user.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 说明:用户资料图片服务接口实现
 *
 * @author Mick
 * CreateDate 2018/6/15 15:08
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IUserDataPhotoServiceImpl extends BaseController implements IUserDataPhotoService {
    @Autowired
    UserDataPhotoMapperWrite userDataPhotoMapperWrite;
    @Autowired
    UserDeviceMapperRead userDeviceMapperRead;
    @Autowired
    UserDataPhotoMapperRead userDataPhotoMapperRead;

    /**
     * 添加用户资料图片
     * @param userDataPhoto 资料图片对象
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserDataPhoto(UserDataPhoto userDataPhoto) {
        ResponseData responseData = new ResponseData();
        userDataPhoto.setId(DataBaseTool.createId());
        //插入数据
        int i = userDataPhotoMapperWrite.insert(userDataPhoto);
        if (i<=0){
            throw new GrilException("添加用户资料失败");
        }
        return responseData;
    }

    @Override
    public ResponseData getUserDataPhoto(Map<String, String> map) {
        ResponseData responseData = new ResponseData();
        UserDataPhoto userNo = userDataPhotoMapperRead.findByUserNo(map.get("userNo"));
        responseData.setData(userNo);
        return responseData;
    }
}
