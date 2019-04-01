package net.sunwukong.www.chat.server.dao;

import net.sunwukong.www.chat.bean.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoMapperTest {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    public void add(){
        UserInfo userInfo = new UserInfo();
        userInfo.setNikeName("dddd");
        userInfo.setPassWord("ddddd");
        userInfoMapper.insertSelective(userInfo);
    }

}