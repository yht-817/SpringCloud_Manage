package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.marketing.bean.SysConfig;
import net.sunwukong.www.marketing.server.dao.SysConfigMapper;
import net.sunwukong.www.marketing.server.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明:运营配置接口实现
 *
 * @author swk
 * @CreateDate 2018/8/30 10:26
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Service
public class ISysConfigServiceImpl implements ISysConfigService {
    @Autowired
    SysConfigMapper sysConfigMapper;

    @Override
    public SysConfig findBySysCode(String sysCode) {
        return sysConfigMapper.findBySysCode(sysCode);
    }
}
