package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserInfoServiceTest {
    @Autowired
    IUserInfoService iUserInfoService;

    @Test
    public void otherLogin() throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("sex","10010001");
        map.put("nikeName","测试登录");
        map.put("userHead","10010001");
        map.put("otherAppId","dqwed");
        map.put("otherAppName","微");
        map.put("phoneType","ios");
        map.put("deviceNo","暂无");
        iUserInfoService.otherLogin(map);
    }

}