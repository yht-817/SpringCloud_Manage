package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.user.bean.UserDevice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserDeviceServiceTest {

    @Autowired
    IUserDeviceService iUserDeviceService;

    @Test
    public void saveDevice() {
        UserDevice userDevice = new UserDevice();
        userDevice.setUserNo("111");
        userDevice.setPhoneType("111");
        userDevice.setDeviceNo("888");
        ResponseData responseData = iUserDeviceService.saveDevice(userDevice);
        assertEquals(StatusEnum.SUCCESS.getCode(),responseData.getCode());

    }
}