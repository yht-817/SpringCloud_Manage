package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import com.sdkinfo.www.im.easemob.dao.IMSendMessage;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.MsgTitleEnum;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.ChatServiceMessage;
import net.sunwukong.www.marketing.bean.ChoiceUserPay;
import net.sunwukong.www.marketing.server.dao.ChatServiceMessageMapper;
import net.sunwukong.www.marketing.server.dao.ChoiceUserPayMapper;
import net.sunwukong.www.marketing.server.service.IChatServiceMessageService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IChatServiceMessageServiceImpl
 * @Author: kangdong
 * @Date: 2018/7/20 下午2:13
 * @Description: 系统服务消息实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class IChatServiceMessageServiceImpl implements IChatServiceMessageService {
    private static final Logger log = LoggerFactory.getLogger(IChatServiceMessageServiceImpl.class);

    @Autowired
    ChatServiceMessageMapper chatServiceMessageMapper;

    @Autowired
    ChoiceUserPayMapper choiceUserPayMapper;

    @Override
    public ResponseData addChatServiceMessage(ChatServiceMessage chatServiceMessage,int type) {
        chatServiceMessage.setId(DataBaseTool.createId());
        chatServiceMessage.setSendDate(new Date());
        chatServiceMessage.setReadState(SysCode.CODE_10200001.getCode());
        int i = chatServiceMessageMapper.insert(chatServiceMessage);
        if (i<=0){
            throw new GrilException("添加服务通知失败");
        }

        //发送推送通知
        Map<String,Object> map = new HashMap<>();
        map.put("type",type);
        IMSendMessage.sendText(null,"users",SysCode.SYS_SWK.getCode(),chatServiceMessage.getUserNo(),map);
        return BaseResp.getInstance();
    }

    @Override
    public ResponseData addChatServiceMessage(ChatServiceMessage chatServiceMessage) {
        return addChatServiceMessage(chatServiceMessage,1);
    }

    /**
     * 定时查询用户下单未支付的订单，生成订单服务通知消息
     * 1、5分钟内未支付的订单choice_user_pay
     * 2、先判断系统服务消息是否有该条未支付通知，如果有，则不新增通知信息
     * 2、生成通知消息chat_service_message
     * @return
     */
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addOrderServiceMessage() {
        ResponseData responseData = new ResponseData();
        try {
            //第一步
            List<ChoiceUserPay> lists = choiceUserPayMapper.getNotPayByTime(1);
            for(ChoiceUserPay list:lists) {
                //第二步
                Boolean result = chatServiceMessageMapper.getByUserNoAndPayNo(list.getUserNo(), list.getPayNo());
                if(!result) {//true表示已经推送过，为false才推送
                    //第三步
                    ChatServiceMessage message = new ChatServiceMessage();
                    message.setId(DataBaseTool.createId());
                    message.setServiceNo(list.getPayNo());
                    message.setUserNo(list.getUserNo());
                    message.setNoticeType(SysCode.CODE_10390004.getCode());
                    message.setSendDate(new Date());
                    message.setSendName(SysCode.SYS_SWK.getMsg());
                    message.setSendText(ApiTool.StringToHtml("red","现金卷订单尚未支付，请您尽快完成支付！"));
                    message.setSendTitle(MsgTitleEnum.CODE_10011.getMsg());
                    message.setSendUrl("/h5/components/payPage.html?shareNo=&payNo=" + list.getPayNo() + "&userId=" + list.getUserNo());
                    message.setReadState(SysCode.CODE_10200001.getCode());

                    int i = chatServiceMessageMapper.insertSelective(message);
                    if (i<=0){
                        log.error("新增消息失败!");
                        responseData.setCode(StatusEnum.FAIL.getCode());
                        responseData.setMessage(StatusEnum.FAIL.getMsg());
                        responseData.setStatus(StatusEnum.FAIL);
                        return responseData;
                    }

                    //发送推送通知
                    Map<String,Object> map = new HashMap<>();
                    map.put("type",1);
                    IMSendMessage.sendText(null,"users",SysCode.SYS_SWK.getCode(),list.getUserNo(),map);
                    return BaseResp.getInstance();
                }
            }
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setStatus(StatusEnum.SUCCESS);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }
}
