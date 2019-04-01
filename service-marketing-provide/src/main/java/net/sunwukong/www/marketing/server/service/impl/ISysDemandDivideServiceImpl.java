package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.marketing.bean.SysDemandDivide;
import net.sunwukong.www.marketing.server.dao.SysDemandDivideMapper;
import net.sunwukong.www.marketing.server.service.ISysDemandDivideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明:分成服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/8 14:01
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class ISysDemandDivideServiceImpl implements ISysDemandDivideService {

    @Autowired
    SysDemandDivideMapper sysDemandDivideMapper;

    @Override
    public SysDemandDivide selectOne() {
        SysDemandDivide sysDemandDivide = sysDemandDivideMapper.selectOne();
        return sysDemandDivide;
    }
}
