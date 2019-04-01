package net.sunwukong.www.user.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 岳虹廷 on  2018/7/3
 * <p>
 * 地址服务范围的的一对多的控制层结构
 */
@RestController
@RequestMapping(value = "/servicecity")
@Api(tags = "城市的地址服务范围", description = "")
public class ServiceCityOneToMoreController extends BaseController {

    // 返回一对多的城市接口
    @ResponseBody
    @RequestMapping(value = "/citylist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取城市范围列表", httpMethod = "POST", notes = "获取城市范围列表", response = ResponseData.class)
    public ResponseData getCityMore() {
        return iServiceScopeOneService.getCityMore();
    }


    // 发布城市列表
    @ResponseBody
    @RequestMapping(value = "/release", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "发布城市范围", httpMethod = "POST", notes = "发布城市范围列表", response = ResponseData.class)
    public ResponseData releaseMore() {
        return iServiceScopeOneService.getreleaseMore();
    }


}
