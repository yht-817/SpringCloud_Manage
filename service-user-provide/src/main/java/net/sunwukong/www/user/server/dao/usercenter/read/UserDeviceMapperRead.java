package net.sunwukong.www.user.server.dao.usercenter.read;


import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.UserDevice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDeviceMapperRead extends BaseMapper<UserDevice> {
    /**
     * 根据用户编码查询设备对象
     * @param userNo
     * @return
     */
    UserDevice findByUserNo(String userNo);


}