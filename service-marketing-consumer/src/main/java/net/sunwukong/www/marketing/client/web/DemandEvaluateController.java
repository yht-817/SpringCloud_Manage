package net.sunwukong.www.marketing.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.DemandEvaluate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明:需求评价控制
 *
 * @author Mick
 * CreateDate 2018/6/25/025 18:10
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/v1/demandevaluate")
@Api(tags = "需求评价控制", description = "")
public class DemandEvaluateController extends BaseController{

    @RequestMapping(value="/adddemandevaluate",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "添加服务需求评价", httpMethod = "POST", notes = "添加服务需求评价",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demandNo", value = "需求单号",paramType = "query",defaultValue = "xq201806241935359471"),
            @ApiImplicitParam(name = "userNo", value = "接单服务机构编码",paramType = "query",defaultValue = "yh201806241935359471"),
            @ApiImplicitParam(name = "presonStar", value = "服务人员评星",paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "timeStar", value = "服务时效评星",paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "qualityStat", value = "服务质量评星",paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "evaluateRemark", value = "评语",paramType = "query",defaultValue = "暂无"),
            @ApiImplicitParam(name = "complainState", value = "投诉状态（未投诉：10280001 投诉中：10280002）",paramType = "query",defaultValue = "10280001"),
    })
    public ResponseData addDemandEvaluate(@RequestBody(required = false) RequestData<DemandEvaluate> requestData){
        return demandEvaluateService.addDemandEvaluate(requestData);
    }
}
