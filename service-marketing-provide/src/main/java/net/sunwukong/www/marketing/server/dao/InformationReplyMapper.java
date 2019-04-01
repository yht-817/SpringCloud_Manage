package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.InformationReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InformationReplyMapper extends BaseMapper<InformationReply> {

    List<Map<String,Object>> queryPageReplyList(@Param("evaluateNo") String evaluateNo,
                                                @Param("start") int start,
                                                @Param("end") int end);
}