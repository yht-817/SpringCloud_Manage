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
 * 说明:验证码控制层
 *
 * @author Mick
 * CreateDate 2018/6/9/009 23:03
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/v1/securitycode")
@Api(tags = "验证码控制", description = "")
public class SecurityCodeController extends BaseController{

    @RequestMapping(value="/getcode",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取验证码接口", httpMethod = "POST", notes = "获取验证码接口",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账户号",paramType = "query"),
            @ApiImplicitParam(name = "accountType", value = "账户类型 1电话号码 2邮箱",paramType = "query"),
    })
    public ResponseData getCode(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return securityCodeService.getCode(requestData);
    }


    @RequestMapping(value="/checkacode",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "校验验证码接口", httpMethod = "POST", notes = "校验短信验证码接口",response = ResponseData.class,hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账户号",paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码",paramType = "query"),
    })
    public ResponseData checkaCode(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return securityCodeService.checkaCode(requestData);
    }

    @RequestMapping(value="/getimgcode",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取图片验证码", httpMethod = "POST", notes = "获取图片验证码",response = ResponseData.class)
    public ResponseData getImgCode(){
        return securityCodeService.getImgCode();
    }

    @RequestMapping(value="/checkaimgcode",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "校验图片验证码接口", httpMethod = "POST", notes = "校验短信验证码接口",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账户号",paramType = "query"),
            @ApiImplicitParam(name = "accountType", value = "账户类型 1电话号码 2邮箱",paramType = "query"),
            @ApiImplicitParam(name = "code", value = "图片验证码",paramType = "query"),
    })
    public ResponseData checkaImgCode(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return securityCodeService.checkaImgCode(requestData);
    }
}
