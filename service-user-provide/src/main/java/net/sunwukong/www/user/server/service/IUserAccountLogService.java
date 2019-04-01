package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserAccountLog;

/**
 * 说明:账户金额变动服务接口
 *
 * @author Mick
 * CreateDate 2018/6/15 17:34
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IUserAccountLogService {

    /**
     * 获取昨日金币
     * @param userNo    用户编码
     * @return          返回响应数据
     */
    ResponseData getYesterDayCoin(String userNo);

    /**
     * 分页获取用户资金变动记录
     * @param userNo    用户编码
     * @param pageNo    当前页
     * @param pageSize  页面长度
     * @return
     */
    ResponseData getDetailsPage(String userNo, String pageNo, String pageSize);

    /**
     * 添加用户资金变动记录
     * @param userAccountLog    用户资金变动对象
     * @return
     */
    ResponseData addUserAccountLog(UserAccountLog userAccountLog);
}
