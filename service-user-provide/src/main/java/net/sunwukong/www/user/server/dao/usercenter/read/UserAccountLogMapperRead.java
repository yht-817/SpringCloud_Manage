package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.UserAccountLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserAccountLogMapperRead extends BaseMapper<UserAccountLog> {
    /**
     *
     * @param userNo
     * @return
     */
    List<UserAccountLog> findByUserNoList(String userNo);

    List<Map<String,Object>> getAllListPage(@Param("userNo") String userNo,
                                            @Param("start")int start,
                                            @Param("end")int end);
}