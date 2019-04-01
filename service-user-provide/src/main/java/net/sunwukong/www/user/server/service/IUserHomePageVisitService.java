package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserHomePageVisit;
import net.sunwukong.www.user.output.Visit;

/**
 * 说明:个人主页访问量服务接口
 *
 * @author Mick
 * CreateDate 2018/6/11 16:49
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IUserHomePageVisitService {

    /**
     * 获取用户主页访问量
     * @param userNo        用户编码
     * @return              返回响应对象
     */
    Visit getVisit(String userNo);

    /**
     * 添加用户主页访问量
     * @param userHomePageVisit 待添加对象
     * @return                  返回响应对象
     */
    ResponseData addUserVisit(UserHomePageVisit userHomePageVisit);
}
