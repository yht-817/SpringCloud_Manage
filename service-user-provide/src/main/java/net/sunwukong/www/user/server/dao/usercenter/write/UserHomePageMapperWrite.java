package net.sunwukong.www.user.server.dao.usercenter.write;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.UserHomePage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserHomePageMapperWrite extends BaseMapper<UserHomePage> {

    /**
     * 动态修改字段内容
     * @param id        ID
     * @param fields    字段名
     * @param path      修改内容
     * @return          返回响应行
     */
    int updateHomePhotofield(@Param("id")String id, @Param("fields")String fields, @Param("path")String path);
}