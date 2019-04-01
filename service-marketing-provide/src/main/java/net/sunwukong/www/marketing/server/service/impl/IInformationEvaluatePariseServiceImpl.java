package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.MsgTitleEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.ChatServiceMessage;
import net.sunwukong.www.marketing.bean.InformationEvaluate;
import net.sunwukong.www.marketing.bean.InformationEvaluateParise;
import net.sunwukong.www.marketing.bean.UserInfo;
import net.sunwukong.www.marketing.server.dao.InformationEvaluateMapper;
import net.sunwukong.www.marketing.server.dao.InformationEvaluatePariseMapper;
import net.sunwukong.www.marketing.server.dao.UserInfoMapper;
import net.sunwukong.www.marketing.server.service.IInformationEvaluatePariseService;
import net.sunwukong.www.marketing.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IInformationEvaluatePariseServiceImpl
 * @Author: kangdong
 * @Date: 2018/7/25 下午1:15
 * @Description: 评论点赞实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class IInformationEvaluatePariseServiceImpl extends BaseController implements IInformationEvaluatePariseService {

    @Autowired
    InformationEvaluatePariseMapper informationEvaluatePariseMapper;

    @Autowired
    InformationEvaluateMapper informationEvaluateMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 设置文章评论点赞
     * @param informationNo 资讯编码
     * @param evaluateNo    评论编码
     * @param userNo        用户编码
     * @param liked         是否点赞（true：点赞 false：未点赞）
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData setEvaluateParise(String informationNo,String evaluateNo,String userNo,String liked) {
        //判断是是否已经点赞
        boolean isok = Boolean.parseBoolean(liked);
        //查询数据库是否拥有该条记录
        InformationEvaluateParise byUserNoAndEvaluateNoAndInformationNo = informationEvaluatePariseMapper.findByUserNoAndEvaluateNoAndInformationNo(userNo, evaluateNo, informationNo);
        if (isok){
            //已点赞
            if (byUserNoAndEvaluateNoAndInformationNo!=null){
                throw new GrilException("已点赞！");
            }
            InformationEvaluateParise parise = new InformationEvaluateParise();
            parise.setEvaluateNo(evaluateNo);
            parise.setInformationNo(informationNo);
            parise.setUserNo(userNo);
            addInformationEvaluateParise(parise);

            //更新评论点赞数
            informationEvaluateMapper.updateComments(informationNo);
        }else {
            //取消
            if (byUserNoAndEvaluateNoAndInformationNo==null){
                throw new GrilException("已取消！");
            }
            informationEvaluatePariseMapper.deleteByPrimaryKey(byUserNoAndEvaluateNoAndInformationNo.getId());
            //更新评论点赞数
        }
        return BaseResp.getInstance();
    }

    /**
     * 保存文章评论点赞信息
     * @param informationEvaluateParise
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addInformationEvaluateParise(InformationEvaluateParise informationEvaluateParise) {
        informationEvaluateParise.setId(DataBaseTool.createId());
        informationEvaluateParise.setPariseDate(new Date());
        int i = informationEvaluatePariseMapper.insert(informationEvaluateParise);
        if (i<=0){
            throw new GrilException("保存评论点赞信息失败");
        }
        //获取点赞者的信息
        UserInfo userByUserNo = userInfoMapper.getUserByUserNo(informationEvaluateParise.getUserNo());

        //获取评论信息
        InformationEvaluate informationEvaluate = informationEvaluateMapper.getByEvaluateNoAndInformationNo(informationEvaluateParise.getInformationNo(), informationEvaluateParise.getEvaluateNo());

        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
        chatServiceMessage.setSendName(userByUserNo.getNikeName());
        chatServiceMessage.setSendText(userByUserNo.getNikeName()+" 点赞了您的评论");
        chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
        chatServiceMessage.setUserNo(informationEvaluate.getUserNo());
        chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10016.getMsg());

        iChatServiceMessageService.addChatServiceMessage(chatServiceMessage);
        return BaseResp.getInstance();
    }
}
