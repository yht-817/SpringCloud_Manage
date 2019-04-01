package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.util.List;
import java.util.Map;

/**
 * Created by tungkang on 2018/7/11.
 */
public interface IChoiceInfoService {
    /**
     * 获取顶部精选列表
     *
     * @param data
     * @return
     */
    ResponseData getTopList(Map<String, String> data);


    /**
     * 获取中间banner列表
     *
     * @param data
     * @return
     */
    ResponseData getMiddleList(Map<String, String> data);


    /**
     * 获取底部分页精选信息
     *
     * @param data
     * @return
     */
    ResponseData getBottomList(Map<String, String> data);


    /**
     * 查看精选详情
     *
     * @param resourceNo
     * @param userNo
     * @return
     */
    ResponseData getDetail(String resourceNo, String userNo);


    /**
     * 点击收藏精选/取消精选收藏
     *
     * @param data
     * @return
     */
    ResponseData addOrCancel(Map<String, String> data);


    /**
     * 购买，用户下单，还未支付
     * (生成订单单号和优惠券单号返回)
     *
     * @param data
     * @return
     */
    ResponseData addBuy(Map<String, String> data);


    /**
     * 定时查询用户下单未支付的订单
     * 1、30分钟内未支付的订单choice_user_pay
     * 2、取消订单 choice_user_pay（pay_state）
     * 3、平台反还蟠桃到该账户
     * 4、精选优惠券数量增加
     *
     * @return
     */
    ResponseData cancelBuy();

    // 分享精选的券
    ResponseData addShare(Map<String, String> data);

    // 取消订单
    ResponseData cancelOrder(Map<String, String> data);

    // 取消超时的订单
    List<Map<String, String>> getDataList(String timet);

    int deletePay(String getNo);

    int updatePay(String resourceNo, String couponNo);

    String selectPs(String getNo);
}
