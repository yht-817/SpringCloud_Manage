package net.sunwukong.www.user.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明:目录控制层
 *
 * @author Mick
 * CreateDate 2018/6/22/022 22:02
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/city")
@Api(tags = "城市服务列表", description = "")
public class CityController extends BaseController {

    @RequestMapping(value="/getcitylist",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取城市列表", httpMethod = "POST", notes = "获取城市列表",response = ResponseData.class)
    public ResponseData getCityList(){
        return iCityService.getCityList();
    }
}
