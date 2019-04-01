package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.MsgTitleEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.base.sms.dao.AliSMSDao;
import net.sunwukong.www.base.sms.enums.SMSTemplateEnum;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.ChatServiceMessage;
import net.sunwukong.www.marketing.server.dao.ChoiceInfoMapper;
import net.sunwukong.www.marketing.server.dao.PayinfoMapper;
import net.sunwukong.www.marketing.server.service.IPayApplicationService;
import net.sunwukong.www.marketing.server.util.AliPayToolsP;
import net.sunwukong.www.marketing.server.util.webpay.WXPay;
import net.sunwukong.www.marketing.server.util.webpay.WXPayConfigImpl;
import net.sunwukong.www.marketing.server.util.webpay.WXPayUtil;
import net.sunwukong.www.marketing.server.web.BaseController;
import org.apache.http.HttpStatus;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * service的实现接口
 */

@Service
public class IPayApplicationServiceImpl extends BaseController implements IPayApplicationService {
    @Autowired
    PayinfoMapper payinfoMapper;

    @Autowired
    ChoiceInfoMapper choiceInfoMapper;

    private static final Logger log = LoggerFactory.getLogger(IPayApplicationServiceImpl.class);
    private static WXPay wxpay;
    private static WXPayConfigImpl config;

    static {
        try {
            config = WXPayConfigImpl.getInstance();
            wxpay = new WXPay(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 微信支付宝充值的蟠桃的充值中心
    public ResponseData add_Peachnumber_Pay(String amount, String coding, String userno, String peachnumber, String givpeachnumber, String recharge_no, String paytype) {
        ResponseData responseData = new ResponseData();
        String id = DataBaseTool.createId(); // 生成id
        String date = DataBaseTool.createDate(); // 生成日期格式
        String ali_wxin = "";
        if (paytype.equals("1")) {
            ali_wxin = "10060001";
        } else {
            ali_wxin = "10060002";
        }
        // 先存入当前的蟠桃的购买的信息
        int insert_info = payinfoMapper.add_Peachnumber_Pay(amount, userno, peachnumber, givpeachnumber, recharge_no, ali_wxin, id, date);
        log.info("插入充值蟠桃的信息条数：" + insert_info + "当前时间：" + date);
        String aliURL = "https://www.sunwukong.net/marketing-service/api/v1/paybusiness/alipayurlp";
        String wexURL = "https://www.sunwukong.net/marketing-service/api/v1/paybusiness/weixinpayurlp";
        if (insert_info > 0) {
            // 返回支付宝的结果参数
            if (ali_wxin.equals("10060001")) {
                Map<String, String> ali = AliPayToolsP.doUnifiedOrder(amount, coding, recharge_no, aliURL);
                log.info("支付宝返回的结果：" + ali.toString());
                if (ali != null) {
                    responseData.setData(ali.get("sdk_data"));
                } else {
                    responseData.setData("支付失败");
                    responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                }
            }
            // 返回微信的支付结果
            else {
                Map<String, String> resultdata = null;
                try {
                    Map<String, String> datamap = new HashMap<>();
                    datamap.put("body", coding);
                    datamap.put("out_trade_no", recharge_no);
                    datamap.put("total_fee", WXPayUtil.yuanToSubdivision(amount));
                    datamap.put("spbill_create_ip", WXPayUtil.getIP());
                    datamap.put("trade_type", "APP");
                    datamap.put("notify_url", wexURL);
                    resultdata = wxpay.unifiedOrder(datamap);
                    if (resultdata != null) {
                        // 进行再次签名
                        Map<String, String> datas = new HashMap<>();
                        datas.put("prepayid", resultdata.get("prepay_id"));
                        // 再次签名
                        Map<String, String> againmap = wxpay.againOrder(datas);
                        if (againmap != null) {
                            responseData.setData(againmap);
                        } else {
                            responseData.setData("微信支付失败");
                            responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                        }
                        log.info("微信返回的结果：" + resultdata.toString());
                    } else {
                        responseData.setData("微信支付失败");
                        responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                    }
                } catch (Exception e) { // 说明返回调度失败
                    e.printStackTrace();
                }
            }
            return responseData;
        } else {
            responseData.setData("数据插入失败");
            responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return responseData;
        }
    }

    //  精选调用支付接口
    public ResponseData add_siftPay(String userNo, String payNo, String payType, String payAmount) {
        ResponseData responseData = new ResponseData();
        // 查询当前订单是否过期
        int esitis = payinfoMapper.find_cz(payNo);
        if (esitis <= 0) {
            responseData.setData("数据插入失败");
            responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return responseData;
        }
        String date = DataBaseTool.createDate(); // 生成日期格式
        String id = DataBaseTool.createId(); // 生成id
        // 进行详细情况记录入数据库
        String payT = "";
        if (payType.equals("1")) {
            payT = "10060002";
        } else {
            payT = "10060001";
        }
        int inert_data_r = payinfoMapper.add_Rinfo(userNo, payAmount, payT, payNo, id);
        if (inert_data_r > 0) {
            // 异步调度地址
            String aliURL = "https://www.sunwukong.net/marketing-service/api/v1/paybusiness/alipayurlpsift";
            String wexURL = "https://www.sunwukong.net/marketing-service/api/v1/paybusiness/weixinpayurlsfit";
            // 支付宝接口
            String choiceType = "精选购买支付";
            if (payType.equals("1")) {
                Map<String, String> ali = AliPayToolsP.doUnifiedOrder(payAmount, choiceType, payNo, aliURL);
                log.info("支付宝返回的结果：" + ali.toString());
                if (ali != null) {
                    responseData.setData(ali.get("sdk_data"));
                } else {
                    responseData.setData("支付失败");
                    responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                }
            } else {
                Map<String, String> resultdata = null;
                try {
                    Map<String, String> datamap = new HashMap<>();
                    datamap.put("body", choiceType);
                    datamap.put("out_trade_no", payNo);
                    datamap.put("total_fee", WXPayUtil.yuanToSubdivision(payAmount));
                    datamap.put("spbill_create_ip", WXPayUtil.getIP());
                    datamap.put("trade_type", "APP");
                    datamap.put("notify_url", wexURL);
                    resultdata = wxpay.unifiedOrder(datamap);
                    if (resultdata != null) {
                        // 进行再次签名
                        Map<String, String> datas = new HashMap<>();
                        datas.put("prepayid", resultdata.get("prepay_id"));
                        // 再次签名
                        Map<String, String> againmap = wxpay.againOrder(datas);
                        if (againmap != null) {
                            responseData.setData(againmap);
                        } else {
                            responseData.setData("微信支付失败");
                            responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                        }
                        log.info("微信返回的结果：" + resultdata.toString());
                    } else {
                        responseData.setData("微信支付失败");
                        responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                    }
                } catch (Exception e) { // 说明返回调度失败
                    e.printStackTrace();
                }
            }
        } else {
            responseData.setData("数据插入失败");
            responseData.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        return responseData;
    }

    // 微信支付的web的业务支付中心
    public Map<String, String> getWexMap(String money, String coding, String orderno, String wexURL, String ip) {
        // 查询当前订单是否过期
        int esitis = payinfoMapper.find_cz(orderno);
        if (esitis <= 0) {
            return null;
        }
        Map<String, String> datamap = new HashMap<>();
        datamap.put("body", coding);
        datamap.put("out_trade_no", orderno);
        datamap.put("total_fee", WXPayUtil.yuanToSubdivision(money));
        datamap.put("spbill_create_ip", ip);
        datamap.put("trade_type", "MWEB");
        datamap.put("notify_url", wexURL);
        datamap.put("scene_info", "{\"h5_info\" : {\"type\": \"Wap\",\"wap_url\": \"www.sunwukong.net/h5/components/Success.html\",\"wap_name\": \"孙悟空华人网\"}}");
        try {
            log.error("支付的传参信息：" + datamap.toString());
            Map<String, String> resultdata = wxpay.unifiedOrder(datamap);
            log.error("预下单的信息：" + resultdata.toString());
            if (resultdata.get("return_code").equals("SUCCESS")) {
                // 返回支付调起客户端的地址url再次签名
                Map<String, String> datas = new HashMap<>();
                datas.put("prepayid", resultdata.get("prepay_id"));
                log.error("支付的支付ID--：" + datas.toString());
                // 进行再次签名
                Map<String, String> againmap = wxpay.againOrder(datas);
                log.error("再次返回签名后的map集合：" + againmap.toString());
                return resultdata;
            } else {
                log.error("H5微信预支付失败");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 插入当前分享后购买的日志信息
     */
    public int add_Insert_Share(String shareNo, String userNo, String buyNo, String shareAmount) {
        String id = DataBaseTool.createId(); // 生成id
        return payinfoMapper.add_Insert_Share(id, shareNo, userNo, buyNo, shareAmount);
    }

    // 网页插入支付信息
    public int add_Insert_Bug(String userNo, String buyNo, int type, String money) {
        String id = DataBaseTool.createId(); // 生成id
        String paytype = null;
        if (type == 1) {
            paytype = "10060002";
        } else {
            paytype = "10060001";
        }
        return payinfoMapper.add_Rinfo(userNo, money, paytype, buyNo, id);
    }

    /**
     * 查询用户的蟠桃数量
     */
    public BigDecimal find_UserCoin(String userNo) {
        BigDecimal bigdata = null;
        try {
            bigdata = payinfoMapper.find_UserCoin(userNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bigdata;
    }

    /**
     * 进行蟠桃的日志记录
     */
    public int update_TxInfo(String id, String userNo, int changeMode, String peachSum, String date, String payNo, String changeRemark) {
        return payinfoMapper.update_UserAccountLog(id, userNo, changeMode, BigDecimal.valueOf(Long.valueOf(peachSum)), date, payNo, changeRemark);
    }

    /**
     * 微信异步调用修改当前订单的状态
     */
    public int update_States(String wxorderno, String totalfee, String transactionid) {
        String conde = "充值";
        String date = DataBaseTool.createDate(); // 生成日期格式
        String id = DataBaseTool.createId(); // 生成id
        int statsmsg = 0;
        // 根据订单号查询当前数据的
        Map<String, Object> data_bak = null;
        try {
            data_bak = payinfoMapper.find_Data_Bak(wxorderno);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("支付宝支付查询蟠桃充值业务的详细信息失败" + e);
        }
        log.error("-----------数据库查询数据-----------" + data_bak.toString());
        if (data_bak != null) {
            String userNo = (String) data_bak.get("userNo");
            // 把两个数充值的币相加
            BigDecimal payNum = (BigDecimal) data_bak.get("payNum"); // 支付的币
            BigDecimal sendNum = (BigDecimal) data_bak.get("sendNum"); // 赠送的币
            // 充值的币数
            BigDecimal sumcoin = payNum.add(sendNum);
            String rechargestate = (String) data_bak.get("rechargeState");
            String datas = WXPayUtil.yuanToSubdivision(String.valueOf(data_bak.get("rechargeAmount")));
            if (!rechargestate.equals("10130001")) {
                log.error("支付回掉的金额匹配：" + totalfee + "数据库金额：" + datas + "订单：" + wxorderno + "的支付状态：" + rechargestate);
                if (totalfee.equals(datas)) {
                    // 变动总的悟空的数量
                    Map<String, Object> sum = payinfoMapper.getSumCoin();
                    BigDecimal sum_coin = (BigDecimal) sum.get("accountAmount");
                    if (sum_coin.compareTo(sumcoin) == 1) {
                        BigDecimal subtractcoin = sum_coin.subtract(sumcoin);
                        // 修改账户的总金额
                        int up_sumcoin = payinfoMapper.update_SumCoin(subtractcoin);
                        log.error("减去平台的支付金额：" + subtractcoin + "减去支付的金额的状态：" + up_sumcoin);
                        int up_coin_info = payinfoMapper.update_SumCoinInfo(wxorderno, userNo, conde, date, sumcoin, id, "10050004");
                        if (up_sumcoin > 0 && up_coin_info > 0) {
                            // 增加用户的账户金额
                            Map<String, Object> usermapvle = payinfoMapper.getUserInfoCoin(userNo);
                            BigDecimal usersumcoin = (BigDecimal) usermapvle.get("accountAmount");
                            BigDecimal upusercoin = usersumcoin.add(sumcoin);
                            // 修改用户的金额
                            int upusersuminfo = payinfoMapper.update_Upusersuminfo(userNo, upusercoin);
                            // 记录用户金额变更记录
                            int upsumcininfodata = payinfoMapper.update_Upsumcininfodata(wxorderno, userNo, conde, date, sumcoin, id);
                            if (upsumcininfodata > 0 && upusersuminfo > 0) {
                                // 修改当前订单的支付状态
                                int up_states = payinfoMapper.update_States(wxorderno, transactionid);
                                if (up_states > 0) {
                                    log.error("蟠桃充值成功进行服务消息通知");
                                    ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
                                    chatServiceMessage.setSendName(SysCode.SYS_SWK.getCode());
                                    chatServiceMessage.setSendText("微信充值" + sumcoin + "个蟠桃成功!");
                                    chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
                                    chatServiceMessage.setUserNo(userNo);
                                    chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10014.getMsg());
                                    iChatServiceMessageService.addChatServiceMessage(chatServiceMessage, 1);
                                    return up_states;
                                } else {
                                    log.info("当前订单号:" + wxorderno + "更改支付状态失败");
                                    return statsmsg;
                                }
                            } else {
                                log.info("当前订单号:" + wxorderno + "添加用户编码:" + userNo + "增加金额" + upusersuminfo + "或者添加用户变动金额日志失败" + upsumcininfodata);
                                return statsmsg;
                            }
                        } else {
                            log.info("当前订单号:" + wxorderno + "减去平台的金额和添加平台金额的变动日志失败");
                            return statsmsg;
                        }
                    } else {
                        log.info("当前订单号:" + wxorderno + "账户的总金额小于要支付的金额支付金额");
                        // 给用户退款
                        return statsmsg;
                    }
                } else {
                    log.info("当前订单号:" + wxorderno + "支付金额和库存信息不符");
                    return statsmsg;
                }
            } else {
                log.info("当前订单号:" + wxorderno + "已经支付了无需再次支付");
                return statsmsg;
            }
        } else {
            log.info("当前订单号:" + wxorderno + "数据库不存在");
            return statsmsg;
        }
    }

    /**
     * 支付宝蟠桃充值的异步回掉的方法
     */
    public String find_aliPayCallBack(Map<String, String> params) {
        String conde = "充值";
        String date = DataBaseTool.createDate(); // 生成日期格式
        String id = DataBaseTool.createId(); // 生成id
        String trade_no = params.get("trade_no");
        String outtradeno = params.get("out_trade_no");
        String tradestatus = params.get("trade_status");
        String totalamount = params.get("total_amount");
        String status = "fail";
        //检查是否交易成功
        if ((tradestatus.equals("TRADE_FINISHED") || tradestatus.equals("TRADE_SUCCESS"))) {
            // 根据订单号查询当前数据的
            Map<String, Object> data_bak = null;
            try {
                data_bak = payinfoMapper.find_Data_Bak(outtradeno);
            } catch (Exception e) {
                e.printStackTrace();
                log.info("支付宝支付查询蟠桃充值业务的详细信息失败" + e);
            }
            log.error("-----------数据库查询数据-----------" + data_bak.toString());
            if (data_bak != null) {
                String rechargestate = (String) data_bak.get("rechargeState");
                String datas = String.valueOf(data_bak.get("rechargeAmount"));
                String userNo = (String) data_bak.get("userNo");
                // 把两个数充值的币相加
                BigDecimal payNum = (BigDecimal) data_bak.get("payNum");
                BigDecimal sendNum = (BigDecimal) data_bak.get("sendNum");
                // 充值的币数
                BigDecimal sumcoin = payNum.add(sendNum);
                if (!rechargestate.equals("10130001")) {
                    log.error("支付回掉的金额匹配：" + totalamount + "数据库金额：" + datas + "订单：" + outtradeno + "的支付状态：" + rechargestate + "充值的币数" + sumcoin);
                    if (totalamount.equals(datas)) {
                        // 变动总的悟空的数量
                        Map<String, Object> sum = payinfoMapper.getSumCoin();
                        System.out.println();
                        BigDecimal sum_coin = (BigDecimal) sum.get("accountAmount");
                        if (sum_coin.compareTo(sumcoin) == 1) {
                            BigDecimal subtractcoin = sum_coin.subtract(sumcoin);
                            // 修改账户的总金额
                            int up_sumcoin = payinfoMapper.update_SumCoin(subtractcoin);
                            log.error("减去平台的支付金额：" + subtractcoin + "减去支付的金额的状态：" + up_sumcoin);
                            int up_coin_info = payinfoMapper.update_SumCoinInfo(outtradeno, userNo, conde, date, sumcoin, id, "10050004");
                            if (up_sumcoin > 0 && up_coin_info > 0) {
                                // 增加用户的账户金额
                                Map<String, Object> usermapvle = payinfoMapper.getUserInfoCoin(userNo);
                                BigDecimal usersumcoin = (BigDecimal) usermapvle.get("accountAmount");
                                BigDecimal upusercoin = usersumcoin.add(sumcoin);
                                // 修改用户的金额
                                int upusersuminfo = payinfoMapper.update_Upusersuminfo(userNo, upusercoin);
                                // 记录用户金额变更记录
                                int upsumcininfodata = payinfoMapper.update_Upsumcininfodata(outtradeno, userNo, conde, date, sumcoin, id);
                                if (upsumcininfodata > 0 && upusersuminfo > 0) {
                                    // 修改当前订单的支付状态
                                    int up_states = payinfoMapper.update_States(outtradeno, trade_no);
                                    if (up_states > 0) {
                                        log.error("---当前的支付全部流程走完支付成功---");
                                        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
                                        chatServiceMessage.setSendName(SysCode.SYS_SWK.getCode());
                                        chatServiceMessage.setSendText("支付宝充值" + sumcoin + "个蟠桃成功!");
                                        chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
                                        chatServiceMessage.setUserNo(userNo);
                                        chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10014.getMsg());
                                        iChatServiceMessageService.addChatServiceMessage(chatServiceMessage, 1);
                                        status = "success";
                                    } else {
                                        log.info("当前订单号:" + outtradeno + "更改支付状态失败");
                                        status = "fail";
                                    }
                                } else {
                                    log.info("当前订单号:" + outtradeno + "添加用户编码:" + userNo + "增加金额" + upusersuminfo + "或者添加用户变动金额日志失败" + upsumcininfodata);
                                    status = "fail";
                                }
                            } else {
                                log.info("当前订单号:" + outtradeno + "减去平台的金额和添加平台金额的变动日志失败");
                                status = "fail";
                            }
                        } else {
                            log.info("当前订单号:" + outtradeno + "账户的总金额小于要支付的金额支付金额");
                            // 给用户退款
                            status = "fail";
                        }
                    } else {
                        log.info("当前订单号:" + outtradeno + "支付金额和库存信息不符");
                        status = "fail";
                    }
                } else {
                    log.info("当前订单号:" + outtradeno + "已经支付了无需再次支付");
                    status = "fail";
                }
            } else {
                log.info("当前订单号:" + outtradeno + "数据库不存在");
                status = "fail";
            }
        } else {
            log.info(">>>>>支付宝端充值失败" + outtradeno);
            status = "fail";
        }
        return status;
    }

    /**
     * APP端进行精选《支付宝》回调实现业务逻辑
     **/
    public String find_aliPaySiftInfo(Map<String, String> params) {
        log.error("APP异步回掉支付宝返回的参数：" + params.toString());
        String trade_no = params.get("trade_no");
        System.out.println("支付宝订单号:" + trade_no);
        String outtradeno = params.get("out_trade_no");
        System.out.println("我们自己的订单:" + outtradeno);
        String tradestatus = params.get("trade_status");
        String totalamount = params.get("total_amount");
        System.out.println("返回的金额:" + totalamount);
        String status = "fail";
        //检查是否交易成功
        if ((tradestatus.equals("TRADE_FINISHED") || tradestatus.equals("TRADE_SUCCESS"))) {
            // 根据订单号查询当前数据的
            Map<String, Object> data_bak = null;
            try {
                // 查询订单的数据
                data_bak = payinfoMapper.find_Data_Sift(outtradeno);
            } catch (Exception e) {
                e.printStackTrace();
                log.info("支付宝支付查询蟠桃充值业务的详细信息失败" + e);
            }
            if (data_bak != null) {
                log.error("-----------数据库查询数据-----------" + data_bak.toString());
                String rechargestate = (String) data_bak.get("payState");
                String datas = String.valueOf(data_bak.get("payAmount"));
                String userNo = (String) data_bak.get("userNo");
                String resourceno = (String) data_bak.get("resourceNo");
                if (rechargestate.equals("10380001")) {
                    log.error("支付回掉的金额匹配：" + totalamount + "数据库金额：" + datas + "订单：" + outtradeno + "的支付状态：" + rechargestate + "用户编码：" + userNo);
                    String date = DataBaseTool.createDate(); // 生成日期格式
                    // 1.0 修改当前订单的支付状态
                    int up_states = payinfoMapper.update_Orderstatus(outtradeno, date, totalamount);
                    // 2.0 修改支付的状态成功
                    if (up_states > 0) {
                        try {
                            int modified_balance_success = 0;
                            // 4.0 查询用户不可提现的余额，看是否足够扣除
                            BigDecimal bigdata = payinfoMapper.find_UserCoin(userNo);
                            // 查询精选用户日志的里面的蟠桃数量
                            BigDecimal usergetcoin = payinfoMapper.get_Usergetcoin_J(userNo, outtradeno);
                            System.out.println("用户的支付日志里面的蟠桃" + usergetcoin);
                            // 4.0.1 先判断用不可提现余额是否可以进行扣除
                            if (bigdata.compareTo(usergetcoin) == 1 || bigdata.compareTo(usergetcoin) == 0) {
                                // 4.0.2 说明可以进行不可提现余额进行扣款
                                modified_balance_success = payinfoMapper.update_UserNoTxSum(userNo, usergetcoin);
                            }
                            // 5.0 查询可提现余额，看是否足够扣除
                            else {
                                BigDecimal noTxCoin = payinfoMapper.find_TxCoin(userNo);
                                if (noTxCoin.compareTo(usergetcoin) == 1 || noTxCoin.compareTo(usergetcoin) == 0) {
                                    // 4.0.2 说明可以进行不可提现余额进行扣款
                                    modified_balance_success = payinfoMapper.update_UserTxSum(userNo, usergetcoin);
                                } else {
                                    modified_balance_success = 1;
                                    log.error("--------------|||||||------------不能进行扣除蟠桃，订单号：" + outtradeno);
                                }
                            }
                            if (modified_balance_success > 0) {
                                try {
                                    Map<String, String> byno = payinfoMapper.getByNo(outtradeno);
                                    log.error("查询的用户资料：" + byno.toString());
//                                    // 转换时间
//                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                                    String start = sdf.format(byno.get("startDate"));
//                                    String end = sdf.format(byno.get("endDate"));
//                                    //组装短信发送的内容
//                                    Map<String, String> smsdata = new HashMap<>();
//                                    smsdata.put("content", byno.get("couponNo"));
//                                    smsdata.put("start", start);
//                                    smsdata.put("end", end);
//                                    smsdata.put("merchant", byno.get("merchantName"));
//                                    smsdata.put("address", byno.get("merchantAddress"));
//                                    smsdata.put("phone", byno.get("merchantPhone"));
//                                    // 进行阿里云短信发送
//                                    String code = AliSMSDao.sendSms(byno.get("phone"), smsdata, SMSTemplateEnum.SMS_142946730).getCode();
//                                    if (!"OK".equals(code)) {
//                                        log.error("------------------短信发送失败------------------");
//                                    }
                                    Map<String, String> mapdata = new HashMap<>();
                                    mapdata.put("phone", "4001003672");
                                    String code = AliSMSDao.sendSms(byno.get("phone"), mapdata, SMSTemplateEnum.SMS_143863410).getCode();
                                    if (!"OK".equals(code)) {
                                        log.error("------------------短信发送失败------------------");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return "success";
                                }
                                int uppaynum = choiceInfoMapper.uppayNum(resourceno);
                                log.error("当前用户支付宝购买精选的商品成功");
                                status = "success";
                            } else {
                                log.error(userNo + "编码的用户" + outtradeno + "订单的金额没有从用户的总金额扣除失败");
                                status = "fail";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error(userNo + "编码的用户" + outtradeno + "查询用户的总金额失败用户的金额");
                            status = "fail";
                        }
                    }
                    // 3.0 修改支付状态失败后 进行记录然后重新给支付报返回没成功再次进行调用
                    else {
                        log.info("-------------------APP精选购买-》支付宝-----------------当前订单号:" + outtradeno + "更改支付状态失败");
                        status = "fail";
                    }
                } else {
                    log.info("当前订单号:" + outtradeno + "已经支付了无需再次支付");
                    status = "fail";
                }
            } else {
                log.info("当前订单号:" + outtradeno + "数据库不存在");
                status = "fail";
            }
        } else {
            log.info(">>>>>支付宝端充值失败" + outtradeno);
            status = "fail";
        }
        return status;
    }

    /**
     * APP端进行精选《微信》回调实现业务逻辑
     **/
    public int update_States_Sfit(String wxorderno, String totalfee, String transactionid) {
        int statsmsg = 0;
        // 根据订单号查询当前数据的
        Map<String, Object> data_bak = null;
        try {
            data_bak = payinfoMapper.find_Data_Sift(wxorderno);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("支付宝支付查询蟠桃充值业务的详细信息失败" + e);
        }
        log.error("-----------数据库查询数据-----------" + data_bak.toString());
        if (data_bak != null) {
            String rechargestate = (String) data_bak.get("payState");
            String userNo = (String) data_bak.get("userNo");
            String datas = String.valueOf(data_bak.get("payAmount"));
            String resourceno = (String) data_bak.get("resourceNo");
            // 把分转元
            String totafee_y = WXPayUtil.yuanToSubdivisions(totalfee);
            if (rechargestate.equals("10380001")) {
                log.error("支付回掉的金额匹配：" + totafee_y + "数据库金额：" + datas + "订单：" + wxorderno + "的支付状态：" + rechargestate + "用户编码：" + userNo);
                String date = DataBaseTool.createDate(); // 生成日期格式
                // 1.0 修改当前订单的支付状态
                int up_states = payinfoMapper.update_Orderstatus(wxorderno, date, totafee_y);
                // 2.0 修改支付的状态成功
                if (up_states > 0) {
                    try {
                        int modified_balance_success = 0;
                        // 4.0 查询用户不可提现的余额，看是否足够扣除
                        BigDecimal bigdata = payinfoMapper.find_UserCoin(userNo);
                        // 查询精选用户日志的里面的蟠桃数量
                        BigDecimal usergetcoin = payinfoMapper.get_Usergetcoin_J(userNo, wxorderno);
                        System.out.println("用户的支付日志里面的蟠桃" + usergetcoin);
                        // 4.0.1 先判断用不可提现余额是否可以进行扣除
                        if (bigdata.compareTo(usergetcoin) == 1 || bigdata.compareTo(usergetcoin) == 0) {
                            // 4.0.2 说明可以进行不可提现余额进行扣款
                            modified_balance_success = payinfoMapper.update_UserNoTxSum(userNo, usergetcoin);
                        }
                        // 5.0 查询可提现余额，看是否足够扣除
                        else {
                            BigDecimal noTxCoin = payinfoMapper.find_TxCoin(userNo);
                            if (noTxCoin.compareTo(usergetcoin) == 1 || noTxCoin.compareTo(usergetcoin) == 0) {
                                // 4.0.2 说明可以进行不可提现余额进行扣款
                                modified_balance_success = payinfoMapper.update_UserTxSum(userNo, usergetcoin);
                            } else {
                                modified_balance_success = 1;
                                log.error("--------------|||||||------------不能进行扣除蟠桃，订单号：" + wxorderno);
                            }
                        }
                        if (modified_balance_success > 0) {
                            try {
                                Map<String, String> byno = payinfoMapper.getByNo(wxorderno);
                                log.error("查询的用户资料：" + byno.toString());
                                // 转换时间
//                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                                String start = sdf.format(byno.get("startDate"));
//                                String end = sdf.format(byno.get("endDate"));
//                                //组装短信发送的内容
//                                Map<String, String> smsdata = new HashMap<>();
//                                smsdata.put("content", byno.get("couponNo"));
//                                smsdata.put("start", start);
//                                smsdata.put("end", end);
//                                smsdata.put("merchant", byno.get("merchantName"));
//                                smsdata.put("address", byno.get("merchantAddress"));
//                                smsdata.put("phone", byno.get("merchantPhone"));
//                                // 进行阿里云短信发送
//                                String code = AliSMSDao.sendSms(byno.get("phone"), smsdata, SMSTemplateEnum.SMS_142946730).getCode();
//                                if (!"OK".equals(code)) {
//                                    log.error("------------------短信发送失败------------------");
//                                }
                                Map<String, String> mapdata = new HashMap<>();
                                mapdata.put("phone", "18602822000");
                                String code = AliSMSDao.sendSms(byno.get("phone"), mapdata, SMSTemplateEnum.SMS_143863410).getCode();
                                if (!"OK".equals(code)) {
                                    log.error("------------------短信发送失败------------------");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                return modified_balance_success;
                            }
                            int uppaynum = choiceInfoMapper.uppayNum(resourceno);
                            log.error("当前用户支付宝购买精选的商品成功");
                            return modified_balance_success;
                        } else {
                            log.error(userNo + "编码的用户" + wxorderno + "订单的金额没有从用户的总金额扣除失败");
                            return statsmsg;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(userNo + "编码的用户" + wxorderno + "查询用户的总金额失败用户的金额");
                        return statsmsg;
                    }
                }
                // 3.0 修改支付状态失败后 进行记录然后重新给支付报返回没成功再次进行调用
                else {
                    log.info("-------------------APP精选购买-》支付宝-----------------当前订单号:" + wxorderno + "更改支付状态失败");
                    return statsmsg;
                }
            } else {
                log.info("当前订单号:" + wxorderno + "已经支付了无需再次支付");
                return statsmsg;
            }
        } else {
            log.info("当前订单号:" + wxorderno + "数据库不存在");
            return statsmsg;
        }
    }

    /**
     * 网页端进行精选《支付宝》回调实现业务逻辑
     **/
    public String findWebPayGetUser(Map<String, String> params) {
        String trade_no = params.get("trade_no");
        String outtradeno = params.get("out_trade_no");
        String tradestatus = params.get("trade_status");
        String totalamount = params.get("total_amount");
        String status = "fail";
        log.error("当单号:" + trade_no + "我们的订单号：" + outtradeno + "金额：" + totalamount + "状态：" + tradestatus);
        //检查是否交易成功
        if ((tradestatus.equals("TRADE_FINISHED") || tradestatus.equals("TRADE_SUCCESS"))) {
            String date = DataBaseTool.createDate(); // 生成日期格式
            String id = DataBaseTool.createId(); // 生成id
            // 根据购买单号查询分享者的用户编码
            Map<String, Object> userinfo = payinfoMapper.getUserNo(outtradeno);
            // 获取字段值
            String userNo = (String) userinfo.get("userNo"); // 分享者的编码
            BigDecimal shareRebateAmount = (BigDecimal) userinfo.get("shareRebateAmount"); // 返利金额
            String resourceno = (String) userinfo.get("resourceNo");
            // 给分享者的账号增加返利金额
            // 先查询当前单号是否增加了用户可提现金额在用户可提现的日志表中
            int selectsum = payinfoMapper.find_TxSum(outtradeno);
            int upUserM = 0;
            if (selectsum == 0) { // 没有进行数据插入进行数据记录
                // 获取分享者体现金额
                BigDecimal userTx = payinfoMapper.find_TxCoin(userNo);
                log.error("分享者体现金额：" + userTx);
                // 插入用户的资金变动日志
                int changeMode = 10330003;
                String changeRemark = "分享优惠券收益";
                upUserM = payinfoMapper.update_TxInfo(id, userNo, changeMode, shareRebateAmount, date, outtradeno, changeRemark);
                if (upUserM > 0) {
                    // 返利和总金额相加获得最后的金额数
                    BigDecimal shareRA = userTx.add(shareRebateAmount);
                    log.error("当前用户的体现金额:" + userTx + "分享获利的金额:" + shareRebateAmount + "两者相加的金额:" + shareRA);
                    // 改变用户的金额
                    int updataM = payinfoMapper.update_Txsuminfo(userNo, shareRA);
                    if (updataM > 0) {
                        System.out.println("修改用户体现总金额成功");
                        /*减去平台分享成功的分享金额*/
                        // 插入平台日志记录
                        int platformint = payinfoMapper.update_SumCoinInfo(outtradeno, userNo, "返利", DataBaseTool.createDate(), shareRebateAmount, DataBaseTool.createId(), "10050003");
                        // 减去分享的金额
                        int payl = payinfoMapper.update_PtSum(shareRebateAmount);
                        log.error("插入平台日志的记录:" + platformint + "修改平台的总金额:" + payl);
                        if (platformint > 0) {
                            if (payl > 0) {
                                // 修改当前支付的用户的支付状态
                                int upstates = payinfoMapper.update_Orderstatus(outtradeno, date, totalamount);
                                if (upstates > 0) {
                                    // 对优惠券对用户短信通知
                                    try {
                                        Map<String, String> byno = payinfoMapper.getByNo(outtradeno);
                                        log.error("查询的用户资料：" + byno.toString());
                                        // 转换时间
//                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                                        String start = sdf.format(byno.get("startDate"));
//                                        String end = sdf.format(byno.get("endDate"));
//                                        //组装短信发送的内容
//                                        Map<String, String> smsdata = new HashMap<>();
//                                        smsdata.put("content", byno.get("couponNo"));
//                                        smsdata.put("start", start);
//                                        smsdata.put("end", end);
//                                        smsdata.put("merchant", byno.get("merchantName"));
//                                        smsdata.put("address", byno.get("merchantAddress"));
//                                        smsdata.put("phone", byno.get("merchantPhone"));
//                                        // 进行阿里云短信发送
//                                        String code = AliSMSDao.sendSms(byno.get("phone"), smsdata, SMSTemplateEnum.SMS_142946730).getCode();
//                                        if (!"OK".equals(code)) {
//                                            log.error("------------------短信发送失败------------------");
//                                        }
                                        Map<String, String> mapdata = new HashMap<>();
                                        mapdata.put("phone", "18602822000");
                                        String code = AliSMSDao.sendSms(byno.get("phone"), mapdata, SMSTemplateEnum.SMS_143863410).getCode();
                                        if (!"OK".equals(code)) {
                                            log.error("------------------短信发送失败------------------");
                                        }
                                        int uppaynum = choiceInfoMapper.uppayNum(resourceno);
                                        log.error("当前订单：" + outtradeno + "支付成功");
                                        return "success";
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        log.error("查询用户资料失败");
                                        return status;
                                    }
                                } else {
                                    log.error("单号：" + outtradeno + "改变支付状态失败");
                                    return status;
                                }
                            } else {
                                // 减去平台金钱失败1.0 删除用户平台日志2.0修改用户体现金额到原来的值
                                // 1.0 删除平台日志
                                int dePt = payinfoMapper.deletePtInfo(outtradeno);
                                int updataY = payinfoMapper.update_Txsuminfo(userNo, userTx);
                                log.error("删除平台的日志记录:" + dePt + "修改原来金额:" + updataY);
                                return status;
                            }
                        } else {
                            // 插入平台日志失败 修改用户体现金额到原来的值
                            int updataY = payinfoMapper.update_Txsuminfo(userNo, userTx);
                            log.error("插入平台日志失败还原体现金额:" + updataY);
                            return status;
                        }
                    } else {
                        // 修改分享者的返利金额失败
                        log.error("分享者：" + userNo + "单号：" + outtradeno + "修改用户的返利金额失败");
                        return status;
                    }
                } else {
                    log.error("插入分享者获利的体现日志表失败");
                    return status;
                }
            } else {
                int deTx = payinfoMapper.deleteTxInfo(outtradeno);
                log.error("分享金额已经进行插入成功,说明失败,删除记录" + deTx);
                return status;
            }
        } else {
            return status;
        }
    }

    /**
     * 网页端进行精选《微信》回调实现业务逻辑
     **/
    public int wxPayInfoU(String wxorderno, String totalfee, String transactionid) {
        int status = 0;
        log.error("当单号:" + transactionid + "我们的订单号：" + wxorderno + "金额：" + totalfee);
        String date = DataBaseTool.createDate(); // 生成日期格式
        String id = DataBaseTool.createId(); // 生成id
        // 根据购买单号查询分享者的用户编码
        Map<String, Object> userinfo = payinfoMapper.getUserNo(wxorderno);
        // 获取字段值
        String userNo = (String) userinfo.get("userNo"); // 分享者的编码
        BigDecimal shareRebateAmount = (BigDecimal) userinfo.get("shareRebateAmount"); // 返利金额
        String resourceno = (String) userinfo.get("resourceNo");
        // 给分享者的账号增加返利金额
        // 先查询当前单号是否增加了用户可提现金额在用户可提现的日志表中
        int selectsum = payinfoMapper.find_TxSum(wxorderno);
        int upUserM = 0;
        if (selectsum == 0) { // 没有进行数据插入进行数据记录
            // 获取分享者体现金额
            BigDecimal userTx = payinfoMapper.find_TxCoin(userNo);
            log.error("分享者体现金额：" + userTx);
            // 插入用户的资金变动日志
            int changeMode = 10330003;
            String changeRemark = "分享优惠券收益";
            upUserM = payinfoMapper.update_TxInfo(id, userNo, changeMode, shareRebateAmount, date, wxorderno, changeRemark);
            if (upUserM > 0) {
                // 返利和总金额相加获得最后的金额数
                BigDecimal shareRA = userTx.add(shareRebateAmount);
                log.error("当前用户的体现金额:" + userTx + "分享获利的金额:" + shareRebateAmount + "两者相加的金额:" + shareRA);
                // 改变用户的金额
                int updataM = payinfoMapper.update_Txsuminfo(userNo, shareRA);
                if (updataM > 0) {
                    System.out.println("修改用户体现总金额成功");
                    /*减去平台分享成功的分享金额*/
                    // 插入平台日志记录
                    int platformint = payinfoMapper.update_SumCoinInfo(wxorderno, userNo, "返利", DataBaseTool.createDate(), shareRebateAmount, DataBaseTool.createId(), "10050003");
                    // 减去分享的金额
                    int payl = payinfoMapper.update_PtSum(shareRebateAmount);
                    log.error("插入平台日志的记录:" + platformint + "修改平台的总金额:" + payl);
                    if (platformint > 0) {
                        if (payl > 0) {
                            // 修改当前支付的用户的支付状态
                            int upstates = payinfoMapper.update_Orderstatus(wxorderno, date, totalfee);
                            if (upstates > 0) {
                                // 对优惠券对用户短信通知
                                try {
                                    Map<String, String> byno = payinfoMapper.getByNo(wxorderno);
                                    log.error("查询的用户资料：" + byno.toString());
                                    // 转换时间
//                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                                    String start = sdf.format(byno.get("startDate"));
//                                    String end = sdf.format(byno.get("endDate"));
//                                    //组装短信发送的内容
//                                    Map<String, String> smsdata = new HashMap<>();
//                                    smsdata.put("content", byno.get("couponNo"));
//                                    smsdata.put("start", start);
//                                    smsdata.put("end", end);
//                                    smsdata.put("merchant", byno.get("merchantName"));
//                                    smsdata.put("address", byno.get("merchantAddress"));
//                                    smsdata.put("phone", byno.get("merchantPhone"));
//                                    // 进行阿里云短信发送
//                                    String code = AliSMSDao.sendSms(byno.get("phone"), smsdata, SMSTemplateEnum.SMS_142946730).getCode();
//                                    if (!"OK".equals(code)) {
//                                        log.error("------------------短信发送失败------------------");
//                                    }
                                    Map<String, String> mapdata = new HashMap<>();
                                    mapdata.put("phone", "18602822000");
                                    String code = AliSMSDao.sendSms(byno.get("phone"), mapdata, SMSTemplateEnum.SMS_143863410).getCode();
                                    if (!"OK".equals(code)) {
                                        log.error("------------------短信发送失败------------------");
                                    }
                                    int uppaynum = choiceInfoMapper.uppayNum(resourceno);
                                    log.error("当前订单：" + wxorderno + "支付成功");
                                    return upstates;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    log.error("查询用户资料失败");
                                    return status;
                                }
                            } else {
                                log.error("单号：" + wxorderno + "改变支付状态失败");
                                return status;
                            }
                        } else {
                            // 减去平台金钱失败1.0 删除用户平台日志2.0修改用户体现金额到原来的值
                            // 1.0 删除平台日志
                            int dePt = payinfoMapper.deletePtInfo(wxorderno);
                            int updataY = payinfoMapper.update_Txsuminfo(userNo, userTx);
                            log.error("删除平台的日志记录:" + dePt + "修改原来金额:" + updataY);
                            return status;
                        }
                    } else {
                        // 插入平台日志失败 修改用户体现金额到原来的值
                        int updataY = payinfoMapper.update_Txsuminfo(userNo, userTx);
                        log.error("插入平台日志失败还原体现金额:" + updataY);
                        return status;
                    }
                } else {
                    // 修改分享者的返利金额失败
                    log.error("分享者：" + userNo + "单号：" + wxorderno + "修改用户的返利金额失败");
                    return status;
                }
            } else {
                log.error("插入分享者获利的体现日志表失败");
                return status;
            }
        } else {
            int deTx = payinfoMapper.deleteTxInfo(wxorderno);
            log.error("分享金额已经进行插入成功,说明失败,删除记录" + deTx);
            return status;
        }
    }

    //修改订单蟠桃支付情况
    public void updatePeach(String payNo) {
        payinfoMapper.updatePeach(payNo);
    }

    /**
     * 进行提现余额查询
     **/
    public BigDecimal find_TxCoin(String userNo) {
        return payinfoMapper.find_TxCoin(userNo);
    }
}
