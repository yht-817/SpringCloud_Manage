package net.sunwukong.www.marketing.server.dao;


import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据用户编码查询运营库用户信息
     *
     * @param userNo 用户编码
     * @return UserInfo
     */
    UserInfo getUserByUserNo(@Param(value = "userNo") String userNo);


    /**
     * 通过用户编码更新用户库、运营库、消息库的用户表数据
     *
     * @param userInfo
     * @return
     */
    int updateByUserNoSelective(UserInfo userInfo);
}