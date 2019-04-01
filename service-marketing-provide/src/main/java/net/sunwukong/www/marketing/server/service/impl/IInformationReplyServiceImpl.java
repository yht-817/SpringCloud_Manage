package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.MsgTitleEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.RelativeDateFormat;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.*;
import net.sunwukong.www.marketing.server.dao.InformationEvaluateMapper;
import net.sunwukong.www.marketing.server.dao.InformationMapper;
import net.sunwukong.www.marketing.server.dao.InformationReplyMapper;
import net.sunwukong.www.marketing.server.dao.UserInfoMapper;
import net.sunwukong.www.marketing.server.service.IInformationReplyService;
import net.sunwukong.www.marketing.server.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IInformationReplyServiceImpl
 * @Author: kangdong
 * @Date: 2018/7/25 上午10:15
 * @Description: 评论回复实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */
@Service
public class IInformationReplyServiceImpl extends BaseController implements IInformationReplyService {

    private static final Logger log = LoggerFactory.getLogger(IInformationReplyServiceImpl.class);

    @Autowired
    InformationReplyMapper informationReplyMapper;

    @Autowired
    InformationEvaluateMapper informationEvaluateMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    InformationMapper informationMapper;


    /**
     * 分页获取子评论
     * @param evaluateNo    主评论编码
     * @param start         开始位置
     * @param size          长度
     * @return
     */
    public ResponseData queryPageReplyList(String evaluateNo,int start,int size) {
        ResponseData responseData = new ResponseData();
        List<Map<String, Object>> maps = informationReplyMapper.queryPageReplyList(evaluateNo, start, size);
        maps.forEach(map->{
            map.put("replyDate", RelativeDateFormat.format((Date) map.get("replyDate")));
        });
        responseData.setData(maps);
        return responseData;
    }

    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addInformationReply(InformationReply informationReply) {
        informationReply.setId(DataBaseTool.createId());
        informationReply.setReplyDate(new Date());
        informationReply.setReplyNo(DataBaseTool.createNo("hf"));
        int i = informationReplyMapper.insert(informationReply);
        if (i<=0){
            throw new GrilException("添加回复评论失败");
        }

        //获取评论者的信息
        UserInfo fromUser = userInfoMapper.getUserByUserNo(informationReply.getFromUserNo());

        //获取被评论者信息
        InformationEvaluate informationEvaluate = informationEvaluateMapper.getByEvaluateNoAndInformationNo(informationReply.getInformationNo(), informationReply.getEvaluateNo());

        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
        chatServiceMessage.setSendName(fromUser.getNikeName());
        chatServiceMessage.setSendText(fromUser.getNikeName()+" 回复了您的评论");
        chatServiceMessage.setNoticeType(SysCode.CODE_10390004.getCode());
        chatServiceMessage.setUserNo(informationEvaluate.getUserNo());
        chatServiceMessage.setSendUrl("http://swknet.oss-cn-hangzhou.aliyuncs.com/h5/components/news-detail.html?informationNo="+informationEvaluate.getInformationNo()+"&userNo="+informationEvaluate.getUserNo());
        chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10015.getMsg());

        iChatServiceMessageService.addChatServiceMessage(chatServiceMessage);
        return BaseResp.getInstance();
    }
}
