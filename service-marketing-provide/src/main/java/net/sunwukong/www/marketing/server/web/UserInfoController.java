package net.sunwukong.www.marketing.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: UserInfoController
 * @Author: kangdong
 * @Date: 2018/6/16 下午2:36
 * @Description: 运营库用户信息控制层
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RestController
@RequestMapping(value = "/user")
@Api(tags = "运营模块用户信息", description = "用户服务提供")
public class UserInfoController extends BaseController {

    @RequestMapping(value="/getuser",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取用户信息", httpMethod = "POST", notes = "获取用户信息",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public ResponseData getUsere(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iUserInfoServer.getUserByUserNo(data.get("userNo"));
    }

    @RequestMapping(value="/getusermsgisokperfect",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "检验用户信息是否完善", httpMethod = "POST", notes = "检验用户信息是否完善",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public ResponseData getUserMsgIsokPerfect(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return iUserInfoServer.getUserMsgIsokPerfect(requestData.getData().get("userNo"));
    }
}
