package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.SysCode;

import java.util.List;

public interface SysCodeMapper extends BaseMapper<SysCode> {
    List<SysCode> findByCodeType(String codeType);
}