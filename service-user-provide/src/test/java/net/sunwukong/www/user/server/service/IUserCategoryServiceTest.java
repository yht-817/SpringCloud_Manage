package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserCategoryServiceTest {

    @Autowired
    IUserCategoryService IUserCategoryService;

    /*@Test
    public void getUserCategory() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("userNo", "619732628b20f2a8ca80cb06a4dc4b74");
        ResponseData userCategory = IUserCategoryService.getUserCategory(map);
        System.out.println(userCategory.toString());
    }*/

}