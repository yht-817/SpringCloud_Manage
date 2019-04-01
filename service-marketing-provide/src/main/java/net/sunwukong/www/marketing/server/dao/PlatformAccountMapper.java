package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.marketing.bean.PlatformAccount;

public interface PlatformAccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlatformAccount record);

    int insertSelective(PlatformAccount record);

    PlatformAccount selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlatformAccount record);

    int updateByPrimaryKey(PlatformAccount record);

    /**
     * 获取平台账户金额
     * @return
     */
    PlatformAccount getPlatformAccount();
}