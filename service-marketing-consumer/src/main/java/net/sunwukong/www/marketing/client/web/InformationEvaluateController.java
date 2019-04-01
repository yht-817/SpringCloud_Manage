package net.sunwukong.www.marketing.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.InformationReply;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: InformationEvaluateController
 * @Author: kangdong
 * @Date: 2018/7/25 上午10:27
 * @Description: 资讯评论控制类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */
@RestController
@RequestMapping("/v1/informationevaluate")
@Api(tags = "资讯评论", description = "资讯评论服务")
public class InformationEvaluateController extends BaseController {

    //分页获取评论列表
    @RequestMapping(value = "/evaluateList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页获取文章的评论列表", httpMethod = "POST", notes = "分页获取文章的评论列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query",defaultValue = "zx201807261522501489"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query",defaultValue = "yh201807101948141454"),
            @ApiImplicitParam(name = "pageNo", value = "页码", paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "query",defaultValue = "10"),
    })
    public ResponseData evaluateList(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return informationEvaluateService.evaluateList(requestData);
    }


    //获取一条评论
    @RequestMapping(value = "/getOneEvaluate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取一条评论", httpMethod = "POST", notes = "获取一条评论", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query"),
            @ApiImplicitParam(name = "evaluateNo", value = "评论的ID", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
    })
    public ResponseData getOneEvaluate(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return informationEvaluateService.getOneEvaluate(requestData);
    }


    //点赞或取消点赞
    @RequestMapping(value = "/addCancel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "点赞或取消点赞", httpMethod = "POST", notes = "点赞或取消点赞", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query"),
            @ApiImplicitParam(name = "evaluateNo", value = "评论编码", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "liked", value = "点赞或取消：true已经点赞，false未点赞", paramType = "query"),
    })
    public ResponseData addOrCancel(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return informationEvaluateService.addOrCancel(requestData);
    }

    @RequestMapping(value = "/getchildcomments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = " 获取子评论列表", httpMethod = "POST", notes = " 获取子评论列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "evaluateNo", value = "主评论编码", paramType = "query",defaultValue = "zxaea57f7b779c1575f432a0b4485bcc61"),
            @ApiImplicitParam(name = "pageNo", value = "分页页码", paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query",defaultValue = "10")
    })
    public ResponseData getChildComments(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return informationEvaluateService.getChildComments(requestData);
    }

    @RequestMapping(value = "/addcommentsreply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "回复评论", httpMethod = "POST", notes = "回复评论", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query"),
            @ApiImplicitParam(name = "evaluateNo", value = "主评论编码", paramType = "query"),
            @ApiImplicitParam(name = "fromUserNo", value = "回复用户编码", paramType = "query"),
            @ApiImplicitParam(name = "replyContent", value = "回复内容", paramType = "query"),
            @ApiImplicitParam(name = "toUserNo", value = "被回复用户编码", paramType = "query"),
    })
    public ResponseData addCommentsReply(@RequestBody(required = false) RequestData<InformationReply> requestData) {
        return informationEvaluateService.addCommentsReply(requestData);
    }

}
