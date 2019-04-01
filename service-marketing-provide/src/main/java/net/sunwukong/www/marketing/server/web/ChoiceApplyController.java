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
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ChoiceInfoController
 * @Author: kangdong
 * @Date: 2018/7/11 下午5:27
 * @Description: 全球精选信息控制类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@RestController
@RequestMapping(value = "/choiceapply")
@Api(tags = "申请精选", description = "申请精选服务")
public class ChoiceApplyController extends BaseController {


    //申请精选
    @RequestMapping(value="/apply",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "申请精选", httpMethod = "POST", notes = "申请精选",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "name", value = "联系人",paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "联系电话",paramType = "query"),
    })
    public ResponseData apply(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iChoiceApplyService.addApply(data);
    }
}
