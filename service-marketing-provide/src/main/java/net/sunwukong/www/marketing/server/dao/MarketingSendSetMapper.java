package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.MarketingSendSet;

import java.util.Map;

public interface MarketingSendSetMapper extends BaseMapper<MarketingSendSet> {

    /**
     * 根据时间和类型获取所在时间段的赠送设置
     * @param map
     * @return
     */
    MarketingSendSet getMarketingSendSetByDateAndType(Map<String,Object> map);
}