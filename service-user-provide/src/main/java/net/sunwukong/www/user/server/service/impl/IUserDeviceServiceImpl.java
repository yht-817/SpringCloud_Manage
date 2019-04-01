package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.bean.UserDevice;
import net.sunwukong.www.user.server.dao.usercenter.read.UserDeviceMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserDeviceMapperWrite;
import net.sunwukong.www.user.server.service.IUserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 说明:用户设备实现类
 *
 * @author Mick
 * CreateDate 2018/6/11 14:08
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IUserDeviceServiceImpl implements IUserDeviceService {

    @Autowired
    UserDeviceMapperRead userDeviceMapperRead;

    @Autowired
    UserDeviceMapperWrite userDeviceMapperWrite;

    /**
     * 保存用户设备信息
     * @param userDevice    待保存对象
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData saveDevice(UserDevice userDevice) {
        ResponseData responseData = new ResponseData();
        userDevice.setId(DataBaseTool.createId());
        userDevice.setLastLoginDate(new Date());
        int insert = userDeviceMapperWrite.insert(userDevice);
        if (insert<=0){
            throw new GrilException("保存用户设备信息失败");
        }
        return responseData;
    }

    /**
     * 查询当前用户的设备信息是否存在
     * @param userNo    用户编码
     * @return
     */
    @Override
    public UserDevice findByUserNo(String userNo) {
        UserDevice byUserNo = userDeviceMapperRead.findByUserNo(userNo);
        return byUserNo;
    }

    /**
     * 更新用户设备信息
     * @param userDevice    待更新对象
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updateDevice(UserDevice userDevice) {
        ResponseData responseData = new ResponseData();
        int i = userDeviceMapperWrite.updateByPrimaryKeySelective(userDevice);
        if (i <= 0) {
            responseData.setStatus(StatusEnum.FAIL);
            return responseData;
        }
        return responseData;
    }
}
