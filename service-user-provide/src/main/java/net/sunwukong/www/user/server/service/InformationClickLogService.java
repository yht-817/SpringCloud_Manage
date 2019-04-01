package net.sunwukong.www.user.server.service;

import java.util.List;
import java.util.Map;

public interface InformationClickLogService {

    /**
     * 获取用户资讯的点击列表
     * @param userNo    用户编码
     * @param start     开始位置
     * @param size      长度
     * @return
     */
    List<Map<String,String>> getUserClickList(String userNo, int start, int size);
}
