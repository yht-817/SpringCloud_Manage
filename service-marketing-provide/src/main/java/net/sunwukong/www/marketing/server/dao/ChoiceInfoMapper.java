package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.ChoiceInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ChoiceInfoMapper extends BaseMapper<ChoiceInfo> {

    /**
     * 通过布局位置获取分页精选信息
     *
     * @param map
     * @return
     */
    List<ChoiceInfo> getListByPositionNo(Map<String, Object> map);


    /**
     * 通过资源编码查询精选详情
     *
     * @param resourceNo
     * @return
     */
    ChoiceInfo getDetailByResourceNo(String resourceNo);

    /**
     * 通过条件获取总条数
     *
     * @param map
     * @return
     */
    int getTotal(Map<String, Object> map);

    // 获取当前资源编码的明细
    Map<String, Object> getChoiceInfo(@Param(value = "resourceNo") String resourceNo);

    // 插入用户精选信息表的数据
    int insertChoiceuser(@Param(value = "id") String id, @Param(value = "userNo") String userNo, @Param(value = "choicetype") String choicetype,
                         @Param(value = "resourceNo") String resourceNo, @Param(value = "payNo") String payNo, @Param(value = "couponNo") String couponNo,
                         @Param(value = "date") String date, @Param(value = "usestate") String usestate, @Param(value = "payNum") int payNum);

    // 插入用户精选支付信息表的数据
    int insertChoicepay(@Param(value = "id") String id, @Param(value = "userNo") String userNo, @Param(value = "payNo") String payNo, @Param(value = "choicetype") String choicetype,
                        @Param(value = "resourceNo") String resourceNo, @Param(value = "wukbsum") BigDecimal wukbsum,
                        @Param(value = "payamount") BigDecimal payamount, @Param(value = "paystate") String paystate, @Param(value = "date") String date);

    // 插入分享信息
    int insertShareInfo(@Param(value = "id") String id, @Param(value = "shareNo") String shareNo, @Param(value = "userNo") String userNo,
                        @Param(value = "choiceType") String choiceType, @Param(value = "resourceNo") String resourceNo, @Param(value = "date") String date);

    // 查询当前资源编码是否存在商家给的码
    String selectifthere(@Param(value = "resourceNo") String resourceNo);

    // 修改当前的额券的状态
    int updatecouponNo(@Param(value = "resourceNo") String resourceNo, @Param(value = "couponNo") String couponNo);

    // 修改购买的张数
    int uppayNum(@Param(value = "resourceNo") String resourceNo);

    // 用户取消后改变当前的前券的状态
    int upCoupon(@Param(value = "resourceNo") String resourceNo, @Param(value = "couponNo") String couponNo);

    // 取消订单增加购买次数
    int updateBysum(@Param(value = "resourceNo") String resourceNo);

    // 查看是否存在手机号码
    String getPhone(@Param(value = "userNo") String userNo);

    // 取消超时的订单
    List<Map<String, String>> getDataList(@Param(value = "timet") String timet);

    // 删除订单
    int deletePay(@Param(value = "getNo") String getNo);

    // 修改当前资源状态
    int updatePay(@Param(value = "resourceNo") String resourceNo,@Param(value = "couponNo") String couponNo);

    // 查询当前订单的支付状态
    String selectPs(@Param(value = "getNo") String getNo);
}
