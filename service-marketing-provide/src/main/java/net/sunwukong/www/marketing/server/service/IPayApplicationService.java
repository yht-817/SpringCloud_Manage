package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 支付接口service
 */
public interface IPayApplicationService {

    // 微信支付宝充值的蟠桃的记录的控制代码
    ResponseData add_Peachnumber_Pay(String amount, String coding, String userno, String peachnumber, String givpeachnumber, String recharge_no, String paytype);

    // 支付宝异步调用
    String find_aliPayCallBack(Map<String, String> params);

    int update_States(String wxorderno, String totalfee, String transactionid);

    // 支付返回进行App调用
    ResponseData add_siftPay(String userNo, String payNo, String payType, String payAmount);

    // 精选支付宝返回回调
    String find_aliPaySiftInfo(Map<String, String> params);

    // 精选微信返回回调
    int update_States_Sfit(String wxorderno, String totalfee, String transactionid);

    // 查询用户的蟠桃数量
    BigDecimal find_UserCoin(String userNo);

    // 公众号支付微信
    Map<String, String> getWexMap(String money, String coding, String orderno, String wexURL, String ip);

    // 插入分享的购买信息表
    int add_Insert_Share(String shareNo, String userNo, String buyNo, String shareAmount);

    // 网页支付支付记录
    int add_Insert_Bug(String userNo, String buyNo, int type, String money);

    // 支付宝网页支付返利的接口
    String findWebPayGetUser(Map<String, String> params);

    // 微信回调的支付返利接口
    int wxPayInfoU(String wxorderno, String totalfee, String transactionid);

    // 修改订单蟠桃的使用情况
    void updatePeach(String payNo);

    // 进行提现余额的查询
    BigDecimal find_TxCoin(String userNo);

    // 用户变动金额日志
    int update_TxInfo(String id, String userNo, int changeMode, String peachSum, String date, String payNo, String changeRemark);
}
