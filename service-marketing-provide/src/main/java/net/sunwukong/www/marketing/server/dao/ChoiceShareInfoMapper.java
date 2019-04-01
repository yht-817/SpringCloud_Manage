package net.sunwukong.www.marketing.server.dao;


import net.sunwukong.www.marketing.bean.ChoiceShareInfo;

public interface ChoiceShareInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ChoiceShareInfo record);

    int insertSelective(ChoiceShareInfo record);

    ChoiceShareInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChoiceShareInfo record);

    int updateByPrimaryKey(ChoiceShareInfo record);

    /**
     * 通过唯一分享单号查询分享信息
     * @param shareNo
     * @return
     */
    ChoiceShareInfo getByShareNo(String shareNo);
}