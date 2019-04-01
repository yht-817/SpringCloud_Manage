package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.CategoryOne;
import net.sunwukong.www.user.bean.UserCategory;

import java.util.List;

public interface UserCategoryMapperRead extends BaseMapper<UserCategory> {
    List<UserCategory> findByUserNo(String userNo);
}