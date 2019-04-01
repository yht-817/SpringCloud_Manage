package net.sunwukong.www.chat.client.web;

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
 * 说明:用户控制
 *
 * @author Mick
 * @CreateDate 2018/7/11 16:59
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/v1/userinfo")
@Api(tags = "用户控制", description = "")
public class UserInfoController extends BaseController {

    @RequestMapping(value="/getuserid",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "根据用户编码获取用户ID", httpMethod = "POST", notes = "根据用户编码获取用户ID",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public ResponseData getUserId(@RequestBody(required = false)RequestData<Map<String,String>> requestData){
        return iUserInfoService.getUserId(requestData);
    }
}
