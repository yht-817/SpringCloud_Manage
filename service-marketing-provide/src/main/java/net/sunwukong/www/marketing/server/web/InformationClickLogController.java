package net.sunwukong.www.marketing.server.web;

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
@RequestMapping("/informationclicklog")
public class InformationClickLogController extends BaseController{
}
