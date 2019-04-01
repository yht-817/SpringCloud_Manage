package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.UserDataPhoto;

import java.util.Map;

public interface UserDataPhotoMapperRead extends BaseMapper<UserDataPhoto>{
    UserDataPhoto findByUserNo(String userNo);
}