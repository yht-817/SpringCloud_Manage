package net.sunwukong.www.marketing.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.CollectionInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 说明:收藏信息控制层
 *
 * @author Mick
 * @CreateDate 2018/6/22 12:24
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/v1/collectioninfo")
@Api(tags = "收藏信息控制层", description = "")
public class CollectionInfoController extends BaseController {

    @RequestMapping(value="/getusercollectioninfopage",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取用户收藏信息", httpMethod = "POST", notes = "获取用户收藏信息",response = CollectionInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
            @ApiImplicitParam(name = "collectionType", value = "收藏类型 资讯收藏:10110001 精选收藏:10110002",paramType = "query",defaultValue = "10110001"),
    })
    public ResponseData getUserCollectionInfoPage(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return collectionInfoService.getUserCollectionInfoPage(requestData);
    }


    @RequestMapping(value="/setusercollection",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "设置用户收藏", httpMethod = "POST", notes = "设置用户收藏",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contentNo", value = "内容编码(资讯编码、精选编码)",paramType = "query"),
            @ApiImplicitParam(name = "collectionType", value = "收藏类型(资讯收藏：10110001 精选收藏：10110002)",paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "isCollection", value = "类型：收藏：ture，取消：false",paramType = "query"),
    })
    public ResponseData setUserCollection(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return collectionInfoService.setUserCollection(requestData);
    }
}
