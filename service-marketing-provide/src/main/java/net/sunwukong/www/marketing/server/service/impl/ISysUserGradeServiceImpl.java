package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.server.dao.SysUserGradeMapper;
import net.sunwukong.www.marketing.server.service.ISysUserGradeService;
import net.sunwukong.www.user.bean.SysUserGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明:用户等级服务接口实现
 *
 * @author Mick
 * CreateDate 2018/6/25/025 18:45
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class ISysUserGradeServiceImpl implements ISysUserGradeService {

    @Autowired
    SysUserGradeMapper sysUserGradeMapper;


    @Override
    public ResponseData getSysUserGradeWhereEvaluate(int evaluate) {
        ResponseData responseData = new ResponseData();
        SysUserGrade sysUserGrade = sysUserGradeMapper.findByEvaluateBetween(evaluate);
        responseData.setData(sysUserGrade);
        return responseData;
    }
}
