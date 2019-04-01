package net.sunwukong.www.user.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.user.bean.UserHomePage;
import net.sunwukong.www.user.bean.UserHomePageVisit;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 说明:用户主页控制层
 *
 * @author Mick
 * CreateDate 2018/6/12/012 11:56
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/home")
@Api(tags = "个人主页API", description = "用户服务提供")
public class UserHomePageController extends BaseController {

    @RequestMapping(value="/updatedata",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "修改个人主页接口", httpMethod = "POST", notes = "修改自我介绍",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID",paramType = "query"),
            @ApiImplicitParam(name = "selfEvaluation", value = "自我介绍",paramType = "query"),
    })
    public ResponseData updateData(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iUserHomePageService.updateData(data.get("id"),data.get("selfEvaluation"));
    }

    @RequestMapping(value="/getuserhomepage",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取个人主页信息", httpMethod = "POST", notes = "获取个人主页信息",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "visitorNo", value = "访问者编码",paramType = "query",defaultValue = ""),
    })
    public ResponseData getUserHomePage(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iUserHomePageService.getUserHomePage(data.get("userNo"),data.get("visitorNo"));
    }

    @RequestMapping(value = "/addimg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "添加个性图片", httpMethod = "POST", notes = "添加个性图片",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "个人主页ID",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "imgNo", value = "图片index",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "homePhoto", value = "图片地址",paramType = "query"),
    })
    public ResponseData addImg(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iUserHomePageService.addImg(data.get("id"),data.get("imgNo"),data.get("homePhoto"));
    }

    @RequestMapping(value = "/updateimg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "修改个性图片", httpMethod = "POST", notes = "修改个性图片",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "个人主页ID",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "imgNo", value = "图片index",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "homePhoto", value = "图片地址",paramType = "query"),
    })
    public ResponseData updateImg(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iUserHomePageService.updateImg(data.get("id"),data.get("imgNo"),data.get("homePhoto"));
    }

    @RequestMapping(value = "/delimg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "删除个性图片", httpMethod = "DELETE", notes = "删除个性图片",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID",paramType = "query"),
            @ApiImplicitParam(name = "imgNo", value = "图片index",paramType = "query"),
    })
    public ResponseData delImg(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> data = requestData.getData();
        return iUserHomePageService.delImg(data.get("id"),data.get("imgNo"));
    }

    @RequestMapping(value="/adduservisit",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "添加用户主页访问量", httpMethod = "POST", notes = "添加用户主页访问量",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "主页所属用户编码",paramType = "query"),
            @ApiImplicitParam(name = "visitNo", value = "访问者编码",paramType = "query"),
    })
    public ResponseData addUserVisit(@RequestBody(required = false) RequestData<UserHomePageVisit> requestData){
        return iUserHomePageVisitService.addUserVisit(requestData.getData());
    }

    @RequestMapping(value="/sethomecover",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "设置主页封面", httpMethod = "POST", notes = "设置主页封面",response = ResponseData.class,hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主页ID",paramType = "query"),
            @ApiImplicitParam(name = "coverNo", value = "封面标识",paramType = "query"),
    })
    public ResponseData setHomeCover(@RequestBody(required = false) RequestData<UserHomePage> requestData){
        return iUserHomePageService.setHomeCover(requestData.getData());
    }

    @RequestMapping(value="/getuserfanslist",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "根据用户编码查询粉丝列表", httpMethod = "POST", notes = "根据用户编码查询粉丝列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query"),
    })
    public ResponseData getUserFansList(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        ResponseData responseData = new ResponseData();
        Map<String, String> data = requestData.getData();
        int pageNo = Integer.parseInt(data.get("pageNo"));
        int pageSize = Integer.parseInt(data.get("pageSize"));
        int start = ApiTool.getStartPage(pageNo,pageSize);
        List<Map<String, String>> userClickList = informationClickLogService.getUserClickList(data.get("userNo"), start, pageSize);
        responseData.setData(userClickList);
        return responseData;
    }
}
