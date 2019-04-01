package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.marketing.bean.ChoiceInfoSearchKeyword;

import java.util.List;

public interface ChoiceInfoSearchKeywordMapper {
    int deleteByPrimaryKey(String id);

    int insert(ChoiceInfoSearchKeyword record);

    int insertSelective(ChoiceInfoSearchKeyword record);

    ChoiceInfoSearchKeyword selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChoiceInfoSearchKeyword record);

    int updateByPrimaryKey(ChoiceInfoSearchKeyword record);


    /**
     * 推荐热门关键词列表
     * @return
     */
    List<ChoiceInfoSearchKeyword> getHotList();
}