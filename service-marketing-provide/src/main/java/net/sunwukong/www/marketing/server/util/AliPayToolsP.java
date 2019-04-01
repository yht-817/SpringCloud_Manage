package net.sunwukong.www.marketing.server.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝支付的统一接口类
 * <p>
 * 根据业务需要调用当前的支付方法
 **/
public class AliPayToolsP {
    private static final Logger log = LoggerFactory.getLogger(AliPayToolsP.class);

    /**
     * APP支付 统一预下单 支付宝支付的接口
     * 参数说明：1.金额，2.标题说明，3.用户编码，4.订单编号
     */
    public static Map<String, String> doUnifiedOrder(String money, String coding, String orderno, String notifyurl) {
        System.out.println("--------" + coding);
        Map<String, String> map = new HashMap<>();
        // 实例化支付对象
        AlipayClient alipayClient = AlipayConfig.getAlipayClient();
        AlipayTradeAppPayRequest alipayTradeAppPayRequest = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel alipayTradeAppPayModel = new AlipayTradeAppPayModel();
        alipayTradeAppPayModel.setSubject(coding);      // 支付类型（标题）
        alipayTradeAppPayModel.setTotalAmount(money);   // 支付金额
        alipayTradeAppPayModel.setTimeoutExpress("90m");// 超时时间
        alipayTradeAppPayModel.setOutTradeNo(orderno);  // 订单号 （生成）
        alipayTradeAppPayModel.setStoreId(AlipayConfig.STOREID);  // 商户门店编号
        // 业务参数
        alipayTradeAppPayRequest.setBizModel(alipayTradeAppPayModel);
        alipayTradeAppPayRequest.setNotifyUrl(notifyurl);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(alipayTradeAppPayRequest);
            //就是orderString 可以直接给客户端请求，无需再做处理。
            log.info("APP支付 统一下单:" + response.toString());
            map.put("trade_no", response.getTradeNo());
            map.put("sdk_data", response.getBody());//UrlUtil.getURLEncoderString()
            return map;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 支付宝的网站支付接口
     */
    public static Map<String, String> doUnifiedWeb(String money, String coding, String orderno, String notifyurl) {
        System.out.println("支付宝web支付接口进去");
        Map<String, String> map = new HashMap<>();
        // 实例化支付对象
        AlipayClient alipayClient = AlipayConfig.getAlipayClient();
        AlipayTradeWapPayRequest alipayTradeWapPayRequest = new AlipayTradeWapPayRequest();
        AlipayTradeWapPayModel alipayTradeWapPayModel = new AlipayTradeWapPayModel();
        alipayTradeWapPayModel.setSubject(coding);      // 支付类型（标题）
        alipayTradeWapPayModel.setTotalAmount(money);   // 支付金额
        alipayTradeWapPayModel.setTimeoutExpress("90m");// 超时时间
        alipayTradeWapPayModel.setProductCode("");
        alipayTradeWapPayModel.setOutTradeNo(orderno);  // 订单号 （生成）
        alipayTradeWapPayModel.setStoreId(AlipayConfig.STOREID);  // 商户门店编号
        alipayTradeWapPayModel.setStoreId(AlipayConfig.STOREID);  // 商户门店编号
        // 业务参数
        alipayTradeWapPayRequest.setBizModel(alipayTradeWapPayModel);
        alipayTradeWapPayRequest.setNotifyUrl(notifyurl);
        alipayTradeWapPayRequest.setReturnUrl("https://sunwukong.net/h5/components/Success.html");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeWapPayResponse response = alipayClient.pageExecute(alipayTradeWapPayRequest);
            //就是orderString 可以直接给客户端请求，无需再做处理。
            log.info("WEB支付 统一下单:" + response.toString());
            map.put("trade_no", response.getTradeNo());
            map.put("sdk_data", response.getBody());//UrlUtil.getURLEncoderString()
            return map;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 支付宝扫码支付
     * 参数说明：1.订单编号，2.金额，3.门店编号（就是唯一id值），4.标题
     */
    public String codePayment(String money, String coding, String userno, String orderno) {
        AlipayClient alipayClient = AlipayConfig.getAlipayClient();
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();//创建API对应的request类
        AlipayTradePrecreateModel alipayTradePrecreateModel = new AlipayTradePrecreateModel();
        alipayTradePrecreateModel.setSubject(coding);   // 支付类型（标题）
        alipayTradePrecreateModel.setTotalAmount(money);// 金额
        alipayTradePrecreateModel.setOutTradeNo(orderno); // 订单号 （生成）
        alipayTradePrecreateModel.setTimeoutExpress("90m");        // 超时
        alipayTradePrecreateModel.setStoreId(AlipayConfig.STOREID);// 商户门店编号
        // 业务参数
        request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        request.setBizModel(alipayTradePrecreateModel);
        AlipayTradePrecreateResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.print(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return null;
    }

    /**
     * 支付查询接口 所有支付接口使用
     * 参数：order_no 商户后端生产的订单号 或者是 trade_no 支付宝生成的流水号
     */
    public String queryAliPay(String order_no) {
        AlipayClient alipayClient = AlipayConfig.getAlipayClient();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(order_no);
        request.setBizModel(model);
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) { // 查询成功返回success：成功  fail：失败
                System.out.println("调用成功");
                if (response.getCode().equals("10000")) {
                    System.out.println(response.toString());
                    return "success";
                } else if (response.getCode().equals("20000")) {
                    response = alipayClient.execute(request);
                    if (response.getCode().equals("10000")) {
                        return "success";
                    }
                    log.info("查询接口再次重试失败：订单号：-----" + order_no);
                    return "fail";
                } else {
                    return "fail";
                }
            } else {
                log.info("查询接口调用失败：订单号：-----" + order_no);
                return "fail";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 退款接口操作
     * 参数： 1.order_no 商户后端生产的订单号 或者是 trade_no 支付宝生成的流水号两个之间的一个和全部，2.金额
     * <p>
     * 说明：当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，
     * 支付宝将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。 交易超过约定时间
     * （签约时设置的可退款时间）的订单无法进行退款 支付宝退款支持单笔交易分多次退款，多次退款需要提交原支付订单的
     * 商户订单号和设置不同的退款单号。一笔退款失败后重新提交，要采用原来的退款单号。总退款金额不能超过用户实际
     * 支付金额
     */
    public String refundAliPay(String order_no, String trade_no, String amount) {
        AlipayClient alipayClient = AlipayConfig.getAlipayClient();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(order_no);
        model.setRefundAmount(amount);
        request.setBizModel(model);
        try {
            AlipayTradeRefundResponse tradeRefundResponse = alipayClient.execute(request);
            if (tradeRefundResponse.isSuccess()) {
                if (tradeRefundResponse.getCode().equals("10000")) {
                    System.out.println(tradeRefundResponse.toString());
                    return "success";
                } else if (tradeRefundResponse.getCode().equals("20000")) {
                    AlipayTradeRefundResponse response = alipayClient.execute(request);
                    if (response.getCode().equals("10000")) {
                        return "success";
                    }
                    log.info("退款的接口再次重试失败：订单号：-----" + order_no + "支付宝交易号------:" + trade_no + "金额：" + amount);
                    return "fail";
                } else {
                    return "fail";
                }
            } else {
                log.info("退款接口调用失败：订单号：-----" + order_no + "支付宝交易号------:" + trade_no + "金额：" + amount);
                return "fail";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 退款查询的操作
     * 参数: 1.order_no 商户后端生产的订单号 或者是 trade_no 支付宝生成的流水号，2.金额
     * 说明：商户可使用该接口查询自已通过alipay.trade.refund提交的退款请求是否执行成功。 该接口的返回码10000，
     * 仅代表本次查询操作成功，不代表退款成功。如果该接口返回了查询数据，则代表退款成功，如果没有查询到则代表未
     * 退款成功，可以调用退款接口进行重试。重试时请务必保证退款请求号一致。
     */
    public String queryRefundAliPay(String order_no, String trade_no) {
        AlipayClient alipayClient = AlipayConfig.getAlipayClient();
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(order_no);
        model.setOutRequestNo(trade_no);
        request.setBizModel(model);
        try {
            AlipayTradeFastpayRefundQueryResponse fastpayRefundQueryResponse = alipayClient.execute(request);
            if (fastpayRefundQueryResponse.isSuccess()) {
                if (fastpayRefundQueryResponse.getCode().equals("10000")) {
                    System.out.println(fastpayRefundQueryResponse.toString());
                    return "success";
                } else if (fastpayRefundQueryResponse.getCode().equals("20000")) {
                    AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
                    if (response.getCode().equals("10000")) {
                        return "success";
                    }
                    log.info("退款查询接口再次重试失败：订单号：-----" + order_no + "支付宝交易号------:" + trade_no);
                    return "fail";
                } else {
                    return "fail";
                }
            } else {
                log.info("退款查询接口调用失败：订单号：-----" + order_no + "支付宝交易号------:" + trade_no);
                return "fail";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 交易关闭接口
     * 参数：商家后端产生的订单号：order_no，支付宝的交易号：trade_no
     * 说明：用于交易创建后，用户在一定时间内未进行支付，可调用该接口直接将未付款的交易进行关闭。
     */
    public String closeAliPay(String order_no, String trade_no) {
        AlipayClient alipayClient = AlipayConfig.getAlipayClient();
        AlipayTradeCloseRequest alipayTradeCloseRequest = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel model = new AlipayTradeCloseModel();
        model.setOutTradeNo(order_no);
        model.setTradeNo(trade_no);
        alipayTradeCloseRequest.setBizModel(model);
        try {
            AlipayTradeCloseResponse alipayTradeCloseResponse = alipayClient.execute(alipayTradeCloseRequest);
            if (alipayTradeCloseResponse.isSuccess()) {
                if (alipayTradeCloseResponse.getCode().equals("10000")) {
                    System.out.println(alipayTradeCloseResponse.toString());
                    return "success";
                } else if (alipayTradeCloseResponse.getCode().equals("20000")) {
                    AlipayTradeCloseResponse response = alipayClient.execute(alipayTradeCloseRequest);
                    if (response.getCode().equals("10000")) {
                        return "success";
                    }
                    log.info("交易关闭订单再次重试失败：订单号：-----" + order_no + "支付宝交易号------:" + trade_no);
                    return "fail";
                } else {
                    return "fail";
                }
            } else {
                log.info("退款查询接口调用失败：订单号：-----" + order_no + "支付宝交易号------:" + trade_no);
                return "fail";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 交易撤销接口
     * 参数：商家后端产生的订单号：order_no，支付宝的交易号：trade_no
     * 说明：支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，支付宝系统会将此订单关闭；
     * 如果用户支付成功，支付宝系统会将此订单资金退还给用户。 注意：只有发生支付系统超时或者支付结果未知时可调
     * 用撤销，其他正常支付的单如需实现相同功能请调用申请退款API。提交支付交易后调用【查询订单API】，
     * 没有明确的支付结果再调用【撤销订单API】。
     */
    public String cancelAliPay(String order_no, String trade_no) {
        AlipayClient alipayClient = AlipayConfig.getAlipayClient();
        AlipayTradeCancelRequest alipayTradeCancelRequest = new AlipayTradeCancelRequest();
        AlipayTradeCancelModel model = new AlipayTradeCancelModel();
        model.setOutTradeNo(order_no);
        model.setTradeNo(trade_no);
        alipayTradeCancelRequest.setBizModel(model);
        try {
            AlipayTradeCancelResponse alipayTradeCancelResponse = alipayClient.execute(alipayTradeCancelRequest);
            if (alipayTradeCancelResponse.isSuccess()) {
                if (alipayTradeCancelResponse.getCode().equals("10000")) {
                    System.out.println(alipayTradeCancelResponse.toString());
                    return "success";
                } else if (alipayTradeCancelResponse.getCode().equals("20000")) {
                    AlipayTradeCancelResponse response = alipayClient.execute(alipayTradeCancelRequest);
                    if (response.getCode().equals("10000")) {
                        return "success";
                    }
                    log.info("交易撤销接口再次重试失败：订单号：-----" + order_no + "支付宝交易号------:" + trade_no);
                    return "fail";
                } else {
                    return "fail";
                }
            } else {
                log.info("交易撤销接口调用失败：订单号：-----" + order_no + "支付宝交易号------:" + trade_no);
                return "fail";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "fail";
        }
    }


}
