package net.sunwukong.www.marketing.server.web;

import com.sdkinfo.www.log.StaticLog;
import com.sun.xml.bind.v2.TODO;
import io.swagger.annotations.*;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.server.service.IPayApplicationService;
import net.sunwukong.www.marketing.server.util.AliPayToolsP;
import net.sunwukong.www.marketing.server.util.AliPayUtil;
import net.sunwukong.www.marketing.server.util.UrlUtil;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 支付接口
 * 支持服务类型：微信、支付宝
 * 接口使用说明：根据支付类型（type）调用不同的方法
 * app sdk 调用APP 进行 支付
 */
@RestController
@RequestMapping(value = "/pay")
@Api(tags = "支付模块", description = "（支付模块）")
public class PayApplicationController {
    @Autowired
    private IPayApplicationService iPayApplicationService;

    private static final Logger log = LoggerFactory.getLogger(PayApplicationController.class);

    /**
     * 蟠桃充值业务-支付宝接口（或者）微信接口（APP支付）
     */
    @RequestMapping(value = "/peachalipay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "蟠桃充值业务-支付宝接口（或者）微信接口（APP支付）", httpMethod = "POST", notes = "蟠桃充值业务", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amount", value = "支付的金额,前段把金额进行判断只准数据两位小数", paramType = "query"),
            @ApiImplicitParam(name = "paytype", value = "充值类型：1：表示支付宝，2表示微信", paramType = "query"),
            @ApiImplicitParam(name = "coding", value = "支付这笔费用的类型说明", paramType = "query"),
            @ApiImplicitParam(name = "userno", value = "用户ID（用户编码）", paramType = "query"),
            @ApiImplicitParam(name = "peachnumber", value = "充值的蟠桃的数量", paramType = "query"),
            @ApiImplicitParam(name = "givpeachnumber", value = "赠送的蟠桃数量", paramType = "query")
    })
    public ResponseData peachAlipay(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        Map<String, String> mapdata = requestData.getData();
        System.out.println(mapdata.toString());
        String paytype = mapdata.get("payType");
        String amount = mapdata.get("amountSum");
        String coding = mapdata.get("codeContent");
        String userno = mapdata.get("userNo");
        String peachnumber = mapdata.get("peachNumber");
        String givpeachnumber = mapdata.get("givPeachNumber");
        System.out.println("支付类型：" + paytype + "金额：" + amount + "标题：" + coding + "用户编码：" + userno + "充值蟠桃：" + peachnumber + "赠送蟠桃：" + givpeachnumber);
        if (amount != null && amount != "" && coding != null && coding != "" && userno != null && userno != ""
                && peachnumber != null && peachnumber != "" && givpeachnumber != null && givpeachnumber != ""
                && paytype != null && paytype != "") {
            // 充值的订单编号
            String recharge_no = UrlUtil.ordernumber();
            // 进行支付业务处理
            ResponseData add_peachnumber_pay = iPayApplicationService.add_Peachnumber_Pay(amount, coding, userno, peachnumber,
                    givpeachnumber, recharge_no, paytype);
            return add_peachnumber_pay;
        } else {
            responseData.setData("参数不能为空");
            responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return responseData;
        }
    }

    /**
     * APP 精选票卷支付接口
     * 蟠桃支付和现金支付
     */
    @RequestMapping(value = "/siftpay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "精选购买劵业务-支付宝接口（或者）微信接口（APP支付）", httpMethod = "POST", notes = "精选购买劵业务", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "payNo", value = "购买单号", paramType = "query"),
            @ApiImplicitParam(name = "payAmount", value = "支付金额", paramType = "query"),
            @ApiImplicitParam(name = "peachSum", value = "支付的蟠桃数量", paramType = "query"),
            @ApiImplicitParam(name = "payType", value = "充值类型：1：表示支付宝，2表示微信", paramType = "query")
    })
    public ResponseData payTicketVolume(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        int changeMode = 10050011;
        String changeRemark = "精选券APP购买";
        // 获取精选支付信息
        String payNo = requestData.getData().get("payNo");
        String userNo = requestData.getData().get("userNo");
        String payType = requestData.getData().get("payType");
        String payAmount = requestData.getData().get("payAmount");
        String peachSum = requestData.getData().get("peachSum");
        log.error("购买单号：" + payNo + "用户编码：" + userNo + "支付类型：" + payType + "支付金额：" + payAmount + "蟠桃的支付数量：" + peachSum);
        if (userNo != null && userNo != "" && payNo != null && payNo != "" && payType != null && payType != "" && payAmount != null && payAmount != "" && peachSum != null && peachSum != "") {
            // 生成日期格式
            String date = DataBaseTool.createDate();
            // 1.0 表示没有使用了蟠桃抵消金额
            if (Integer.valueOf(peachSum) == 0) {
                int insertTxlog = iPayApplicationService.update_TxInfo(DataBaseTool.createId(), userNo, changeMode, peachSum, date, payNo, changeRemark);
                if (insertTxlog > 0) {
                    // 1.1.1 返回支付方的信息然后APP调用进行支付
                    ResponseData datamag = iPayApplicationService.add_siftPay(userNo, payNo, payType, payAmount);
                    return datamag;
                }
                responseData.setData("亲!请稍后重试。");
                responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return responseData;
            }
            // 2.0 用户使用蟠桃进行抵消金额
            else {
                BigDecimal usersumcoin = iPayApplicationService.find_UserCoin(userNo);
                log.error(userNo + "当前用户的蟠桃数量:" + usersumcoin);
                BigDecimal peachsum = new BigDecimal(peachSum);
                // 3.0 当用户表蟠桃大于抵消的数进行直接抵消
                if (usersumcoin.compareTo(peachsum) == 1) {
                    // 3.1 进行用户的资金变动的日志记录
                    int insertTxlog = iPayApplicationService.update_TxInfo(DataBaseTool.createId(), userNo, changeMode, peachSum, date, payNo, changeRemark);
                    if (insertTxlog > 0) {
                        // 3.2 修改下单时蟠桃的使用状态
                        iPayApplicationService.updatePeach(payNo);
                        // 1.1.1 返回支付方的信息然后APP调用进行支付
                        ResponseData datamag = iPayApplicationService.add_siftPay(userNo, payNo, payType, payAmount);
                        return datamag;
                    }
                    responseData.setData("亲!请稍后重试。");
                    responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                    return responseData;
                }
                // 4.0 当小于的时候进行比较看剩余的金额是否与抵消的金额相等
                else {
                    // 4.1 抵消的金额和剩余的金额相等
                    if (usersumcoin.compareTo(peachsum) == 0) {
                        // 4.1 进行用户的资金变动的日志记录
                        int insertTxlog = iPayApplicationService.update_TxInfo(DataBaseTool.createId(), userNo, changeMode, peachSum, date, payNo, changeRemark);
                        if (insertTxlog > 0) {
                            // 4.3 修改下单时蟠桃的使用状态
                            iPayApplicationService.updatePeach(payNo);
                            // 1.1.1 返回支付方的信息然后APP调用进行支付
                            ResponseData datamag = iPayApplicationService.add_siftPay(userNo, payNo, payType, payAmount);
                            return datamag;
                        }
                        responseData.setData("亲!请稍后重试。");
                        responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                        return responseData;
                    }
                    // 5.0 当剩余的金额与抵消金额不相等时，把提现的金额进行抵消
                    else {
                        // 5.1 查询提现字段的余额
                        BigDecimal txye = iPayApplicationService.find_TxCoin(userNo);
                        if (txye.compareTo(peachsum) == 1) {
                            // 5.2 进行用户的资金变动的日志记录
                            int insertTxlog = iPayApplicationService.update_TxInfo(DataBaseTool.createId(), userNo, changeMode, peachSum, date, payNo, changeRemark);
                            if (insertTxlog > 0){
                                // 5.3 修改下单时蟠桃的使用状态
                                iPayApplicationService.updatePeach(payNo);
                                // 1.1.1 返回支付方的信息然后APP调用进行支付
                                ResponseData datamag = iPayApplicationService.add_siftPay(userNo, payNo, payType, payAmount);
                                return datamag;
                            }
                            responseData.setData("亲!请稍后重试。");
                            responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                            return responseData;
                        }
                        // 6.0 说明提现金额也不足，把蟠桃相加成现金进行支付
                        else {
                            // 6.1 把抵消蟠桃进行现金相加
                            String payment_money = String.valueOf(Integer.valueOf(payAmount) + Integer.valueOf(peachSum));
                            ResponseData datamag = iPayApplicationService.add_siftPay(userNo, payNo, payType, payment_money);
                            return datamag;
                        }
                    }
                }
            }
        } else {
            responseData.setData("参数不能为空");
            responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return responseData;
        }
    }

    /**
     * WEB 网站支付接口调用（微信支付宝公用）
     */
    @RequestMapping(value = "/webpay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "精选购买劵业务-支付宝接口（或者）微信接口（WEB支付）", httpMethod = "POST", notes = "精选购买劵业务", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buyNo", value = "购买的单号", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "shareNo", value = "分享的单号", paramType = "query"),
            @ApiImplicitParam(name = "payAmount", value = "支付金额", paramType = "query"),
            @ApiImplicitParam(name = "peachSum", value = "支付的蟠桃数量", paramType = "query"),
            @ApiImplicitParam(name = "shareAmount", value = "分享者返利的金额", paramType = "query"),
            @ApiImplicitParam(name = "payType", value = "充值类型：1：表示支付宝，2表示微信", paramType = "query")
    })
    public ResponseData payWebPay(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        ResponseData rd = new ResponseData();
        String money = requestData.getData().get("payAmount");
        String coding = "精选优惠券的购买";
        String shareNo = requestData.getData().get("shareNo");
        String userNo = requestData.getData().get("userNo");
        String shareAmount = requestData.getData().get("shareAmount");
        String buyNo = requestData.getData().get("buyNo");
        int type = Integer.valueOf(requestData.getData().get("payType"));
        // 获取客户端IP
        String ip = requestData.getData().get("khIp");
        StaticLog.error("-------------------------------------ip:{}", ip);
        // 添加分享者分享后购买者的支付记录表
        int infono = iPayApplicationService.add_Insert_Share(shareNo, userNo, buyNo, shareAmount);
        // 添加购买者的的日志信息记录表
        int infobuy = iPayApplicationService.add_Insert_Bug(userNo, buyNo, type, money);
        if (infono > 0 && infobuy > 0) {
            String zfbUrl = "https://www.sunwukong.net/marketing-service/api/v1/paybusiness/alipaweburl";
            String wexURL = "https://www.sunwukong.net/marketing-service/api/v1/paybusiness/weixinpaywebur";
            if (type == 1) {
                Map<String, String> webpay = AliPayToolsP.doUnifiedWeb(money, coding, buyNo, zfbUrl);
                if (webpay != null) {
                    rd.setData(webpay.get("sdk_data"));
                } else {
                    rd.setData("支付宝支付失败");
                    rd.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                }
            } else {
                // 微信支付走service层
                Map<String, String> wexmap = iPayApplicationService.getWexMap(money, coding, buyNo, wexURL, ip);
                if (wexmap != null) {
                    rd.setData(wexmap);
                } else {
                    rd.setData("微信支付失败");
                    rd.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            rd.setData("支付失败");
            rd.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        return rd;
    }


    /**
     * 支付宝蟠桃充值业务的回调方法
     */
    @RequestMapping(value = "/appnotifyurl", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "服务器异步通知", httpMethod = "POST", response = ResponseData.class, notes = "APP--支付宝蟠桃充值业务的回调方法")
    public String postPayAsy(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        System.out.println("支付宝异步回调");
        // 获取支付宝异步返回的参数
        Map<String, String> params = null;
        try {
            params = requestData.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (params != null) {
            if (AliPayUtil.alipayNotify(params)) {
                // 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，
                // 校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
                return iPayApplicationService.find_aliPayCallBack(params);
            }
            // 当值返回为false，验证失败
            else {
                System.out.println(">>>>>验证签名失败");
                return "fail";
            }
        } else {
            System.out.println(">>>>>获取数据失败");
            return "fail";
        }
    }

    /**
     * 微信蟠桃充值的异步通信方法
     */
    @RequestMapping(value = "/wxappcoinurl", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "服务器异步通知", httpMethod = "POST", response = ResponseData.class, notes = "APP-微信蟠桃充值的异步通信方法")
    public String wxAppCoinUrl(@RequestBody(required = false) RequestData<Map<String, String>> requestDataMap) {
        Map<String, String> mapdata = requestDataMap.getData();
        // 获取微信支付的订单号
        String wxorderno = mapdata.get("wxorderno");
        // 金钱
        String totalfee = mapdata.get("totalfee");
        // 微信订单号
        String transactionid = mapdata.get("transactionid");
        // 进行修改当前订单的状态
        int upstates = iPayApplicationService.update_States(wxorderno, totalfee, transactionid);
        if (upstates > 0) {
            return "success";
        }
        return "fail";
    }


    /**
     * APP 支付宝支付的精选券购买后异步调用地址
     */
    @RequestMapping(value = "/siftpayinfoa", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "服务器异步通知", httpMethod = "POST", response = ResponseData.class, notes = "APP--支付宝蟠桃充值业务的回调方法")
    public String postSiftPayInfo(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        System.out.println("支付宝异步回调");
        // 获取支付宝异步返回的参数
        Map<String, String> params = null;
        try {
            params = requestData.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (params != null) {
            if (AliPayUtil.alipayNotify(params)) {
                // 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，
                // 校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
                return iPayApplicationService.find_aliPaySiftInfo(params);
            }
            // 当值返回为false，验证失败
            else {
                System.out.println(">>>>>验证签名失败");
                return "fail";
            }
        } else {
            System.out.println(">>>>>获取数据失败");
            return "fail";
        }
    }

    /**
     * 微信精选异步回调的地址
     */
    @RequestMapping(value = "/siftpayinfow", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "服务器异步通知", httpMethod = "POST", response = ResponseData.class, notes = "APP-微信蟠桃充值的异步通信方法")
    public String wxAppCoinUrlSfit(@RequestBody(required = false) RequestData<Map<String, String>> requestDataMap) {
        Map<String, String> mapdata = requestDataMap.getData();
        // 获取微信支付的订单号
        String wxorderno = mapdata.get("wxorderno");
        // 金钱
        String totalfee = mapdata.get("totalfee");
        // 微信订单号
        String transactionid = mapdata.get("transactionid");
        // 进行修改当前订单的状态
        int upstates = iPayApplicationService.update_States_Sfit(wxorderno, totalfee, transactionid);
        if (upstates > 0) {
            return "success";
        }
        return "fail";
    }


    /**
     * 支付宝分享网页支付
     * 作用：分享者获得返利的金额
     */
    @RequestMapping(value = "/webpayinfo", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "服务器异步通知", httpMethod = "POST", response = ResponseData.class, notes = "APP--支付宝蟠桃充值业务的回调方法")
    public String postWebInfo(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        System.out.println("支付宝异步回调");
        // 获取支付宝异步返回的参数
        Map<String, String> params = requestData.getData();
        if (params != null) {
            if (AliPayUtil.alipayNotify(params)) {
                // 修改分享者返利的蟠桃数
                return iPayApplicationService.findWebPayGetUser(params);
            } else {
                System.out.println(">>>>>验证签名失败");
                return "fail";
            }
        } else {
            System.out.println(">>>>>获取数据失败");
            return "fail";
        }
    }

    /**
     * 微信网页支付的异步调用接口
     * 修改获利者蟠桃的数量
     */
    @RequestMapping(value = "/wxweburl", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "服务器异步通知", httpMethod = "POST", response = ResponseData.class, notes = "APP-微信蟠桃充值的异步通信方法")
    public String wxWebUrl(@RequestBody(required = false) RequestData<Map<String, String>> requestDataMap) {
        Map<String, String> mapdata = requestDataMap.getData();
        // 获取微信支付的订单号
        String wxorderno = mapdata.get("wxorderno");
        // 金钱
        String totalfee = mapdata.get("totalfee");
        // 微信订单号
        String transactionid = mapdata.get("transactionid");
        // 修改分享者的获利金额
        int wxpaylog = iPayApplicationService.wxPayInfoU(wxorderno, totalfee, transactionid);
        if (wxpaylog > 0) {
            return "success";
        }
        return "fail";
    }

}