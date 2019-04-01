package net.sunwukong.www.marketing.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sdkinfo.www.http.HttpRequest;
import net.sunwukong.www.api.crypto.symmetric.AES128UtilBase64;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.JSONUtil;
import net.sunwukong.www.marketing.server.api.ServiceUrl;
import net.sunwukong.www.marketing.server.service.IDemandInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: DemandInfoTest
 * @Author: kangdong
 * @Date: 2018/6/19 下午10:52
 * @Description: 需求信息测试类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
public class DemandInfoTest {

    @Autowired
    IDemandInfoService iDemandInfoService;

    /**
     * 发布需求测试
     */
    /*@Test
    public void publishDemandInfo() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("userNo", "100000000");
        data.put("categoryOneNo", "10001");
        data.put("categoryOneName", "娱乐");
        data.put("categoryTwoNo", "10001001");
        data.put("categoryTwoName", "打牌");
        data.put("cityNo", "A1001");
        data.put("cityName", "成都");
        data.put("specialRemark", "一起来玩");
        ResponseData responseData = iDemandInfoService.insertDemandInfo(data);
        responseData.getCode();
        System.out.println("==========" + responseData.getCode());
    }*/

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("userNo","140f4c1aad139fbe69f04fc56906b198");
        RequestData requestData = new RequestData();
        requestData.setUserNo("140f4c1aad139fbe69f04fc56906b198");
        requestData.setData(AES128UtilBase64.encryptAES(JSONUtil.map2json(map)));
        HttpRequest httpRequest = HttpRequest.post(ServiceUrl.GETUSERACCOUNT).body(JSONUtil.bean2json(requestData)).contentType("application/json");
        JSONObject jsonObject = JSON.parseObject(httpRequest.execute().body());
        if (jsonObject.getInteger("code")!= StatusEnum.SUCCESS.getCode()){
            throw new GrilException("获取用户个人账户失败");
        }
        Object o = jsonObject.getObject("data", Map.class).get("allCoin");
        BigDecimal account = new BigDecimal(String.valueOf(o));
        System.out.println(account);
    }

}
