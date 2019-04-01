package net.sunwukong.www.user.server.dao.marketing.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.server.model.InformationClickLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InformationClickLogMapperRead extends BaseMapper<InformationClickLog> {

    /**
     * 获取用户资讯的点击列表
     * @param userNo    用户编码
     * @param start     开始位置
     * @param size      长度
     * @return
     */
    List<Map<String,String>> getUserClickList(@Param("userNo") String userNo,
                                              @Param("start") int start,
                                              @Param("size") int size);
}