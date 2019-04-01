package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.SysConfig;

public interface SysConfigMapper extends BaseMapper<SysConfigMapper> {

    /**
     * 根据代码查询配置信息
     * @param sysCode   配置代码
     * @return
     */
    SysConfig findBySysCode(String sysCode);
}