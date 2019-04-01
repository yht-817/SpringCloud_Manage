package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.ChatServiceMessage;
import org.apache.ibatis.annotations.Param;

public interface ChatServiceMessageMapper extends BaseMapper<ChatServiceMessage> {

    /**
     * 查询是否已经推送过该用户该条未支付的订单
     * @param userNo
     * @param serviceNo
     * @return
     */
    Boolean getByUserNoAndPayNo(@Param(value = "userNo") String userNo,
                                @Param(value = "serviceNo") String serviceNo);
}