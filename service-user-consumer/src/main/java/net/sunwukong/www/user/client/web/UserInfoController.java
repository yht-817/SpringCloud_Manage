package net.sunwukong.www.user.client.web;

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
 * 说明:用户控制层
 *
 * @author Mick
 * CreateDate 2018/6/9/009 22:15
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/v1/user")
@Api(tags = "用户API控制", description = "")
public class UserInfoController extends BaseController {

    @RequestMapping(value = "/checkaccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "验证用户是否存在接口", httpMethod = "POST", notes = "验证用户是否存在接口", response = ResponseData.class, hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "account", value = "账户号", paramType = "query", defaultValue = "18728578786"),
    })
    public ResponseData checkAccount(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userInfoService.checkAccount(requestData);
    }

    @RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用户注册接口", httpMethod = "POST", notes = "用户注册接口", response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "account", value = "账户号", paramType = "query"),
            @ApiImplicitParam(required = true, name = "accountType", value = "账户类型 1手机号 2邮箱", paramType = "query"),
            @ApiImplicitParam(required = true, name = "code", value = "验证码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "nikeName", value = "昵称", paramType = "query", defaultValue = "测试昵称"),
            @ApiImplicitParam(required = true, name = "password", value = "密码", paramType = "query", defaultValue = "admin"),
            @ApiImplicitParam(name = "phoneType", value = "设备类型", paramType = "query", defaultValue = "IOS"),
            @ApiImplicitParam(name = "deviceNo", value = "设备编码", paramType = "query", defaultValue = "暂无"),
    })
    public ResponseData register(@RequestBody(required = false) RequestData<RegisterInput> requestData) {
        return userInfoService.register(requestData);

    }

    @RequestMapping(value = "/getvolume", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分享注册购买卷", httpMethod = "POST", notes = "用户注册接口", response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "account", value = "账户号", paramType = "query"),
            @ApiImplicitParam(required = true, name = "accountType", value = "账户类型 1手机号 2邮箱", paramType = "query"),
            @ApiImplicitParam(required = true, name = "code", value = "验证码", paramType = "query")
    })
    public ResponseData getVolume(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return userInfoService.getVolume(requestData);

    }

    @RequestMapping(value = "/addUserPhoneNo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "精选购买添加手机号", httpMethod = "POST", notes = "用户注册接口", response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "phoneNo", value = "电话号码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "code", value = "验证码", paramType = "query")
    })
    public ResponseData addUserPhoneNo(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return userInfoService.addUserPhoneNo(requestData);

    }

    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用户登录接口", httpMethod = "POST", notes = "用户登录接口", response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "account", value = "账户号", paramType = "query", defaultValue = "18728578786"),
            @ApiImplicitParam(required = true, name = "password", value = "密码", paramType = "query", defaultValue = "admin"),
            @ApiImplicitParam(name = "phoneType", value = "设备类型", paramType = "query", defaultValue = "ios"),
            @ApiImplicitParam(name = "deviceNo", value = "设备编码", paramType = "query", defaultValue = "暂无"),
    })
    public ResponseData login(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userInfoService.login(requestData);
    }

    @RequestMapping(value = "/otherlogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "第三方登录接口", httpMethod = "POST", notes = "第三方登录接口", response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sexNo", value = "性别 男：10010001 女：10010002", paramType = "query"),
            @ApiImplicitParam(name = "nikeName", value = "昵称", paramType = "query"),
            @ApiImplicitParam(name = "userHead", value = "头像地址", paramType = "query"),
            @ApiImplicitParam(name = "otherAppId", value = "第三方ID", paramType = "query"),
            @ApiImplicitParam(name = "otherAppName", value = "第三方类型(微信,QQ,微博)", paramType = "query"),
            @ApiImplicitParam(name = "phoneType", value = "设备类型(安卓：10120001 苹果：10120002)", paramType = "query", defaultValue = "ios"),
            @ApiImplicitParam(name = "deviceNo", value = "设备编码", paramType = "query", defaultValue = "暂无"),
    })
    public ResponseData otherLogin(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userInfoService.otherLogin(requestData);
    }

    @RequestMapping(value = "/updatedata", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "修改个人资料", httpMethod = "POST", notes = "修改个人资料", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", paramType = "query"),
            @ApiImplicitParam(name = "userHead", value = "用户头像（base64字符串）", paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "query"),
            @ApiImplicitParam(name = "nikeName", value = "用户昵称", paramType = "query"),
            @ApiImplicitParam(name = "sexNo", value = "性别 (男:10010001 女:10010002)", paramType = "query"),
            @ApiImplicitParam(name = "phoneNo", value = "电话", paramType = "query"),
            @ApiImplicitParam(name = "mailboxNo", value = "邮箱", paramType = "query"),
            @ApiImplicitParam(name = "userType", value = "用户类型(普通用户:10020001 运营官:10020002 服务机构:10020003 个人服务:10020004)", paramType = "query"),
            @ApiImplicitParam(name = "userState", value = "状态(正常:10030001 注销:10030002)", paramType = "query"),
    })
    public ResponseData updateUserInfo(@RequestBody(required = false) RequestData<UserInfo> requestData) {
        return userInfoService.updateUserInfo(requestData);
    }

    @RequestMapping(value = "/updateuserhead", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "修改用户头像", httpMethod = "POST", notes = "修改用户头像", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", paramType = "query"),
            @ApiImplicitParam(name = "userHead", value = "头像地址", paramType = "query"),
    })
    public ResponseData updateUserHead(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userInfoService.updateUserHead(requestData);
    }

    @RequestMapping(value = "/resetpassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "重置密码", httpMethod = "POST", notes = "重置密码", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户账号", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "新用户密码", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", paramType = "query"),
    })
    public ResponseData resetpassword(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userInfoService.resetPassword(requestData);
    }

    @RequestMapping(value = "/findbyiduserinfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "通过用户ID查询用户信息", httpMethod = "POST", notes = "通过用户ID查询用户信息", response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", paramType = "query"),
    })
    public ResponseData findByIdUserInfo(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userInfoService.findByIdUserInfo(requestData);
    }

    @RequestMapping(value = "/updateuserevaluate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "修改用户评分值", httpMethod = "POST", notes = "修改用户评分值", response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "evaluateNo", value = "评分（+87/-52）", paramType = "query"),
    })
    public ResponseData updateUserEvaluate(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userInfoService.updateUserEvaluate(requestData);
    }

    @RequestMapping(value = "/wechatlogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "微信登陆", httpMethod = "POST", notes = "微信登陆", response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code代码", paramType = "query"),
    })
    public ResponseData weChatLogin(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userInfoService.weChatLogin(requestData);
    }

    @RequestMapping(value = "/onlineuser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用户在线的统计", httpMethod = "POST", notes = "用户在线的统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "logCode", value = "经度", paramType = "query"),
            @ApiImplicitParam(name = "latCode", value = "纬度", paramType = "query"),
    })
    public ResponseData userStates(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userInfoService.userStates(requestData);
    }
}
