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
 * 用户提现的控制层
 */
@RestController
@RequestMapping(value = "/v1/reflects")
@Api(tags = "用户提现的业务控制层", description = "UserReflectsController")
public class UserReflectsController extends BaseController {

    @RequestMapping(value = "/registers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "绑定提现银行卡号", httpMethod = "POST", notes = "绑定提现银行卡号", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userName", value = "开户行姓名", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userPhone", value = "用户电话号码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAddress", value = "开户行地址", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAccount", value = "银行卡账号", paramType = "query"),
            @ApiImplicitParam(required = true, name = "bankName", value = "开户银行名称", paramType = "query"),
    })
    public ResponseData register(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userReflectsService.addUserTxInfo(requestData);
    }

    @RequestMapping(value = "/countuserinfos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查看用户是否存在账号，存在直接进行体现处理，没有进行信息填写", httpMethod = "POST", notes = "查看用户是否存在账号，存在直接进行体现处理，没有进行信息填写", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
    })
    public ResponseData userInfoR(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userReflectsService.userInfoRs(requestData);
    }

    @RequestMapping(value = "/addorders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "提现的金额", httpMethod = "POST", notes = "提现的金额", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "amount", value = "提现金额", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAccount", value = "提现银行卡", paramType = "query"),
    })
    public ResponseData addOrder(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userReflectsService.addOrders(requestData);
    }

    /**
     * 解绑银行卡
     */
    @RequestMapping(value = "/removeaccounts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "解绑银行卡", httpMethod = "POST", notes = "解绑银行卡", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAccount", value = "提现银行卡", paramType = "query"),
    })
    public ResponseData removeAccount(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return userReflectsService.removeAccounts(requestData);
    }


}