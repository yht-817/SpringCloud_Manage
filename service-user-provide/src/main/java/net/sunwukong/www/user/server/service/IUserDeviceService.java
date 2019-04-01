package net.sunwukong.www.user.server.service;


import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserDevice;

public interface IUserDeviceService {

    /**
     * 添加设备信息
     * @param userDevice    待保存对象
     * @return              返回响应对象
     */
    ResponseData saveDevice(UserDevice userDevice);

    /**
     * 查询设备信息是否存在
     * @param userNo    用户编码
     * @return
     */
    UserDevice findByUserNo(String userNo);

    /**
     * 更新设备信息
     * @param userDevice    待更新对象
     * @return              返回响应对象
     */
    ResponseData updateDevice(UserDevice userDevice);

}