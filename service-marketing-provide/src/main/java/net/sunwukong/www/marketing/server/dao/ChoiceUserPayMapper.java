package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.ChoiceUserPay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChoiceUserPayMapper extends BaseMapper<ChoiceUserPay> {

    /**
     * 查询多久时间内未支付的订单
     * @param timeLength
     * @return
     */
    List<ChoiceUserPay> getNotPayByTime(@Param(value = "timeLength") int timeLength);


    /**
     * 通过订单编号查询未支付下单记录
     * @param payNo
     * @return
     */
    ChoiceUserPay getNotPayByPayNo(String payNo);


    /**
     * 获取已购该票券的张数（已支付张数 + 未支付张数）
     * @param resourceNo
     * @param userNo
     * @return
     */
    int getByResourceNoAndUserNo(@Param(value = "resourceNo") String resourceNo, @Param(value = "userNo") String userNo);
}