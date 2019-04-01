package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.MsgTitleEnum;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.RelativeDateFormat;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.*;
import net.sunwukong.www.marketing.server.dao.*;
import net.sunwukong.www.marketing.server.service.IInformationEvaluateService;
import net.sunwukong.www.marketing.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IInformationEvaluateServiceImpl
 * @Author: kangdong
 * @Date: 2018/7/25 上午10:13
 * @Description: 资讯评论实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */
@Service
public class IInformationEvaluateServiceImpl extends BaseController implements IInformationEvaluateService {
    @Autowired
    InformationEvaluatePariseMapper informationEvaluatePariseMapper;

    @Autowired
    InformationEvaluateMapper informationEvaluateMapper;

    @Autowired
    InformationReplyMapper informationReplyMapper;

    @Autowired
    InformationMapper informationMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 分页获取文章的评论列表
     */
    public ResponseData evaluateList(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        try {
            // 获取资讯编码
            String informationNo = data.get("informationNo");
            // 获取用户编码
            String userno = data.get("userNo");
            // 获取最大的分页条数
            int end = Integer.valueOf(data.get("pageSize"));
            // 获取开始的页码
            int start = (Integer.valueOf(data.get("pageNo")) - 1) * end;
            List<Map<String, Object>> lists = informationEvaluateMapper.getPageList(userno, informationNo, start, end);
            lists.forEach(eMaps -> {
                // 转换资讯时间
                eMaps.put("replyDate", RelativeDateFormat.format((Date) eMaps.get("evaluateDate")));
            });
            responseData.setData(lists);
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setStatus(StatusEnum.SUCCESS);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return responseData;
    }


    /**
     * 获取一条评论
     *
     * @param data
     * @return
     */
    public ResponseData getOneEvaluate(Map<String, String> data) {
        String informationNo = data.get("informationNo");
        String evaluateNo = data.get("evaluateNo");
        String userNo = data.get("userNo");
        ResponseData responseData = new ResponseData();
        List<Map<String, Object>> evaluatevo = informationEvaluateMapper.getByEvaluateNo(informationNo, evaluateNo, userNo);
        evaluatevo.forEach(map -> {
            // 转换资讯时间
            map.put("evaluateDate", RelativeDateFormat.format((Date) map.get("evaluateDate")));
        });
        responseData.setData(evaluatevo);
        return responseData;
    }

    // 用户对资讯文章评论
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData evaluate(String userNo,String informationNo,String evaluateContent) {
        ResponseData responseData = new ResponseData();
        InformationEvaluate informationEvaluate = new InformationEvaluate();
        informationEvaluate.setUserNo(userNo);
        informationEvaluate.setEvaluateContent(evaluateContent);
        informationEvaluate.setInformationNo(informationNo);
        //保存评论信息
        addInformationEvaluate(informationEvaluate);
        return responseData;
    }

    /**
     * 回复评论
     * @param informationReply
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addCommentsReply(InformationReply informationReply) {
        if (informationReply.getEvaluateNo()==""||informationReply.getEvaluateNo()==null){
            return evaluate(informationReply.getFromUserNo(),informationReply.getInformationNo(),informationReply.getReplyContent());
        }
        iInformationReplyService.addInformationReply(informationReply);
        //更新主评论回复数
        informationEvaluateMapper.updateUserComments(informationReply.getEvaluateNo());
        return BaseResp.getInstance();
    }

    /**
     * 保存文章评论信息
     * @param informationEvaluate
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addInformationEvaluate(InformationEvaluate informationEvaluate) {
        informationEvaluate.setId(DataBaseTool.createId());
        informationEvaluate.setEvaluateNo(DataBaseTool.createNo("pl"));
        informationEvaluate.setEvaluateDate(new Date());
        informationEvaluate.setPraiseNum(0);
        informationEvaluate.setReplyNum(0);
        int i = informationEvaluateMapper.insert(informationEvaluate);
        if (i<=0){
            throw new GrilException("保存文章评论失败");
        }

        //获取评论者的信息
        UserInfo userByUserNo = userInfoMapper.getUserByUserNo(informationEvaluate.getUserNo());

        //获取资讯发布者编码
        Information byInformationNo = informationMapper.findByInformationNo(informationEvaluate.getInformationNo());


        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
        chatServiceMessage.setSendName(userByUserNo.getNikeName());
        chatServiceMessage.setSendText(userByUserNo.getNikeName()+" 评论了您的文章");
        chatServiceMessage.setNoticeType(SysCode.CODE_10390004.getCode());
        chatServiceMessage.setUserNo(byInformationNo.getUserNo());
        chatServiceMessage.setSendUrl("http://swknet.oss-cn-hangzhou.aliyuncs.com/h5/components/news-detail.html?informationNo="+byInformationNo.getInformationNo()+"&userNo="+byInformationNo.getUserNo());
        chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10015.getMsg());
        iChatServiceMessageService.addChatServiceMessage(chatServiceMessage);

        //添加评论成功 更新资讯评论数量
        informationEvaluateMapper.updateComments(informationEvaluate.getInformationNo());

        return BaseResp.getInstance();
    }
}
