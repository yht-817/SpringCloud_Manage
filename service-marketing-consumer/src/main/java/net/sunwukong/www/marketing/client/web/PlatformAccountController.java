package net.sunwukong.www.marketing.client.web;

import io.swagger.annotations.Api;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: PlatformAccountController
 * @Author: kangdong
 * @Date: 2018/6/21 上午10:8
 * @Description: 平台账户金额控制类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@RestController
@RequestMapping("/v1/platformaccount")
@Api(tags = "平台账户金额模块", description = "（平台账户金额服务）")
public class PlatformAccountController extends BaseController {

    /**
     * 获取平台账户金额
     * @return
     */
    @RequestMapping(value="/getPlatformAccount",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    public ResponseData getPlatformAccount() {
        return platformAccountService.getPlatformAccount();
    }

}
