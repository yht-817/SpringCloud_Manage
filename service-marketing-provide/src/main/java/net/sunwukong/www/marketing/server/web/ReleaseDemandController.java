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
 * 说明:发布需求
 *
 * @author Mick
 * @CreateDate 2018/7/3 10:25
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping("/releasedemand")
@Api(tags = "发布需求", description = "")
public class ReleaseDemandController extends BaseController {
    @RequestMapping(value="/getcityofficerlist",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取所在城市的所有运营官", httpMethod = "POST", notes = "获取所在城市的所有运营官",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityNo", value = "城市编码",paramType = "query"),
    })
    public ResponseData getCityOfficerList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return iReleaseDemandService.getCityOfficerList(requestData.getData().get("cityNo"));
    }
}
