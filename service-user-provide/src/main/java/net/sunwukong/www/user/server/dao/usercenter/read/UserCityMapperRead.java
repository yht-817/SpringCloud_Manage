package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.UserCity;

import java.util.List;

public interface UserCityMapperRead extends BaseMapper<UserCity> {
    List<UserCity> findByUserNo(String userNo);
}