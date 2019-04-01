package net.sunwukong.www.marketing.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ChoiceInfoSearchKeywordController
 * @Author: kangdong
 * @Date: 2018/7/12 下午5:35
 * @Description: 关键词
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@RestController
@RequestMapping("/v1/choiceinfosearchkeyword")
@Api(tags = "关键词模块", description = "（关键词服务）")
public class ChoiceInfoSearchKeywordController extends BaseController {

    //推荐搜索热门关键词列表
    @RequestMapping(value="/getHotList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "热门关键词列表", httpMethod = "POST", notes = "热门关键词列表",response = ResponseData.class)
    public ResponseData getHotList() {
        return choiceInfoSearchKeywordService.getHotList();
    }
}
