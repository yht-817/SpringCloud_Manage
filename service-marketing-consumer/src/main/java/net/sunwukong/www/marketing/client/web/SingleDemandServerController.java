package net.sunwukong.www.marketing.client.web;

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
 * 说明:个体服务控制
 *
 * @author Mick
 * @CreateDate 2018/6/26 15:35
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping("/v1/singledemandserver")
@Api(tags = "个体服务者-服务控制", description = "")
public class SingleDemandServerController extends BaseController {

    @RequestMapping(value="/getserverlist",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取服务列表", httpMethod = "POST", notes = "获取服务列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = "yh201806252144549253"),
            @ApiImplicitParam(name = "serverState", value = "类型(待审核:10290006 服务中:10290002 已完成:10290003 已结单:10290004)",paramType = "query",defaultValue = "10290006"),
    })
    public ResponseData getServerList(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return singleDemandServerService.getServerList(requestData);
    }

    @RequestMapping(value="/getserverdetails",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取服务列表详情", httpMethod = "POST", notes = "获取服务列表详情",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demandNo", value = "需求编码",paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "serverState", value = "服务状态 (待审核:10290006 服务中:10290002 已完成:10290003 已结单:10290004)",paramType = "query",defaultValue = "10290006"),
    })
    public ResponseData getServerDetails(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return singleDemandServerService.getServerDetails(requestData);
    }

    @RequestMapping(value="/completeserver",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "我已完成服务", httpMethod = "POST", notes = "我已完成服务",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "demandNo", value = "需求单号",paramType = "query"),
    })
    public ResponseData completeServer(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return singleDemandServerService.completeServer(requestData);
    }
}
