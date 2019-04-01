package net.sunwukong.www.chat.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.chat.bean.FriendInvite;
import net.sunwukong.www.chat.vo.AuditVo;
import net.sunwukong.www.chat.vo.PageVo;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * 说明:好友邀请控制
 *
 * @author Mick
 * @CreateDate 2018/7/11 16:59
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/friendinvite")
@Api(tags = "好友邀请控制", description = "")
public class FriendInviteController extends BaseController {

    @RequestMapping(value = "/addfriendinvite", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "添加好友邀请", httpMethod = "POST", notes = "添加好友邀请", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "friendUserNo", value = "好友编码", paramType = "query"),
            @ApiImplicitParam(name = "verifyInfo", value = "验证消息", paramType = "query"),
    })
    public ResponseData addFriendInvite(@RequestBody(required = false) @Valid FriendInvite vo, BindingResult result) {
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iFriendInviteService.addInvite(vo);
    }

    @RequestMapping(value = "/auditfriendinvite", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "审核好友邀请", httpMethod = "POST", notes = "审核好友邀请", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inviteNo", value = "申请编码", paramType = "query"),
            @ApiImplicitParam(name = "auditState", value = "状态(同意:10150002 拒绝:10150003)", paramType = "query"),
    })
    public ResponseData auditFriendInvite(@RequestBody(required = false) @Valid AuditVo vo, BindingResult result) {
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iFriendInviteService.witerAuditFriendInvite(vo.getInviteNo(), vo.getAuditState());
    }

    @RequestMapping(value = "/querypagefriends", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取好友列表", httpMethod = "POST", notes = "审核好友邀请", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度", paramType = "query"),
    })
    public ResponseData queryPageFriends(@RequestBody(required = false) @Valid PageVo vo, BindingResult result) {
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iFriendInviteService.queryPageFriends(vo.getUserNo(), vo.getPageNo(), vo.getPageSize());
    }

    @RequestMapping(value = "/delfriend", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "删除好友", httpMethod = "POST", notes = "删除好友", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inviteNo", value = "申请编码", paramType = "query"),
    })
    public ResponseData delFriend(@RequestBody(required = false)RequestData<Map<String,String>> vo) {
        Map<String, String> data = vo.getData();
        return iFriendInviteService.writeDelFriend(data.get("inviteNo"));
    }
}
