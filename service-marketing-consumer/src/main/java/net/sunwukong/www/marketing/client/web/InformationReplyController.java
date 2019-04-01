package net.sunwukong.www.marketing.client.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: InformationReplyController
 * @Author: kangdong
 * @Date: 2018/7/25 上午10:27
 * @Description: 评论回复控制类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@RestController
@RequestMapping("/v1/informationreply")
@Api(tags = "评论回复", description = "评论回复服务")
public class InformationReplyController extends BaseController{

}