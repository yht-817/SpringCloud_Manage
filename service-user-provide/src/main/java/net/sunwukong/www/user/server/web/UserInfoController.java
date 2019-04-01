package net.sunwukong.www.user.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserInfo;
import net.sunwukong.www.user.input.RegisterInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 说明:
 *
 * @author Mick
 * CreateDate 2018/6/6 14:07
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户登录/注册API", description = "用户服务提供")
public class UserInfoController extends BaseController {

    /*@RequestMapping(value="/checkaccount",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "验证用户是否存在接口", httpMethod = "POST", notes = "验证用户是否存在接口",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true,name = "account", value = "账户号",paramType = "query"),
    })
    public ResponseData checkAccount(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iUserInfoService.checkAccount(data.get("account"));
    }*/

    @RequestMapping(value="/register",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "用户注册接口", httpMethod = "POST", notes = "用户注册接口",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true,name = "account", value = "账户号",paramType = "query"),
            @ApiImplicitParam(required = true,name = "accountType", value = "账户类型 1手机号 2邮箱",paramType = "query"),
            @ApiImplicitParam(required = true,name = "code", value = "验证码",paramType = "query"),
            @ApiImplicitParam(required = true,name = "nikeName", value = "昵称",paramType = "query"),
            @ApiImplicitParam(required = true,name = "password", value = "密码",paramType = "query"),
            @ApiImplicitParam(name = "phoneType", value = "设备类型",paramType = "query"),
            @ApiImplicitParam(name = "deviceNo", value = "设备编码",paramType = "query"),
    })
    public ResponseData register(@RequestBody(required = false) RequestData<RegisterInput> requestData){
        return iUserInfoService.register(requestData.getData());
    }

    @RequestMapping(value = "/getvolume", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分享注册购买卷", httpMethod = "POST", notes = "用户注册接口", response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "account", value = "账户号", paramType = "query"),
            @ApiImplicitParam(required = true, name = "accountType", value = "账户类型 1手机号 2邮箱", paramType = "query"),
            @ApiImplicitParam(required = true, name = "code", value = "验证码", paramType = "query")
    })
    public ResponseData getVolume(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iUserInfoService.getVolume(data.get("account"),data.get("accountType"),data.get("code"));

    }

    @RequestMapping(value = "/adduserphoneno", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "精选购买添加手机号", httpMethod = "POST", notes = "用户注册接口", response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "phoneNo", value = "电话号码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "code", value = "验证码", paramType = "query")
    })
    public ResponseData addUserPhoneNo(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iUserInfoService.addUserPhoneNo(data.get("userNo"),data.get("phoneNo"),data.get("code"));

    }

    @RequestMapping(value="/login",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "用户登录接口", httpMethod = "POST", notes = "用户登录接口",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true,name = "account", value = "账户号",paramType = "query"),
            @ApiImplicitParam(required = true,name = "password", value = "密码",paramType = "query"),
            @ApiImplicitParam(name = "phoneType", value = "设备类型",paramType = "query"),
            @ApiImplicitParam(name = "deviceNo", value = "设备编码",paramType = "query"),
    })
    public ResponseData login(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iUserInfoService.login(data.get("account"),data.get("password"),data.get("phoneType"),data.get("deviceNo"));
    }

    @RequestMapping(value="/otherlogin",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "第三方登录接口", httpMethod = "POST", notes = "第三方登录接口",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sexNo", value = "性别 男：10010001 女：10010002",paramType = "query"),
            @ApiImplicitParam(name = "nikeName", value = "昵称",paramType = "query"),
            @ApiImplicitParam(name = "userHead", value = "头像地址",paramType = "query"),
            @ApiImplicitParam(name = "otherAppId", value = "第三方ID",paramType = "query"),
            @ApiImplicitParam(name = "otherAppName", value = "第三方类型(微信:10320001 QQ:10320002 微博:10320003)",paramType = "query"),
            @ApiImplicitParam(name = "phoneType", value = "设备类型(安卓：10120001 苹果：10120002)",paramType = "query",defaultValue = "ios"),
            @ApiImplicitParam(name = "deviceNo", value = "设备编码",paramType = "query",defaultValue = "暂无"),
    })
    public ResponseData otherLogin(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return iUserInfoService.otherLogin(requestData.getData());
    }

    @RequestMapping(value="/updatedata",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "修改个人资料", httpMethod = "POST", notes = "修改个人资料",response = ResponseData.class)
    public ResponseData updateUserInfo(@RequestBody(required = false) RequestData<UserInfo> requestData){
        return iUserInfoService.updateUserInfo(requestData.getData());
    }

    @RequestMapping(value = "/updateuserhead", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "修改用户头像", httpMethod = "POST", notes = "修改用户头像",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID",paramType = "query"),
            @ApiImplicitParam(name = "userHead", value = "头像地址",paramType = "query"),
    })
    public ResponseData updateUserHead(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iUserInfoService.updateUserHead(data.get("id"),data.get("userHead"));
    }

    @RequestMapping(value = "/resetpassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "重置密码", httpMethod = "POST", notes = "重置密码",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户账号",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "新用户密码",paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码",paramType = "query"),
    })
    public ResponseData resetpassword(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iUserInfoService.resetPassword(data.get("account"),data.get("password"),data.get("code"));
    }

    @RequestMapping(value = "/findbyiduserinfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "通过用户ID查询用户信息", httpMethod = "POST", notes = "通过用户ID查询用户信息",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID",paramType = "query"),
    })
    public ResponseData findByIdUserInfoLogin(@RequestBody RequestData<Map<String,String>> requestData) {
        return iUserInfoService.findByIdUserInfoLogin(requestData.getData());
    }

    @RequestMapping(value = "/updateuserevaluate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "修改用户评分值", httpMethod = "POST", notes = "修改用户评分值",response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "evaluateNo", value = "评分",paramType = "query"),
    })
    public ResponseData updateUserEvaluate(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return iUserInfoService.updateUserEvaluate(requestData.getData());
    }

    // 用户在线状态的统计
    @RequestMapping(value = "/userstates", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用户在线的统计", httpMethod = "POST", notes = "用户在线的统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "logCode", value = "经度", paramType = "query"),
            @ApiImplicitParam(name = "latCode", value = "纬度", paramType = "query"),
    })
    public ResponseData userStates(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> userdata = requestData.getData();
        return iUserInfoService.addUserState(userdata);
    }

}
