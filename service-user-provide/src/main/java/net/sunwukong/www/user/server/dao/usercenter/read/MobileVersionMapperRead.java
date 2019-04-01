package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.server.model.MobileVersion;
import org.apache.ibatis.annotations.Param;

public interface MobileVersionMapperRead extends BaseMapper<MobileVersion> {

    /**
     * 根据应用名称和应用类型查询版本列表
     * @param appName   应用名称
     * @param appType   应用类型
     * @return
     */
    MobileVersion findByAppNameAndAppTypeOrderByAppVersion(@Param("appName") String appName, @Param("appType") String appType);
}