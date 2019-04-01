package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.user.server.dao.marketing.read.InformationClickLogMapperRead;
import net.sunwukong.www.user.server.dao.marketing.write.InformationClickLogMapperWrite;
import net.sunwukong.www.user.server.service.InformationClickLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InformationClickLogServiceImpl implements InformationClickLogService {

    @Autowired
    InformationClickLogMapperRead informationClickLogMapperRead;

    @Autowired
    InformationClickLogMapperWrite informationClickLogMapperWrite;

    /**
     * 获取用户资讯的点击列表
     * @param userNo    用户编码
     * @param start     开始位置
     * @param size      长度
     * @return
     */
    @Override
    public List<Map<String, String>> getUserClickList(String userNo,int start,int size) {
        return informationClickLogMapperRead.getUserClickList(userNo,start,size);
    }
}
