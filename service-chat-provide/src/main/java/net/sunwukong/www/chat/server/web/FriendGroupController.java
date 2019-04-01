package net.sunwukong.www.chat.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.chat.bean.FriendGroup;
import net.sunwukong.www.chat.vo.*;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * 说明:用户群聊消息控制
 *
 * @author Mick
 * @CreateDate 2018/7/9 14:46
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/friendgroup")
@Api(tags = "用户群聊消息控制", description = "")
public class FriendGroupController extends BaseController {

    @RequestMapping(value="/addfriendgroup",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "创建群聊", httpMethod = "POST", notes = "创建群聊",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName", value = "群名称",paramType = "query",defaultValue = "孙悟空华人网1群"),
            @ApiImplicitParam(name = "groupHead", value = "群头像",paramType = "query",defaultValue = "http://47.98.139.112:8080/static_img/userinfo/head/2018071015084276941"),
            @ApiImplicitParam(name = "userNo", value = "创建人编码",paramType = "query",defaultValue = "yh201807091742161017"),
            @ApiImplicitParam(name = "groupNode", value = "群公告",paramType = "query",defaultValue = "这是群公告"),
            @ApiImplicitParam(name = "ifPayGroup", value = "是否付费(公开群:10340001 收费群:10340002)",paramType = "query",defaultValue = "10340001"),
            @ApiImplicitParam(name = "payAmount", value = "收费金额",paramType = "query"),
            @ApiImplicitParam(name = "labelNo", value = "标签编码",paramType = "query",defaultValue = "10001"),
            @ApiImplicitParam(name = "labelName", value = "标签名称",paramType = "query",defaultValue = "艺术"),
    })
    public ResponseData addFriendGroup(@RequestBody(required = false) @Valid FriendGroup vo, BindingResult result){
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iFriendGroupService.addFriendGroup(vo);
    }

    @RequestMapping(value="/writedissolve",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "解散群聊", httpMethod = "POST", notes = "解散群聊",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
    })
    public ResponseData writeDissolve(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iFriendGroupService.writeDissolve(data.get("groupNo"));
    }

    @RequestMapping(value="/writeexit",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "退出群聊", httpMethod = "POST", notes = "退出群聊",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public ResponseData writeExit(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iFriendGroupService.writeExit(data.get("userNo"),data.get("groupNo"));
    }

    @RequestMapping(value="/delgroupuser",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "删除群成员", httpMethod = "POST", notes = "删除群成员",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "群主编码",paramType = "query"),
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
            @ApiImplicitParam(name = "userNos", value = "用户编码列表",paramType = "query"),
    })
    public ResponseData delGroupUser(@RequestBody(required = false) @Valid DelGroupUserVo vo,BindingResult result){
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iFriendGroupService.writeDelGroupUser(vo.getUserNo(),vo.getGroupNo(),vo.getUserNos());
    }

    @RequestMapping(value="/invitejoin",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "邀请加入", httpMethod = "POST", notes = "邀请加入",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "邀请方的用户编码",paramType = "query"),
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
            @ApiImplicitParam(name = "userNos", value = "用户编码",paramType = "query")
    })
    public ResponseData writeInviteJoin(@RequestBody(required = false) @Valid JoinGroupVo vo){
        return iFriendGroupService.writeInviteJoin(vo.getUserNo(),vo.getUserNos(),vo.getGroupNo());
    }

    @RequestMapping(value="/applyjoin",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "申请加入", httpMethod = "POST", notes = "申请加入",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
    })
    public ResponseData writeApplyJoin(@RequestBody(required = false) @Valid JoinGroupVo vo){
        return iFriendGroupService.writeApplyJoin(vo.getUserNo(),vo.getGroupNo());
    }

    @RequestMapping(value="/updatefriendgroup",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "修改群资料", httpMethod = "POST", notes = "修改群资料",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
            @ApiImplicitParam(name = "groupName", value = "群名称",paramType = "query"),
            @ApiImplicitParam(name = "groupNode", value = "群公告",paramType = "query"),
            @ApiImplicitParam(name = "groupHead", value = "群头像",paramType = "query"),
    })
    public ResponseData updateFriendGroup(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iFriendGroupService.updateFriendGroup(data.get("groupNo"),data.get("groupName"),data.get("groupNode"),data.get("groupHead"));
    }

    @RequestMapping(value = "/updategroupsetting", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "设置群是否收费", httpMethod = "POST", notes = "设置群是否收费", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群聊编码", paramType = "query"),
            @ApiImplicitParam(name = "ifPayGroup", value = "是否付费(公开群:10340001 收费群:10340002)", paramType = "query"),
            @ApiImplicitParam(name = "payAmount", value = "付费金额", paramType = "query"),
    })
    public ResponseData updateGroupSetting(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iFriendGroupService.updateGroupSetting(data.get("groupNo"),data.get("ifPayGroup"),data.get("payAmount"));
    }

    @RequestMapping(value = "/querypagegroupusers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页获取群成员列表",response = GroupUsers.class, notes = "分页获取群成员列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = "yh201807091742161017"),
            @ApiImplicitParam(name = "content", value = "检索名称",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "开始页面",paramType = "query",defaultValue = "10"),
    })
    public ResponseData queryPageGroupUsers(@RequestBody(required = false) @Valid PageVo vo, BindingResult result) {
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iFriendGroupService.queryPageGroupUsers(vo.getGroupNo(),vo.getContent(),vo.getPageNo(),vo.getPageSize());
    }

    @RequestMapping(value = "/querypageusergroups", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页获取用户群列表", httpMethod = "POST", notes = "分页获取用户群列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = "yh201807091742161017"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "开始页面",paramType = "query",defaultValue = "10"),
    })
    public ResponseData queryPageUserGroups(@RequestBody(required = false) @Valid PageVo vo, BindingResult result) {
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iFriendGroupService.queryPageUserGroups(vo.getUserNo(),vo.getPageNo(),vo.getPageSize());
    }

    @RequestMapping(value="/querylabels",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取标签列表",response = QueryGroup.class, notes = "获取标签列表", httpMethod = "POST")
    public ResponseData queryLabels(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return iFriendGroupService.queryLabels();
    }

    @RequestMapping(value="/auditinvitation",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "审核(面对群主 用户)",response = QueryGroup.class, notes = "审核", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inviteNo", value = "申请编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "auditState", value = "审核状态(同意:true 拒绝:false)",paramType = "query",defaultValue = ""),
    })
    public ResponseData auditInvitation(@RequestBody(required = false)RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iFriendGroupUserService.writeAudit(data.get("inviteNo"),Boolean.parseBoolean(data.get("auditState")));
    }

    @RequestMapping(value = "/querypagesearch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页检索", httpMethod = "POST", notes = "开始检索", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "搜索者编码",paramType = "query",defaultValue = "yh201807101948141454"),
            @ApiImplicitParam(name = "content", value = "检索内容",paramType = "query",defaultValue = "秋"),
            @ApiImplicitParam(name = "type", value = "检索类型(用户:0 群聊:1)",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public ResponseData queryPageSearch(@RequestBody(required = false) @Valid PageSearchVo vo, BindingResult result) {
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iFriendGroupService.queryPageSearch(vo.getUserNo(),vo.getContent(),vo.getType(),vo.getPageNo(),vo.getPageSize());
    }

    @RequestMapping(value = "/querygroupdetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取群详情", httpMethod = "POST", notes = "获取群详情", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
    })
    public ResponseData queryGroupDetails(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iFriendGroupService.queryGroupDetails(data.get("groupNo"),data.get("userNo"));
    }

    @RequestMapping(value = "/querypagesearchinviteuser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页检索邀请群成员列表", httpMethod = "POST", notes = "分页检索邀请群成员列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "搜索者编码",paramType = "query",defaultValue = "yh201807101948141454"),
            @ApiImplicitParam(name = "groupNo", value = "群编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "content", value = "检索内容",paramType = "query",defaultValue = "秋"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public ResponseData queryPageSearchInviteUser(@RequestBody(required = false) @Valid SearchInviteUserVo vo, BindingResult result) {
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iFriendGroupService.queryPageSearchInviteUser(vo.getUserNo(),vo.getGroupNo(),vo.getContent(),vo.getPageNo(),vo.getPageSize());
    }
}
