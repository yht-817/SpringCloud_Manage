package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;

/**
 * 说明:用户等级服务接口
 *
 * @author Mick
 * CreateDate 2018/6/25/025 18:42
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface ISysUserGradeService {

    /**
     * 根据用户评分查询对应等级
     * @param evaluate
     * @return
     */
    ResponseData getSysUserGradeWhereEvaluate(int evaluate);
}
