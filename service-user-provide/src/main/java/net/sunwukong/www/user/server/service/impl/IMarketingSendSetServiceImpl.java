package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.marketing.bean.MarketingSendSet;
import net.sunwukong.www.user.server.dao.marketing.read.MarketingSendSetMapperRead;
import net.sunwukong.www.user.server.service.IMarketingSendSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 说明:营销赠送设置服务接口实现
 *
 * @author Mick
 * CreateDate 2018/7/4/004 1:09
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IMarketingSendSetServiceImpl implements IMarketingSendSetService {

    @Autowired
    MarketingSendSetMapperRead marketingSendSetMapperRead;

    /**
     * 根据当前时间和赠送类型查询当前可以赠送的套餐
     * @param nowDate   当前时间
     * @param sendType  赠送类型
     * @return
     */
    @Override
    public MarketingSendSet findBySendTypeBetweenDate(Date nowDate, String sendType) {
        MarketingSendSet bySendTypeBetweenDate = marketingSendSetMapperRead.findBySendTypeBetweenDate(nowDate,sendType);
        return bySendTypeBetweenDate;
    }
}
