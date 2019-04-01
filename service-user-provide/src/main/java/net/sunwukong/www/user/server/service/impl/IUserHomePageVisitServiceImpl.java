package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.bean.UserHomePageVisit;
import net.sunwukong.www.user.output.Visit;
import net.sunwukong.www.user.server.dao.usercenter.read.UserHomePageVisitMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserHomePageVisitMapperWrite;
import net.sunwukong.www.user.server.service.IUserHomePageVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 说明:个人主页访问实现类
 *
 * @author Mick
 * CreateDate 2018/6/11 17:04
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IUserHomePageVisitServiceImpl implements IUserHomePageVisitService {

    @Autowired
    UserHomePageVisitMapperWrite userHomePageVisitMapperWrite;

    @Autowired
    UserHomePageVisitMapperRead userHomePageVisitMapperRead;

    /**
     * 获取用户主页访问量
     * @param userNo        用户编码
     * @return
     */
    @Override
    public Visit getVisit(String userNo) {
        int total = userHomePageVisitMapperRead.countByUserNo(userNo);

        int today = 0;
        if (total>0){
            today = userHomePageVisitMapperRead.countByUserNoAndVisitDate(userNo, new Date());
        }
        Visit visit = new Visit();
        visit.setTotal(total);
        visit.setToday(today);
        return visit;
    }

    /**
     * 添加用户主页访问量
     * @param userHomePageVisit 待添加对象
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserVisit(UserHomePageVisit userHomePageVisit) {
        ResponseData responseData = new ResponseData();
        //如果访问者与被访问者不相等 则添加访问记录 否则不做任何添加
        if (!userHomePageVisit.getUserNo().equals(userHomePageVisit.getVisitNo())) {
            userHomePageVisit.setId(DataBaseTool.createId());
            userHomePageVisit.setVisitDate(new Date());
            int i = userHomePageVisitMapperWrite.insertSelective(userHomePageVisit);
            if (i <= 0) {
                responseData.setStatus(StatusEnum.FAIL);
                return responseData;
            }
        }
        return responseData;
    }
}
