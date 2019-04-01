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
 * 说明:用户账户控制层
 *
 * @author Mick
 * CreateDate 2018/6/9/009 23:03
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/v1/useraccount")
@Api(tags = "用户账户控制", description = "")
public class UserAccountController extends BaseController{

    @RequestMapping(value = "/getuseraccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取个人账户信息", httpMethod = "POST", notes = "获取个人账户信息", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query",defaultValue = ""),
    })
    public ResponseData getUserAccount(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userAccountService.getallCoin(requestData);
    }
}
