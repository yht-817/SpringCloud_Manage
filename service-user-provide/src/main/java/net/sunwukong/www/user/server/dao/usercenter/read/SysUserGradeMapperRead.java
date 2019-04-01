package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.SysUserGrade;

public interface SysUserGradeMapperRead extends BaseMapper {
    /**
     * 根据用户评分获取对应等级
     * @param evaluate
     * @return
     */
    SysUserGrade findByEvaluateBetween(int evaluate);
}