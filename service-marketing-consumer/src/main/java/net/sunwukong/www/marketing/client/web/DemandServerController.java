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
 * 说明:需求服务信息控制层
 *
 * @author Mick
 * CreateDate 2018/6/13/013 14:53
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping("/v1/demandserver")
@Api(tags = "业务服务模块", description = "（需求服务）")
public class DemandServerController extends BaseController {
    /**
     * 获取我的需求服务列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value="/getDemandServerList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取我的需求服务列表", httpMethod = "POST", notes = "获取我的需求服务列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getDemandServerList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.getDemandServerList(requestData);
    }

    /**
     * 我的已抢到列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value="/getRobbedList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "已抢到列表", httpMethod = "POST", notes = "已抢到列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getRobbedList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.getRobbedList(requestData);
    }

    /**
     * 我的待接单列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value="/getPendingOrderList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "待接单列表", httpMethod = "POST", notes = "待接单列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getPendingOrderList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.getPendingOrderList(requestData);
    }


    /**
     * 我的待审核列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value="/getWaitAuditList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "待审核列表", httpMethod = "POST", notes = "待审核列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getWaitAuditList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.getWaitAuditList(requestData);
    }


    /**
     * 服务中的列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value="/getInServiceList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "服务中的列表", httpMethod = "POST", notes = "服务中的列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getInServiceList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.getInServiceList(requestData);
    }


    /**
     * 我的已完成的列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value="/getFinishList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "我的已完成的列表", httpMethod = "POST", notes = "我的已完成的列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getFinishList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.getFinishList(requestData);
    }


    /**
     * 我的已结单列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value="/getEndServerList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "我的已结单列表", httpMethod = "POST", notes = "我的已结单列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getEndServerList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.getEndServerList(requestData);
    }


    /**
     * 获取我的需求服务详情
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value="/getDemandServerDetail",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取我的需求服务详情", httpMethod = "POST", notes = "获取我的需求服务详情",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id编码",paramType = "query"),
    })
    public ResponseData getDemandServerDetail(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.getDemandServerDetail(requestData);
    }



    /**
     * 开始需求服务
     * 服务机构想自己服务时，点击开始服务按钮，需求和服务状态都处于服务中
     * @param requestData
     * @return
     */
    @RequestMapping(value="/startServer",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "开始需求服务", httpMethod = "POST", notes = "开始需求服务",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "demandNo", value = "需求单号",paramType = "query"),
    })
    public ResponseData startServer(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.startServer(requestData);
    }



    /**
     * 审核需求服务
     * @param requestData
     * @return
     */
    @RequestMapping(value="/auditServer",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "审核需求服务", httpMethod = "POST", notes = "审核需求服务",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "demandNo", value = "需求单号",paramType = "query"),
            @ApiImplicitParam(name = "auditState", value = "审核状态",paramType = "query"),
    })
    public ResponseData auditServer(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.auditServer(requestData);
    }


    /**
     * 分发需求服务
     * @param requestData
     * @return
     */
    @RequestMapping(value="/distribute",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分发需求服务", httpMethod = "POST", notes = "分发需求服务",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "demandNo", value = "需求单号",paramType = "query"),
    })
    public ResponseData distribute(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.distribute(requestData);
    }



    /**
     * 点击确认已经完成服务
     * @param requestData
     * @return
     */
    @RequestMapping(value="/confirmFinish",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "已完成服务", httpMethod = "POST", notes = "已完成服务",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demandNo", value = "单号",paramType = "query"),
            @ApiImplicitParam(name = "userNo   ", value = "用户编码",paramType = "query"),
    })
    public ResponseData confirmFinish(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return demandServerService.confirmFinish(requestData);
    }
}
