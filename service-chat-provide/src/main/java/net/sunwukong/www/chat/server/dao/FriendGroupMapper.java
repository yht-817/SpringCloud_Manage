package net.sunwukong.www.chat.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.chat.bean.FriendGroup;
import net.sunwukong.www.chat.vo.QueryGroup;
import net.sunwukong.www.chat.vo.QueryGroupDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FriendGroupMapper extends BaseMapper<FriendGroup> {
    /**
     * 根据群聊编码查询群聊信息
     * @param groupNo   群编码
     * @return
     */
    FriendGroup findByGroupNo(String groupNo);

    /**
     * 检索群
     * @param content       检索内容
     * @param groupState    检索状态
     * @param start         开始位置
     * @param size          检索长度
     * @return  id,group_no,group_name,group_head
     */
    List<QueryGroup> queryPageSearchGroup(@Param("content") String content,
                                         @Param("groupState") String groupState,
                                         @Param("start") int start,
                                         @Param("size") int size);

    /**
     * 根据群编码和用户编码查询群信息
     * @param groupNo   群编码
     * @param userNo    用户编码
     * @return
     */
    FriendGroup findByGroupNoAndUserNo(@Param("groupNo")String groupNo,
                                       @Param("userNo")String userNo);

    /**
     * 获取群详情
     * @param groupNo   群编码
     * @return
     */
    QueryGroupDetails queryGroupDetails(String groupNo);
}