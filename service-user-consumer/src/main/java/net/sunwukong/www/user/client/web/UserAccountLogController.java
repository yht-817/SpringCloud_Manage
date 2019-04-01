package net.sunwukong.www.user.client.web;

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
 * 说明:账户金币变动服务控制
 *
 * @author Mick
 * CreateDate 2018/6/15 18:09
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/v1/useraccountlog")
@Api(tags = "用户账户控制", description = "")
public class UserAccountLogController extends BaseController {

    @RequestMapping(value="/getyesterdaycoin",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取昨日金币", httpMethod = "POST", notes = "获取昨日金币",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = ""),
    })
    public ResponseData getYesterDayCoin(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return userAccountLogService.getYesterDayCoin(requestData);
    }

    @RequestMapping(value = "/getdetailspage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取资金变动明细", httpMethod = "POST", notes = "获取资金变动明细", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "显示条数", paramType = "query", defaultValue = "10"),
    })
    public ResponseData getDetailsPage(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {

        return userAccountLogService.getDetailsPage(requestData);
    }
}
