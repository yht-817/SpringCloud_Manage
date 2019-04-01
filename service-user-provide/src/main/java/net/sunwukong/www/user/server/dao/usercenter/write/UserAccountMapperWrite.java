package net.sunwukong.www.user.server.dao.usercenter.write;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.UserAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserAccountMapperWrite extends BaseMapper<UserAccount> {
    /**
     * 根据用户编码 累加用户金币
     * @param map   用户编码 累加金币
     * @return
     */
    int updateByUserNoPlusAccount(Map<String, Object> map);
}