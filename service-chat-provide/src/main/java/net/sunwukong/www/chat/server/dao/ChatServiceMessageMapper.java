package net.sunwukong.www.chat.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.chat.bean.ChatServiceMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChatServiceMessageMapper extends BaseMapper<ChatServiceMessage>{

    List<Map<String,Object>> queryPageChatServiceMessage(@Param("userNo") String userNo,
                                                         @Param("start")int start,
                                                         @Param("size")int size);

    /**
     * 根据业务编码查询业务状态
     * @param inviteNo  业务编码
     * @return
     */
    ChatServiceMessage findByServiceNo(String inviteNo);
}