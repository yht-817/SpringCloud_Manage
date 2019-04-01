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
 * 说明:用户服务城市控制
 *
 * @author Mick
 * @CreateDate 2018/6/24 12:59
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/usercity")
@Api(tags = "用户所在城市控制", description = "")
public class UserCityController extends BaseController {

    @RequestMapping(value = "/getusercity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "根据用户编码获取用户所在城市", httpMethod = "POST", notes = "根据用户编码获取用户所在城市", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query",defaultValue = ""),
    })
    public ResponseData getUserCity(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userCityService.getUserCity(requestData);
    }
}
