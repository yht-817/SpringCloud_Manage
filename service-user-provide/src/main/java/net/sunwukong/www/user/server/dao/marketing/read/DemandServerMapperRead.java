package net.sunwukong.www.user.server.dao.marketing.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.server.model.DemandServer;
import org.apache.ibatis.annotations.Param;

public interface DemandServerMapperRead extends BaseMapper<DemandServer> {

    /**
     * 根据用户编码和需求状态获取需求单数
     * @param userNo        用户编码
     * @param serverState   服务状态
     * @return
     */
    int countByUserNoAndServerState(@Param("userNo") String userNo,
                                    @Param("serverState") String serverState);
}