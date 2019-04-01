package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.UserHomePage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserHomePageMapperRead extends BaseMapper<UserHomePage> {


    /**
     * 根据用户编码查询个人主页信息
     * @param userNo
     * @return
     */
    UserHomePage findByUserNo(String userNo);
}