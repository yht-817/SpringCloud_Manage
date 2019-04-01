package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.SysUserGrade;

/**
 * 说明:用户等级服务接口
 *
 * @author Mick
 * CreateDate 2018/7/4/004 23:51
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface SysUserGradeMapper extends BaseMapper<SysUserGrade> {
    /**
     * 根据用户评分获取对应等级
     * @param evaluate
     * @return
     */
    SysUserGrade findByEvaluateBetween(int evaluate);
}
