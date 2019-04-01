package net.sunwukong.www.chat.server.service.impl;

import com.sdkinfo.www.im.easemob.dao.IMSendMessage;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.TimeUtil;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.chat.bean.ChatServiceMessage;
import net.sunwukong.www.chat.server.dao.ChatServiceMessageMapper;
import net.sunwukong.www.chat.server.service.IChatServiceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明:服务消息服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/12 10:43
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IChatServiceMessageServiceImpl implements IChatServiceMessageService {

    @Autowired
    ChatServiceMessageMapper chatServiceMessageMapper;

    /**
     * 分页获取服务消息
     * @param userNo    用户编码
     * @param start     开始位置
     * @param size      检索长度
     * @return
     */
    @Override
    public ResponseData queryPageChatServiceMessage(String userNo, int start, int size) {
        ResponseData responseData = new ResponseData();
        List<Map<String, Object>> maps = chatServiceMessageMapper.queryPageChatServiceMessage(userNo, start, size);
        maps.forEach(map -> {
            map.put("sendDate", TimeUtil.getChatTimeStr(((Date)map.get("sendDate")).getTime()));
        });
        responseData.setData(maps);
        return responseData;
    }

    /**
     * 添加服务消息
     * @param chatServiceMessage    添加对象
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addChatServiceMessage(ChatServiceMessage chatServiceMessage) {
        chatServiceMessage.setId(DataBaseTool.createId());
        chatServiceMessage.setSendDate(new Date());
        chatServiceMessage.setReadState(SysCode.CODE_10200001.getCode());
        int i = chatServiceMessageMapper.insert(chatServiceMessage);
        if (i<=0){
            throw new GrilException("添加服务通知失败");
        }
        //发送推送通知
        Map<String,Object> map = new HashMap<>();
        map.put("type",1);
        IMSendMessage.sendText(null,"users",SysCode.SYS_SWK.getCode(),chatServiceMessage.getUserNo(),map);

        return BaseResp.getInstance();
    }

    /**
     * 根据业务单号查询业务状态
     * @param inviteNo  业务单号
     * @return
     */
    @Override
    public ChatServiceMessage findByServiceNo(String inviteNo) {
        ChatServiceMessage byServiceNo = chatServiceMessageMapper.findByServiceNo(inviteNo);
        return byServiceNo;
    }

    /**
     * 修改服务通知消息
     * @param byServiceNo
     */
    @Override
    public void updateChatServiceMessage(ChatServiceMessage byServiceNo) {
        byServiceNo.setReadDate(new Date());
        int i = chatServiceMessageMapper.updateByPrimaryKeySelective(byServiceNo);
        if (i<=0){
            throw new GrilException("修改服务通知消息失败");
        }
    }

    /**
     * 设置消息阅读状态
     * @param id        消息ID
     * @param readState 阅读状态
     * @return
     */
    @Override
    public ResponseData updateReadState(String id, String readState) {
        //获取服务消息信息
        ChatServiceMessage byId = findById(id);
        //设置新的状态
        byId.setReadState(readState);
        //修改数据信息
        updateChatServiceMessage(byId);
        return BaseResp.getInstance();
    }

    /**
     * 根据消息ID查询消息对象
     * @param id  消息ID
     * @return
     */
    @Override
    public ChatServiceMessage findById(String id) {
        ChatServiceMessage chatServiceMessage = chatServiceMessageMapper.selectByPrimaryKey(id);
        if (chatServiceMessage==null){
            throw new GrilException("未查询到相关服务消息");
        }
        return chatServiceMessage;
    }
}
