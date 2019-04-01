package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.InformationReply;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/25.
 */
public interface IInformationReplyService {

    /**
     * 分页查询回复；列表
     * @param evaluateNo    主评论编码
     * @param start         开始位置
     * @param size          长度
     * @return
     */
    ResponseData queryPageReplyList(String evaluateNo,int start,int size);

    /**
     * 添加用户子评论
     * @param informationReply
     * @return
     */
    ResponseData addInformationReply(InformationReply informationReply);
}
