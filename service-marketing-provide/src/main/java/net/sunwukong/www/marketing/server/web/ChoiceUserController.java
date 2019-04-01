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
 * @FileName: ChoiceUserController
 * @Author: kangdong
 * @Date: 2018/7/13 上午10:22
 * @Description: 用户精选券信息控制类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */
@RestController
@RequestMapping(value = "/choiceuser")
@Api(tags = "用户精选券信息", description = "用户精选券信息服务")
public class ChoiceUserController extends BaseController {
    @RequestMapping(value = "/getUsedList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "我使用过的票券列表", httpMethod = "POST", notes = "我使用过的票券列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query")
    })
    public ResponseData getUsedList(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iChoiceUserService.getUsedList(data);
    }

    @RequestMapping(value = "/getNotUsedList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "我未使用的票券列表", httpMethod = "POST", notes = "我未使用的票券列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query")
    })
    public ResponseData getNotUsedList(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iChoiceUserService.getNotUsedList(data);
    }

    @RequestMapping(value = "/getOverdueList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "已过期的票券列表", httpMethod = "POST", notes = "已过期的票券列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query")
    })
    public ResponseData getOverdueList(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iChoiceUserService.getOverdueList(data);
    }

    @RequestMapping(value = "/show", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取二维码", httpMethod = "POST", notes = "获取二维码", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponNo", value = "优惠券编码", paramType = "query"),
    })
    public ResponseData show(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iChoiceUserService.getShowCode(data);
    }

    //消费优惠券
    @RequestMapping(value = "/used", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "使用优惠券", httpMethod = "POST", notes = "使用优惠券", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponNo", value = "优惠券编码", paramType = "query"),
    })
    public ResponseData used(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iChoiceUserService.updateUseState(data);
    }

    //去支付,跳转支付页面
    @RequestMapping(value = "/goPay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "去支付", httpMethod = "POST", notes = "去支付", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query"),
            @ApiImplicitParam(name = "payNo", value = "支付单号", paramType = "query"),
            @ApiImplicitParam(name = "shareNo", value = "分享单号", paramType = "query"),
    })
    public ResponseData goPay(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iChoiceUserService.goPay(data);
    }
}
