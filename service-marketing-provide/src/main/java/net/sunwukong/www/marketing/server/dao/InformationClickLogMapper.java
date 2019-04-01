package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.marketing.bean.InformationClickLog;

public interface InformationClickLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(InformationClickLog record);

    int insertSelective(InformationClickLog record);

    InformationClickLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InformationClickLog record);

    int updateByPrimaryKey(InformationClickLog record);
}