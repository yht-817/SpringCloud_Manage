package net.sunwukong.www.marketing.server.dao;


import net.sunwukong.www.marketing.bean.ChoiceUserPayDetail;

public interface ChoiceUserPayDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(ChoiceUserPayDetail record);

    int insertSelective(ChoiceUserPayDetail record);

    ChoiceUserPayDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChoiceUserPayDetail record);

    int updateByPrimaryKey(ChoiceUserPayDetail record);
}