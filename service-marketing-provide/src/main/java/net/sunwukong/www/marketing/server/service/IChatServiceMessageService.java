package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.ChatServiceMessage;

/**
 * Created by tungkang on 2018/7/20.
 */
public interface IChatServiceMessageService {

    /**
     * 定时查询用户下单未支付的订单，生成订单服务通知消息
     * 1、5分钟内未支付的订单choice_user_pay
     * 2、生成通知消息chat_service_message
     * @return
     */
    ResponseData addOrderServiceMessage();

    /**
     * 添加服务消息
     * @param chatServiceMessage    消息体
     * @param type                  消息类型(1:普通推送消息 2:抢单推送消息)
     * @return
     */
    ResponseData addChatServiceMessage(ChatServiceMessage chatServiceMessage, int type);

    /**
     * 添加服务消息(默认推送普通消息)
     * @param chatServiceMessage    消息体
     * @return
     */
    ResponseData addChatServiceMessage(ChatServiceMessage chatServiceMessage);
}
