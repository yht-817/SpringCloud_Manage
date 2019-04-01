package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.marketing.bean.DemandInfo;
import net.sunwukong.www.marketing.server.web.BaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IDemandInfoServiceTest extends BaseController{
    @Test
    public void insertDemandInfo() throws Exception {
        DemandInfo demandInfo = new DemandInfo();
        demandInfo.setUserNo("yh201807081034026925");
        demandInfo.setSpecialRemark("测试9");

        demandInfo.setCityNo("10001000");
        demandInfo.setCityName("纽约");

        demandInfo.setCategoryOneNo("10001");
        demandInfo.setCategoryOneName("出国咨询");
        demandInfo.setCategoryTwoNo("1000100002");
        demandInfo.setCategoryTwoName("移民服务");

        demandInfo.setScopeOneNo("201");
        demandInfo.setScopeOneName("中国");
        demandInfo.setScopeTwoNo("20101");
        demandInfo.setScopeTwoName("四川");

        demandInfo.setPayNum(new BigDecimal(20));
        demandInfo.setPhoneNo("18728578781");
        iDemandInfoService.insertDemandInfo(demandInfo);
    }

}