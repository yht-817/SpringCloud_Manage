package net.sunwukong.www.marketing.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: PlatformAccountLogController
 * @Author: kangdong
 * @Date: 2018/6/16 下午4:48
 * @Description: 平台账户变动日志控制层
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@RestController
@RequestMapping(value = "/platformaccountlog")
@Api(tags = "平台账户变动日志", description = "平台账户变动日志服务")
public class PlatformAccountLogController extends BaseController {

    @RequestMapping(value="/insertPlatformAccountLog",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "新增平台账户变动日志", httpMethod = "POST", notes = "新增平台账户变动日志",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "changeMode", value = "变动方式",paramType = "query"),
            @ApiImplicitParam(name = "changeAmount", value = "变动金额",paramType = "query"),
            @ApiImplicitParam(name = "changeNo", value = "变动单号",paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "对应用户",paramType = "query"),
            @ApiImplicitParam(name = "changeRemark", value = "变动说明",paramType = "query"),

    })
    public ResponseData insertPlatformAccountLog(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return null;
    }
}
