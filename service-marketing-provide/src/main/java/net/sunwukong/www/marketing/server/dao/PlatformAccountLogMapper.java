package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.marketing.bean.PlatformAccountLog;

public interface PlatformAccountLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlatformAccountLog record);

    int insertSelective(PlatformAccountLog record);

    PlatformAccountLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlatformAccountLog record);

    int updateByPrimaryKey(PlatformAccountLog record);
}