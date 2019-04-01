package net.sunwukong.www.chat.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.bean.FriendGroup;
import net.sunwukong.www.chat.vo.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(value = "/v1/friendgroup")
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
    public ResponseData addFriendGroup(@RequestBody(required = false) RequestData<FriendGroup> requestData){
        return iFriendGroupService.addFriendGroup(requestData.getData());
    }

    @RequestMapping(value="/writedissolve",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "解散群聊", httpMethod = "POST", notes = "解散群聊",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
    })
    public ResponseData writeDissolve(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return iFriendGroupService.writeDissolve(requestData);
    }

    @RequestMapping(value="/writeexit",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "退出群聊", httpMethod = "POST", notes = "退出群聊",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public ResponseData writeExit(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return iFriendGroupService.writeExit(requestData);
    }

    @RequestMapping(value="/delgroupuser",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "删除群成员", httpMethod = "POST", notes = "删除群成员",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "群主编码",paramType = "query"),
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
            @ApiImplicitParam(name = "userNos", value = "用户编码列表",paramType = "query"),
    })
    public ResponseData delGroupUser(@RequestBody(required = false) RequestData<DelGroupUserVo> requestData){
        return iFriendGroupService.delGroupUser(requestData.getData());
    }

    @RequestMapping(value="/invitejoin",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "邀请加入", httpMethod = "POST", notes = "邀请加入",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "邀请方的用户编码",paramType = "query"),
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
            @ApiImplicitParam(name = "userNos", value = "用户编码",paramType = "query")
    })
    public ResponseData inviteJoin(@RequestBody(required = false)RequestData<JoinGroupVo> requestData){
        return iFriendGroupService.inviteJoin(requestData.getData());
    }

    @RequestMapping(value="/applyjoin",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "申请加入", httpMethod = "POST", notes = "申请加入",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
    })
    public ResponseData applyJoin(@RequestBody(required = false)RequestData<JoinGroupVo> requestData){
        return iFriendGroupService.applyJoin(requestData.getData());
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
        return iFriendGroupService.updateFriendGroup(requestData);
    }

    @RequestMapping(value="/updategroupsetting",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "设置群是否收费", httpMethod = "POST", notes = "设置群是否收费",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
            @ApiImplicitParam(name = "ifPayGroup", value = "是否付费(公开群:10340001 收费群:10340002)",paramType = "query"),
            @ApiImplicitParam(name = "payAmount", value = "付费金额",paramType = "query"),
    })
    public ResponseData updateGroupSetting(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return iFriendGroupService.updateGroupSetting(requestData);
    }

    @RequestMapping(value="/querypagegroupusers",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取群成员列表",response = QueryUser.class, notes = "分页获取群成员列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
            @ApiImplicitParam(name = "content", value = "检索名称",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "开始页面",paramType = "query",defaultValue = "10"),
    })
    public ResponseData queryPageGroupUsers(@RequestBody(required = false) RequestData<PageVo> requestData){
        return iFriendGroupService.queryPageGroupUsers(requestData.getData());
    }

    @RequestMapping(value="/querypageusergroups",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取用户群列表",response = QueryGroup.class, notes = "分页获取用户群列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = "yh201807091742161017"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "开始页面",paramType = "query",defaultValue = "10"),
    })
    public ResponseData queryPageUserGroups(@RequestBody(required = false) RequestData<PageVo> requestData){
        return iFriendGroupService.queryPageUserGroups(requestData.getData());
    }

    @RequestMapping(value="/querylabels",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取标签列表",response = QueryGroup.class, notes = "获取标签列表", httpMethod = "POST")
    public ResponseData queryLabels(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return iFriendGroupService.queryLabels(requestData);
    }

    @RequestMapping(value="/auditinvitation",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "审核(面对群主 用户)",response = ResponseData.class, notes = "审核", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inviteNo", value = "申请编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "auditState", value = "审核状态(同意:true 拒绝:false)",paramType = "query",defaultValue = ""),
    })
    public ResponseData auditInvitation(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return iFriendGroupService.auditInvitation(requestData);
    }

    @RequestMapping(value="/querypagesearch",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页检索", httpMethod = "POST", notes = "分页检索",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "搜索者编码",paramType = "query",defaultValue = "yh201807101948141454"),
            @ApiImplicitParam(name = "content", value = "检索内容",paramType = "query",defaultValue = "秋"),
            @ApiImplicitParam(name = "type", value = "检索类型(用户:0 群聊:1)",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public ResponseData queryPageSearch(@RequestBody(required = false) RequestData<PageSearchVo> requestData){
        return iFriendGroupService.queryPageSearch(requestData.getData());
    }

    @RequestMapping(value = "/querygroupdetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取群详情", httpMethod = "POST", notes = "获取群详情", response = QueryGroupDetails.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群编码", paramType = "query", defaultValue = "54329887227905"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = "yh201807091742161017"),
    })
    public ResponseData queryGroupDetails(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return iFriendGroupService.queryGroupDetails(requestData);
    }

    @RequestMapping(value = "/querypagesearchinviteuser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页检索邀请群成员列表", httpMethod = "POST", notes = "分页检索邀请群成员列表", response = QueryUser.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "搜索者编码",paramType = "query",defaultValue = "yh201807101948141454"),
            @ApiImplicitParam(name = "groupNo", value = "群编码",paramType = "query",defaultValue = "54329887227905"),
            @ApiImplicitParam(name = "content", value = "检索内容",paramType = "query",defaultValue = "秋"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public ResponseData queryPageSearchInviteUser(@RequestBody(required = false) RequestData<SearchInviteUserVo> vo) {
        return iFriendGroupService.queryPageSearchInviteUser(vo.getData());
    }
}
