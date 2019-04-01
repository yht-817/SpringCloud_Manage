package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.marketing.bean.PlatformRecharge;

public interface PlatformRechargeMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlatformRecharge record);

    int insertSelective(PlatformRecharge record);

    PlatformRecharge selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlatformRecharge record);

    int updateByPrimaryKey(PlatformRecharge record);
}