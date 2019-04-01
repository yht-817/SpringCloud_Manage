package net.sunwukong.www.user.client.web;

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
 * 说明:邀请控制
 *
 * @author Mick
 * @CreateDate 2018/6/23 17:11
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/v1/invitefriends")
@Api(tags = "邀请好友控制", description = "")
public class InviteFriendsController extends BaseController {

    @RequestMapping(value="/smsinvite",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "短信邀请", httpMethod = "POST", notes = "提交申请",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userPhone", value = "被邀请人的电话号码",paramType = "query"),
    })
    public ResponseData smsInvite(@RequestBody(required = false) RequestData<Map<String,Object>> requestData){
        return inviteFriendsService.smsInvite(requestData);
    }

    @RequestMapping(value = "/judgeisexistfriend", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "判断用户是否为好友", httpMethod = "POST", notes = "判断用户是否为好友", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "visitorNo", value = "访问者编码", paramType = "query", defaultValue = ""),
    })
    public ResponseData judgeIsExistFriend(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return inviteFriendsService.judgeIsExistFriend(requestData);
    }

    @RequestMapping(value = "/acceptinvitation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "接受邀请", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "邀请者的用户编码",paramType = "query"),
            @ApiImplicitParam(name = "account", value = "接受邀请者的账号",paramType = "query"),
            @ApiImplicitParam(name = "accountType", value = "账号类型（1电话号码 2邮箱）",paramType = "query"),
    })
    public ResponseData AcceptInvitation(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return inviteFriendsService.acceptInvitation(requestData);
    }
}
