package net.sunwukong.www.user.server.dao.marketing.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.MarketingSendSet;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface MarketingSendSetMapperRead extends BaseMapper<MarketingSendSet> {

    /**
     * 根据当前时间和赠送类型查询当前可以赠送的套餐
     * @param nowDate   当前时间
     * @param sendType  赠送类型
     * @return
     */
    MarketingSendSet findBySendTypeBetweenDate(@Param("nowDate") Date nowDate, @Param("sendType") String sendType);
}