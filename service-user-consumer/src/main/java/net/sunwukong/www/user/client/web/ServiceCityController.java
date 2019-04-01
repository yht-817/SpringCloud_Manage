package net.sunwukong.www.user.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.ServiceScopeOne;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 岳虹廷 on  2018/7/3
 */

@RestController
@RequestMapping(value = "/v1/onetomorecity")
@Api(tags = "服务范围控制", description = "")
public class ServiceCityController extends BaseController {
    @RequestMapping(value = "/listmorecity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取城市范围列表", httpMethod = "POST", notes = "获取城市范围列表", response = ServiceScopeOne.class)
    public ResponseData getListCity() {
        return serviceScopeOneService.getCityList();
    }

    // 发布城市列表
    @ResponseBody
    @RequestMapping(value = "/releases", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "发布城市范围", httpMethod = "POST", notes = "发布城市范围列表", response = ResponseData.class)
    public ResponseData releaseMore() {
        return serviceScopeOneService.getreleaseMores();
    }
}



