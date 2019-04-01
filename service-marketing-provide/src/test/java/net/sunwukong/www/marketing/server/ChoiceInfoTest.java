package net.sunwukong.www.marketing.server;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.ChoiceInfo;
import net.sunwukong.www.marketing.server.service.IChoiceInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ChoiceInfoTest
 * @Author: kangdong
 * @Date: 2018/7/12 下午5:52
 * @Description:
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChoiceInfoTest {

    @Autowired
    IChoiceInfoService iChoiceInfoService;
}
