package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.UserHomePageVisit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface UserHomePageVisitMapperRead extends BaseMapper<UserHomePageVisit> {
    /**
     * 根据用户编码查询访问量
     * @param userNo    用户编码
     * @return          返回响应对象
     */
    int countByUserNo(String userNo);

    /**
     * 根据用户编码and日期查询访问量
     * @param userNo    用户编码
     * @param date      日期
     * @return          返回响应对象
     */
    int countByUserNoAndVisitDate(@Param("userNo") String userNo,
                                  @Param("date") Date date);
}