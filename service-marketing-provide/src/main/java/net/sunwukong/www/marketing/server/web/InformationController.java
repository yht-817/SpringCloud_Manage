package net.sunwukong.www.marketing.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.marketing.dto.SaveInformationInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 说明:资讯控制层
 *
 * @author Mick
 * @CreateDate 2018/7/19 15:18
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping("/information")
@Api(tags = "资讯控制层", description = "")
public class InformationController extends BaseController {

    @RequestMapping(value = "/getPageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页资讯列表", httpMethod = "POST", notes = "分页资讯列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationClass", value = "资讯分类", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度", paramType = "query"),
            @ApiImplicitParam(name = "keyword", value = "搜索关键词", paramType = "query"),
    })
    public ResponseData getPageList(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        int pageNo = Integer.parseInt(data.get("pageNo"));
        int pageSize = Integer.parseInt(data.get("pageSize"));
        int start = ApiTool.getStartPage(pageNo, pageSize);
        return iInformationService.getPageList(data.get("informationClass"),start,pageSize,data.get("keyword"));
    }

    @RequestMapping(value = "/getDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "资讯详情", httpMethod = "POST", notes = "资讯详情", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
    })
    public ResponseData getDetails(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        System.out.print(data.toString());
        return iInformationService.getDetails(data.get("informationNo"), data.get("userNo"));
    }

    @RequestMapping(value = "/getarticlecontent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取URL链接内容", httpMethod = "POST", notes = "获取URL链接内容", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "地址链接", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型(1:微信文章 2:本地文章)", paramType = "query"),
    })
    public ResponseData getArticleContent(@RequestBody(required = false) RequestData<Map<String, String>> requestData) throws IOException {
        Map<String, String> data = requestData.getData();
        return iInformationService.getArticleContent(data.get("url"), data.get("type"));
    }

    @RequestMapping(value = "/saveinformation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "发布资讯", httpMethod = "POST", notes = "发布资讯", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationType", value = "资讯类型(10090001:广告 10090002:优惠券)", paramType = "query", defaultValue = "10090001"),
            @ApiImplicitParam(name = "informationClass", value = "资讯分类(10220001:留学 10220002:移民 10220003:旅游)", paramType = "query", defaultValue = "10220001"),
            @ApiImplicitParam(name = "informationCityNo", value = "城市编码", paramType = "query", defaultValue = "10001004"),
            @ApiImplicitParam(name = "cityName", value = "城市名称", paramType = "query", defaultValue = "日本"),
            @ApiImplicitParam(name = "informationMode", value = "资讯模式(10230001:原创 10230002:转载)", paramType = "query", defaultValue = "10230002"),
            @ApiImplicitParam(name = "userNo", value = "发布人ID", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationName", value = "资讯标题", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationPhoto", value = "资讯图片(10400001:矩形图片 10400002:方形图片)", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "photoSizeType", value = "图片尺寸类型", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationContent", value = "资讯内容", paramType = "query", defaultValue = ""),
    })
    public ResponseData saveInformation(@RequestBody(required = false) RequestData<SaveInformationInput> requestData) {
        return iInformationService.saveInformation(requestData.getData());
    }

    @RequestMapping(value = "/getinformationclass", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取资讯分类", httpMethod = "POST", notes = "获取资讯分类", response = ResponseData.class)
    public ResponseData getInformationClass(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return iInformationService.getInformationClass();
    }

    @RequestMapping(value = "/delinformation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "删除资讯", httpMethod = "POST", notes = "删除资讯", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query"),
    })
    public ResponseData delInformation(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iInformationService.delInformation(data.get("informationNo"));
    }

    @RequestMapping(value = "/updateinformation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "发布资讯", httpMethod = "POST", notes = "发布资讯", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationType", value = "资讯类型(10090001:广告 10090002:优惠券)", paramType = "query", defaultValue = "10090001"),
            @ApiImplicitParam(name = "informationClass", value = "资讯分类(10220001:留学 10220002:移民 10220003:旅游)", paramType = "query", defaultValue = "10220001"),
            /*@ApiImplicitParam(name = "informationCityNo", value = "城市编码", paramType = "query", defaultValue = "10001004"),
            @ApiImplicitParam(name = "cityName", value = "城市名称", paramType = "query", defaultValue = "日本"),*/
            @ApiImplicitParam(name = "informationMode", value = "资讯模式(10230001:原创 10230002:转载)", paramType = "query", defaultValue = "10230002"),
            @ApiImplicitParam(name = "informationName", value = "资讯标题", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationPhoto", value = "资讯图片", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "photoSizeType", value = "图片尺寸类型(10400001:矩形图片 10400002:方形图片)", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationContent", value = "资讯内容", paramType = "query", defaultValue = ""),
    })
    public ResponseData updateInformation(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iInformationService.updateInformation(data.get("informationNo"),
                data.get("informationType"),
                data.get("informationClass"),
                data.get("informationMode"),
                data.get("informationName"),
                data.get("informationPhoto"),
                data.get("photoSizeType"),
                data.get("informationContent"));
    }
}
