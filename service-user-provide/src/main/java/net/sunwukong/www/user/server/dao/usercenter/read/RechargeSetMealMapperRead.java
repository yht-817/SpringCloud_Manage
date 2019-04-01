package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.user.bean.RechargeSetMeal;

import java.util.List;

public interface RechargeSetMealMapperRead {

    /**
     * 获取充值套餐列表
     * @return
     */
    List<RechargeSetMeal> getAllList();
}