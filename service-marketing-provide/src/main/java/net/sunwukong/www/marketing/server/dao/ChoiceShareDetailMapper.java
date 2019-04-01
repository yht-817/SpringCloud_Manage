package net.sunwukong.www.marketing.server.dao;


import net.sunwukong.www.marketing.bean.ChoiceShareDetail;

public interface ChoiceShareDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(ChoiceShareDetail record);

    int insertSelective(ChoiceShareDetail record);

    ChoiceShareDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChoiceShareDetail record);

    int updateByPrimaryKey(ChoiceShareDetail record);
}