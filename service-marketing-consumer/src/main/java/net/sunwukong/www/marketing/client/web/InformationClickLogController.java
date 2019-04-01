package net.sunwukong.www.marketing.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.InformationClickLog;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 说明:资讯点击记录控制层
 *
 * @author Mick
 * CreateDate 2018/6/13/013 14:38
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping("/v1/informationclicklog")
@Api(tags = "业务服务模块", description = "（资讯点击记录服务）")
public class InformationClickLogController extends BaseController{
}
