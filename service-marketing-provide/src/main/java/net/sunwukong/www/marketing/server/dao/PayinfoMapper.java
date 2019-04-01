package net.sunwukong.www.marketing.server.dao;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Map;

public interface PayinfoMapper {

    // 支付宝微信支付充值业务代码
    int add_Peachnumber_Pay(@Param(value = "amount") String amount, @Param(value = "userno") String userno,
                            @Param(value = "peachnumber") String peachnumber, @Param(value = "givpeachnumber") String givpeachnumber,
                            @Param(value = "recharge_no") String recharge_no, @Param(value = "ali_wxin") String ali_wxin,
                            @Param(value = "id") String id, @Param(value = "date") String date);


    // 查询当前的订单
    Map<String, Object> find_Data_Bak(@Param(value = "outtradeno") String outtradeno);

    // 修改当前订单的支付状态
    int update_States(@Param(value = "outtradeno") String outtradeno, @Param(value = "trade_no") String trade_no);

    // 支付日志表
    int add_Rinfo(@Param(value = "userNo") String userNo, @Param(value = "payAmount") String payAmount,
                  @Param(value = "payT") String payT, @Param(value = "payNo") String payNo, @Param(value = "id") String id);

    // 查询精选支付信息
    Map<String, Object> find_Data_Sift(@Param(value = "outtradeno") String outtradeno);

    // 修改精选支付状态
    int update_Orderstatus(@Param(value = "outtradeno") String outtradeno, @Param(value = "date") String date, @Param(value = "totalamount") String totalamount);

    // 获取总悟空币
    Map<String, Object> getSumCoin();

    // 修改当前的总金额
    int update_SumCoin(@Param(value = "subtractcoin") BigDecimal subtractcoin);

    // 修改平台的资金总和
    int update_PtSum(@Param(value = "shareRebateAmount") BigDecimal shareRebateAmount);

    // 添加平台资金账户的日志
    int update_SumCoinInfo(@Param(value = "outtradeno") String outtradeno, @Param(value = "userNo") String userNo,
                           @Param(value = "conde") String conde, @Param(value = "date") String date, @Param(value = "sumcoin") BigDecimal sumcoin, @Param(value = "id") String id, @Param(value = "changemode") String changemode);

    // 查询当前用户的金额
    Map<String, Object> getUserInfoCoin(@Param(value = "userNo") String userNo);

    // 修改当前用户的总金额
    int update_Upusersuminfo(@Param(value = "userNo") String userNo, @Param(value = "upusercoin") BigDecimal upusercoin);

    int update_Upsumcininfodata(@Param(value = "outtradeno") String outtradeno, @Param(value = "userNo") String userNo,
                                @Param(value = "conde") String conde, @Param(value = "date") String date, @Param(value = "sumcoin") BigDecimal sumcoin, @Param(value = "id") String id);

    // 查询用户用户当前的蟠桃数量
    BigDecimal find_UserCoin(@Param(value = "userNo") String userNo);

    // 查询现在可体现蟠桃数
    BigDecimal find_TxCoin(@Param(value = "userNo") String userNo);

    // 精选用户变动资金变动日志
    int add_Insert_Log(@Param(value = "payNo") String payNo, @Param(value = "userNo") String userNo,
                       @Param(value = "payAmount") String payAmount, @Param(value = "date") String date, @Param(value = "id") String id);

    BigDecimal get_Usergetcoin_J(@Param(value = "userNo") String userNo, @Param(value = "outtradeno") String outtradeno);

    // 分享购买的日记记录标表
    int add_Insert_Share(@Param(value = "id") String id, @Param(value = "shareNo") String shareNo,
                         @Param(value = "userNo") String userNo, @Param(value = "buyNo") String buyNo, @Param(value = "shareAmount") String shareAmount);

    // 根据购买的单号获取分享者的信息
    Map<String, Object> getUserNo(@Param(value = "outtradeno") String outtradeno);

    int update_UserAccountLog(@Param(value = "id") String id, @Param(value = "userNo") String userNo, @Param(value = "changeMode") int changeMode,
                              @Param(value = "shareRebateAmount") BigDecimal shareRebateAmount, @Param(value = "date") String date,
                              @Param(value = "outtradeno") String outtradeno, @Param(value = "changeRemark") String changeRemark);

    // 获得购买的优惠券码
    Map<String, String> getByNo(@Param(value = "outtradeno") String outtradeno);

    // 修改订单的蟠桃使用情况
    void updatePeach(@Param(value = "payNo") String payNo);

    // 在体现日志表中进行查询当前订单是否存在
    int find_TxSum(@Param(value = "outtradeno") String outtradeno);

    // 体现日志
    int update_TxInfo(@Param(value = "id") String id, @Param(value = "userNo") String userNo, @Param(value = "changeMode") int changeMode,
                      @Param(value = "shareRebateAmount") BigDecimal shareRebateAmount, @Param(value = "date") String date,
                      @Param(value = "outtradeno") String outtradeno, @Param(value = "changeRemark") String changeRemark);

    // 修改当前用户的体现总金额
    int update_Txsuminfo(@Param(value = "userNo") String userNo, @Param(value = "upusercoin") BigDecimal upusercoin);

    // 删除体现日志
    int deleteTxInfo(@Param(value = "outtradeno") String outtradeno);

    // 删除平台日志
    int deletePtInfo(@Param(value = "outtradeno") String outtradeno);

    // 修改用户可提现的余额
    int update_UserTxSum(@Param(value = "userNo") String userNo,@Param(value = "upusercoin") BigDecimal usergetcoin);

    // 修改用户不可提现的余额
    int update_UserNoTxSum(@Param(value = "userNo") String userNo,@Param(value = "upusercoin") BigDecimal usergetcoin);

    // 查询当前订单是否有效
    int find_cz(@Param(value = "payNo") String payNo);
}
