package net.sunwukong.www.chat.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.bean.ChatServiceMessage;

/**
 * 说明:服务通知模板
 *
 * @author Mick
 * @CreateDate 2018/7/12 10:39
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IChatServiceMessageService {

    /**
     * 分页获取服务消息
     * @param userNo    用户编码
     * @param start     开始位置
     * @param size      检索长度
     * @return
     */
    ResponseData queryPageChatServiceMessage(String userNo,int start,int size);

    /**
     * 添加服务消息
     * @param chatServiceMessage    添加对象
     * @return
     */
    ResponseData addChatServiceMessage(ChatServiceMessage chatServiceMessage);

    /**
     * 根据业务单号查询业务状态
     * @param inviteNo  业务单号
     * @return
     */
    ChatServiceMessage findByServiceNo(String inviteNo);

    /**
     * 修改服务通知消息信息
     * @param byServiceNo
     */
    void updateChatServiceMessage(ChatServiceMessage byServiceNo);

    /**
     * 根据消息ID修改阅读状态
     * @param id        消息ID
     * @param readState 阅读状态
     * @return
     */
    ResponseData updateReadState(String id, String readState);

    /**
     * 根据消息ID查询业务状态
     * @param id  消息ID
     * @return
     */
    ChatServiceMessage findById(String id);
}
