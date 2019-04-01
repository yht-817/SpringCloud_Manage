package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.RechargeSetMeal;
import net.sunwukong.www.user.server.dao.usercenter.read.RechargeSetMealMapperRead;
import net.sunwukong.www.user.server.service.IRechargeSetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明:充值套餐服务接口实现
 *
 * @author Mick
 * CreateDate 2018/6/19/019 17:36
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IRechargeSetMealServiceImpl implements IRechargeSetMealService {

    @Autowired
    RechargeSetMealMapperRead rechargeSetMealMapperRead;

    /**
     * 获取充值套餐列表
     * @return
     */
    @Override
    public ResponseData getList() {
        ResponseData responseData = new ResponseData();
        List<RechargeSetMeal> allList = rechargeSetMealMapperRead.getAllList();
        responseData.setData(allList);
        return responseData;
    }
}
