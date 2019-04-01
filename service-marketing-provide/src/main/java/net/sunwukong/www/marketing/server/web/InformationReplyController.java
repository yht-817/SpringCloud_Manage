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
 * @FileName: InformationReplyController
 * @Author: kangdong
 * @Date: 2018/7/25 上午10:27
 * @Description: 评论回复控制类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */
@RestController
@RequestMapping(value = "/informationreply")
@Api(tags = "评论回复", description = "评论回复服务")
public class InformationReplyController extends BaseController {

}
