package net.sunwukong.www.user.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.vo.ServerDetails;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 说明:我要抢单
 *
 * @author Mick
 * @CreateDate 2018/6/23 13:25
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/applyserver")
@Api(tags = "我要抢单", description = "")
public class ApplyServerController extends BaseController {

    @RequestMapping(value="/saveserverdetails",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "保存服务数据详情", httpMethod = "POST", notes = "保存服务数据详情",response = ResponseData.class,hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "userCategories", value = "服务类目",paramType = "query"),
            @ApiImplicitParam(name = "userCitys", value = "所在城市",paramType = "query"),
            @ApiImplicitParam(name = "userScopes", value = "服务城市",paramType = "query"),
    })
    public ResponseData saveServerDetails(@RequestBody(required = false) RequestData<ServerDetails> requestData){
        return iApplyServerService.saveServerDetails(requestData.getData());
    }

    @RequestMapping(value="/getserverdetails",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取服务数据详情", httpMethod = "POST", notes = "获取服务数据详情",response = ResponseData.class,hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query")
    })
    public ResponseData getServerDetails(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return iApplyServerService.getServerDetails(requestData.getData().get("userNo"));
    }
}
