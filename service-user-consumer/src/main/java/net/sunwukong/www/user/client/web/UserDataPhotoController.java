package net.sunwukong.www.user.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserDataPhoto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 说明:用户资料图片控制层
 *
 * @author Mick
 * @CreateDate 2018/6/22 10:46
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/v1/userdataphoto")
@Api(tags = "用户资料图片控制", description = "")
public class UserDataPhotoController extends BaseController {

    @RequestMapping(value="/adduserdataphoto",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "添加用户资料图片", httpMethod = "POST", notes = "添加用户资料图片",response = ResponseData.class,hidden = true)
    public ResponseData addUserDataPhoto(@RequestBody(required = false) RequestData<UserDataPhoto> requestData){
        return userDataPhotoService.addUserDataPhoto(requestData);
    }

    @RequestMapping(value="/getuserdataphoto",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "根据用户编码获取用户证件信息", httpMethod = "POST", notes = "根据用户编码获取用户证件信息",response = ResponseData.class,hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query",defaultValue = ""),
    })
    public ResponseData getUserDataPhoto(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return userDataPhotoService.getUserDataPhoto(requestData);
    }
}
