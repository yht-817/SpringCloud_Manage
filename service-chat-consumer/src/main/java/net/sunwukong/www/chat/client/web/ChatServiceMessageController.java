package net.sunwukong.www.chat.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.vo.PageVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 说明:服务通知控制
 *
 * @author Mick
 * @CreateDate 2018/7/12 17:07
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/v1/chatservicemessage")
@Api(tags = "服务通知控制", description = "")
public class ChatServiceMessageController extends BaseController {

    @RequestMapping(value = "/querypagechatservicemessage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页获取用户通知列表", httpMethod = "POST", notes = "分页获取用户通知列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query",defaultValue = "yh201807091742161017"),
            @ApiImplicitParam(name = "pageNo", value = "当前页面", paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度", paramType = "query",defaultValue = "10"),
    })
    public ResponseData queryPageChatServiceMessage(@RequestBody(required = false)RequestData<PageVo> vo) {
        return iChatServiceMessageService.queryPageChatServiceMessage(vo.getData());
    }

    @RequestMapping(value = "/setreadstate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "设置消息阅读状态", httpMethod = "POST", notes = "设置消息阅读状态", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "消息ID", paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "readState", value = "已阅读:10200002", paramType = "query",defaultValue = "10200002"),
    })
    public ResponseData setReadState(@RequestBody(required = false)RequestData<Map<String,String>> vo) {
        return iChatServiceMessageService.setReadState(vo);
    }
}
