package net.sunwukong.www.marketing.client.web;

import io.swagger.annotations.*;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.util.ParseTools;
import net.sunwukong.www.marketing.client.service.PayServiceClinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付模块
 */

@RestController
@RequestMapping("/v1/paybusiness")
@Api(tags = "支付模块", description = "（支付模块）")
public class PayBusinessController extends BaseController {

    @Autowired
    PayServiceClinetService payServiceClinetService;

    /**
     * 蟠桃充值业务-支付宝接口（或者）微信接口（APP支付）
     */
    @RequestMapping(value = "/payWxAli", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "蟠桃充值业务-支付宝接口（或者）微信接口（APP支付）", httpMethod = "POST", notes = "蟠桃充值业务", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amountSum", value = "支付的金额,前段把金额进行判断只准数据两位小数", paramType = "query"),
            @ApiImplicitParam(name = "payType", value = "充值类型：1：表示支付宝，2表示微信", paramType = "query"),
            @ApiImplicitParam(name = "codeContent", value = "支付这笔费用的类型说明", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户ID（用户编码）", paramType = "query"),
            @ApiImplicitParam(name = "peachNumber", value = "充值的蟠桃的数量", paramType = "query"),
            @ApiImplicitParam(name = "givPeachNumber", value = "赠送的蟠桃数量", paramType = "query")
    })
    public ResponseData payBusinessApp(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        ResponseData responseData = payServiceClinetService.addPayDataInfo(requestData);
        return responseData;
    }

    /**
     * 精选的支付
     */
    @RequestMapping(value = "/siftpayc", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
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
    public ResponseData payTicketVolumeC(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return payServiceClinetService.payTick(requestData);
    }

    /**
     * web支付
     * 支付者参数：金额 ，当前用户的用户编码，购买单号，支付的蟠桃，购买类型
     * 返利获得者的参数：当前用户的编码，返利的金额
     */
    @RequestMapping(value = "/webpayc", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "精选购买劵业务-支付宝接口（或者）微信接口（WEB支付）", httpMethod = "POST", notes = "精选购买劵业务", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "khIp", value = "客户端IP", paramType = "query"),
            @ApiImplicitParam(name = "buyNo", value = "购买的单号", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "shareNo", value = "分享的单号", paramType = "query"),
            @ApiImplicitParam(name = "payAmount", value = "支付金额", paramType = "query"),
            @ApiImplicitParam(name = "peachSum", value = "支付的蟠桃数量", paramType = "query"),
            @ApiImplicitParam(name = "shareAmount", value = "分享者返利的金额", paramType = "query"),
            @ApiImplicitParam(name = "payType", value = "充值类型：1：表示支付宝，2表示微信", paramType = "query")
    })
    public ResponseData payWebC(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        return payServiceClinetService.payTickWeb(requestData);
    }

    /**
     * 蟠桃支付支付宝的异地链接
     */
    @RequestMapping(value = "/alipayurlp")
    public String alipayAppUrl(HttpServletRequest request) {
        System.out.println("支付宝回调消费者");
        String datainfo = "fail";
        // 获取解析的结果
        RequestData<Map<String, String>> requestData = ParseTools.analyticAlipay(request);
        try {
            datainfo = payServiceClinetService.getAlipayInfo(requestData);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("异步回调返回的参数" + datainfo);
        return datainfo;
    }

    /**
     * 蟠桃支付微信支付的异地链接
     */
    @RequestMapping(value = "/weixinpayurlp")
    public void weixinPayAppUrl(HttpServletRequest request, HttpServletResponse response) {
        String resXml = "";
        try {
            Map<String, String> datamsgmap = ParseTools.wxParsing(request, response);
            //判断签名是否正确
            if (datamsgmap != null) {
                for (Object keyValue : datamsgmap.keySet()) {
                    System.out.println("----------" + keyValue + "=" + datamsgmap.get(keyValue));
                }
                // 这里是支付成功
                if ("SUCCESS".equals((String) datamsgmap.get("result_code"))) {
                    // 支付成功修改当前充值蟠桃的订单的支付状态和添加微信支付的支付订单号
                    System.out.println("微信端返回map" + datamsgmap.toString());
                    System.out.println("支付成功，修改支付状态");
                    // 获取微信支付的订单号
                    String wxorderno = datamsgmap.get("out_trade_no");
                    // 金钱
                    String totalfee = datamsgmap.get("total_fee");
                    // 微信订单号
                    String transactionid = datamsgmap.get("transaction_id");
                    System.out.println("微信支付的订单：" + wxorderno + "金钱" + "微信订单：" + transactionid);
                    Map<String, String> requestmap = new HashMap<>();
                    requestmap.put("wxorderno", wxorderno);
                    requestmap.put("totalfee", totalfee);
                    requestmap.put("transactionid", transactionid);
                    RequestData<Map<String, String>> requestDataMap = new RequestData<>();
                    requestDataMap.setData(requestmap);
                    String redata = payServiceClinetService.getWeixinPayInfo(requestDataMap);
                    if (redata.equals("success")) {
                        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    } else {
                        resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";
                    }
                } else {
                    System.out.println("支付失败,错误信息：" + datamsgmap.get("err_code"));
                    // 支付失败修改当前充值蟠桃的订单的支付状态（支付失败的状态）
                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                }
            } else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[通知签名验证失败]]></return_msg>" + "</xml> ";
                System.out.println("通知签名验证失败");
            }
            //处理业务完毕返回参数
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 精选支付宝的的异步链接
     */
    @RequestMapping(value = "/alipayurlpsift")
    public String alipayAppSiftUrl(HttpServletRequest request) {
        System.out.println("精选支付宝回调消费者");
        String datainfo = "fail";
        // 获取解析的结果
        RequestData<Map<String, String>> requestData = ParseTools.analyticAlipay(request);
        try {
            datainfo = payServiceClinetService.getAlipaySiftInfo(requestData);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("异步回调返回的参数" + datainfo);
        return datainfo;
    }

    /**
     * 微信精选支付的异步回调方式
     */
    @RequestMapping(value = "/weixinpayurlsfit")
    public void weixinPayAppUrlSfit(HttpServletRequest request, HttpServletResponse response) {
        String resXml = "";
        try {
            Map<String, String> datamsgmap = ParseTools.wxParsing(request, response);
            //判断签名是否正确
            if (datamsgmap != null) {
                for (Object keyValue : datamsgmap.keySet()) {
                    System.out.println("----------" + keyValue + "=" + datamsgmap.get(keyValue));
                }
                // 这里是支付成功
                if ("SUCCESS".equals((String) datamsgmap.get("result_code"))) {
                    // 支付成功修改当前充值蟠桃的订单的支付状态和添加微信支付的支付订单号
                    System.out.println("微信端返回map" + datamsgmap.toString());
                    System.out.println("支付成功，修改支付状态");
                    // 获取微信支付的订单号
                    String wxorderno = datamsgmap.get("out_trade_no");
                    // 金钱
                    String totalfee = datamsgmap.get("total_fee");
                    // 微信订单号
                    String transactionid = datamsgmap.get("transaction_id");
                    System.out.println("微信支付的订单：" + wxorderno + "金钱" + "微信订单：" + transactionid);
                    Map<String, String> requestmap = new HashMap<>();
                    requestmap.put("wxorderno", wxorderno);
                    requestmap.put("totalfee", totalfee);
                    requestmap.put("transactionid", transactionid);
                    RequestData<Map<String, String>> requestDataMap = new RequestData<>();
                    requestDataMap.setData(requestmap);
                    String redata = payServiceClinetService.getWeixinPayInfoSfit(requestDataMap);
                    if (redata.equals("success")) {
                        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    } else {
                        resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";
                    }
                } else {
                    System.out.println("支付失败,错误信息：" + datamsgmap.get("err_code"));
                    // 支付失败修改当前充值蟠桃的订单的支付状态（支付失败的状态）
                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                }
            } else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[通知签名验证失败]]></return_msg>" + "</xml> ";
                System.out.println("通知签名验证失败");
            }
            //处理业务完毕返回参数
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付宝的web异步调用接口
     * 参数：获取分享单号查询应该获利的用户编码
     */
    @RequestMapping(value = "/alipaweburl")
    public String alipWebftUrl(HttpServletRequest request) {
        System.out.println("支付宝的网支付");
        String datainfo = "fail";
        // 获取解析的结果
        RequestData<Map<String, String>> requestData = ParseTools.analyticAlipay(request);
        try {
            datainfo = payServiceClinetService.getAlipayWeb(requestData);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("异步回调返回的参数" + datainfo);
        return datainfo;
    }

    /**
     * 微信 h5的支付回调的方法
     */
    @RequestMapping(value = "/weixinpaywebur")
    public void weixinPayWebUr(HttpServletRequest request, HttpServletResponse response) {
        String resXml = "";
        try {
            Map<String, String> datamsgmap = ParseTools.wxParsing(request, response);
            //判断签名是否正确
            if (datamsgmap != null) {
                for (Object keyValue : datamsgmap.keySet()) {
                    System.out.println("----------" + keyValue + "=" + datamsgmap.get(keyValue));
                }
                // 这里是支付成功
                if ("SUCCESS".equals((String) datamsgmap.get("result_code"))) {
                    // 支付成功修改当前充值蟠桃的订单的支付状态和添加微信支付的支付订单号
                    System.out.println("微信端返回map" + datamsgmap.toString());
                    System.out.println("支付成功，修改支付状态");
                    // 获取微信支付的订单号
                    String wxorderno = datamsgmap.get("out_trade_no");
                    // 金钱
                    String totalfee = datamsgmap.get("total_fee");
                    // 微信订单号
                    String transactionid = datamsgmap.get("transaction_id");
                    System.out.println("微信支付的订单：" + wxorderno + "金钱" + "微信订单：" + transactionid);
                    Map<String, String> requestmap = new HashMap<>();
                    requestmap.put("wxorderno", wxorderno);
                    requestmap.put("totalfee", totalfee);
                    requestmap.put("transactionid", transactionid);
                    RequestData<Map<String, String>> requestDataMap = new RequestData<>();
                    requestDataMap.setData(requestmap);
                    String redata = payServiceClinetService.getWeixinWeb(requestDataMap);
                    if (redata.equals("success")) {
                        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    } else {
                        resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";
                    }
                } else {
                    System.out.println("支付失败,错误信息：" + datamsgmap.get("err_code"));
                    // 支付失败修改当前充值蟠桃的订单的支付状态（支付失败的状态）
                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                }
            } else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[通知签名验证失败]]></return_msg>" + "</xml> ";
                System.out.println("通知签名验证失败");
            }
            //处理业务完毕返回参数
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
