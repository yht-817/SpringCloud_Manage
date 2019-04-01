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
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ChoiceInfoController
 * @Author: kangdong
 * @Date: 2018/7/12 下午5:35
 * @Description: 全球精选信息控制类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */
@RestController
@RequestMapping("/v1/choiceinfo")
@Api(tags = "全球精选信息模块", description = "（全球精选信息服务）")
public class ChoiceInfoController extends BaseController {

    //首页顶部精选信息
    @RequestMapping(value = "/getTopList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "顶部列表", httpMethod = "POST", notes = "顶部精选列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "positiongoods", value = "商品销售的地点", paramType = "query"),
    })
    public ResponseData getTopList(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return choiceInfoService.getTopList(requestData);
    }

    //首页中间banner
    @RequestMapping(value = "/getMiddleList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "中间banner列表", httpMethod = "POST", notes = "中间banner列表", response = ResponseData.class)
    public ResponseData getMiddleList(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return choiceInfoService.getMiddleList(requestData);
    }


    //首页底部精选分页列表信息
    @RequestMapping(value = "/getBottomList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "底部分页列表", httpMethod = "POST", notes = "底部分页列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", defaultValue = "yh201807091742161017"),
            @ApiImplicitParam(name = "pageNo", value = "页码", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "positiongoods", value = "商品销售的地点", paramType = "query"),
            @ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query"),
    })
    public ResponseData getBottomList(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return choiceInfoService.getBottomList(requestData);
    }


    //查看详情
    @RequestMapping(value = "/getDetail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查看详情", httpMethod = "POST", notes = "查看详情", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceNo", value = "资源编码", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "shareNo", value = "分享单号", paramType = "query"),
    })
    public ResponseData getDetail(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return choiceInfoService.getDetail(requestData);
    }


    //点击收藏精选
    @RequestMapping(value = "/addOrCancel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "添加或取消收藏", httpMethod = "POST", notes = "添加或取消收藏", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceNo", value = "资源编码", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query"),
            @ApiImplicitParam(name = "isCollection", value = "收藏或取消：ture已经收藏，false未收藏", paramType = "query"),
    })
    public ResponseData addOrCancel(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return choiceInfoService.addOrCancel(requestData);
    }

    //下单
    @RequestMapping(value = "/addBuy", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "下单", httpMethod = "POST", notes = "下单", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "resourceNo", value = "资源编码", paramType = "query"),
            @ApiImplicitParam(name = "payNum", value = "购买数量", paramType = "query"),
    })
    public ResponseData addBuy(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return choiceInfoService.addBuy(requestData);
    }

    //用户分享精选信息
    @RequestMapping(value = "/shareurl", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分享精选信息", httpMethod = "POST", notes = "分享精选信息", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "resourceNo", value = "资源编码", paramType = "query"),
    })
    public ResponseData share(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return choiceInfoService.addShare(requestData);
    }

    //取消订单
    @RequestMapping(value = "/cancelOrderS", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "取消订单", httpMethod = "POST", notes = "取消订单", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单号", paramType = "query"),
            @ApiImplicitParam(name = "couponNo", value = "券编码", paramType = "query"),
            @ApiImplicitParam(name = "resourceNo", value = "资源编码", paramType = "query"),
    })
    public ResponseData cancelOrder(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return choiceInfoService.cancelOrderS(requestData);
    }
}
