package net.sunwukong.www.marketing.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: PlatformAccountController
 * @Author: kangdong
 * @Date: 2018/6/20 下午3:8
 * @Description: 平台账户金额控制类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@RestController
@RequestMapping(value = "/platformaccount")
@Api(tags = "平台账户金额", description = "平台账户金额服务")
public class PlatformAccountController extends BaseController {

    /**
     * 获取平台账户金额
     * @return
     */
    @RequestMapping(value="/getPlatformAccount",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.GET)
    @ApiOperation(value = "获取平台账户金额", httpMethod = "GET", notes = "获取平台账户金额",response = ResponseData.class)
    public ResponseData getPlatformAccount() {
        return iPlatformAccountService.getPlatformAccount();
    }
}
