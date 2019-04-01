package net.sunwukong.www.user.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.input.RegisterInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户体现的方式
 */
@RestController
@RequestMapping(value = "/reflect")
@Api(tags = "用户提现的业务控制层", description = "UserReflectsController")
public class UserReflectsController extends BaseController {

    /**
     * 绑定用户的账号
     */
    @RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用户绑定提现账号", httpMethod = "POST", notes = "用户绑定提现账号", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userName", value = "开户行姓名", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userPhone", value = "用户电话号码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAddress", value = "开户行地址", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAccount", value = "银行卡账号", paramType = "query"),
            @ApiImplicitParam(required = true, name = "bankName", value = "开户银行名称", paramType = "query"),
    })
    public ResponseData register(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> reflectInfo = requestData.getData();
        return iUserReflects.addReflectMeth(reflectInfo);
    }

    /**
     * 查看用户的是否存在账号和用户现在可提现余额
     */
    @RequestMapping(value = "/countuserinfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查看用户是否存在账号，存在直接进行体现处理，没有进行信息填写", httpMethod = "POST", notes = "查看用户是否存在账号，存在直接进行体现处理，没有进行信息填写", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
    })
    public ResponseData userInfoR(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> reflectInfo = requestData.getData();
        // 查看用户是否存在提现的信息
        boolean cz = iUserReflects.seeUserInfoR(reflectInfo);
        BigDecimal mony = iUserReflects.getMony(reflectInfo);
        Map<String, Object> msgdata = new HashMap<>();
        msgdata.put("exist", cz);
        msgdata.put("money", mony);
        if (cz) {
            // 查询当前用户的银行卡信息
            Map<String, String> cardNo = iUserReflects.seeCarNo(reflectInfo);
            msgdata.put("carNo", cardNo.get("userAccount"));
            msgdata.put("bankName", cardNo.get("bankName"));
        }
        ResponseData responseData = new ResponseData();
        responseData.setData(msgdata);
        responseData.setCode(200);
        return responseData;
    }

    /**
     * 进行提现申请
     */
    @RequestMapping(value = "/addorder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "提现的金额", httpMethod = "POST", notes = "提现的金额", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "amount", value = "提现金额", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAccount", value = "提现银行卡", paramType = "query"),
    })
    public ResponseData addOrder(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> reflectInfo = requestData.getData();
        return iUserReflects.addOrder(reflectInfo);
    }

    /**
     * 解绑银行卡
     */
    @RequestMapping(value = "/removeaccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "解绑银行卡", httpMethod = "POST", notes = "解绑银行卡", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAccount", value = "提现银行卡", paramType = "query"),
    })
    public ResponseData removeAccount(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> reflectInfo = requestData.getData();
        return iUserReflects.removeAccount(reflectInfo);
    }


}
