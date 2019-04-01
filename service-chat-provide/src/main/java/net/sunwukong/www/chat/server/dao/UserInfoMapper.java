package net.sunwukong.www.chat.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.chat.bean.UserInfo;
import net.sunwukong.www.chat.vo.QueryUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo>{

    List<QueryUser> queryPageSearchUser(@Param("content") String content,
                                        @Param("start") int start,
                                        @Param("size") int size);

    UserInfo findByUserNo(String userNo);


    String getUserId(String userNo);
}