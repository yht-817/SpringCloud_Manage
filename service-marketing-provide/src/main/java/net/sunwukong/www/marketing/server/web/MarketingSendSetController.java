package net.sunwukong.www.marketing.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: MarketingSendSetController
 * @Author: kangdong
 * @Date: 2018/6/20 下午3:50
 * @Description: 营销赠送设置控制层
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@RestController
@RequestMapping(value = "/marketingsendset")
@Api(tags = "营销赠送设置", description = "营销赠送设置服务")
public class MarketingSendSetController extends BaseController {

    //营销赠送设置信息
    @RequestMapping(value="/getRegisterSendWuKongCoin",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "注册时获取平台赠送悟空币", httpMethod = "POST", notes = "获取平台赠送悟空币",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public ResponseData getNowMarketingSendSet() {
        return iMarketingSendSetService.getNowMarketingSendSet();
    }
}
