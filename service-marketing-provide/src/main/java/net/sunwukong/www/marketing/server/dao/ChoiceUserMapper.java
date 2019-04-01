package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.ChoiceUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChoiceUserMapper extends BaseMapper<ChoiceUser> {
    // 通过券编码查询券信息
    ChoiceUser getCouponNo(String couponNo);

    // 获取未使用的优惠券
    List<Map<String, Object>> getChoiceUserL(@Param(value = "userNo") String userNo, @Param(value = "date") String date);

    // 获取使用的券
    List<Map<String, Object>> getChoiceUserSy(@Param(value = "userNo") String userNo);

    // 查询用户过期的券
    List<Map<String, Object>> getChoiceUserGq(@Param(value = "userNo") String userNo, @Param(value = "date") String date);
}