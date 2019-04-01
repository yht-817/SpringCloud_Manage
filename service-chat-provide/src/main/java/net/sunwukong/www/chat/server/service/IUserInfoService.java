package net.sunwukong.www.chat.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.bean.UserInfo;

/**
 * 说明:用户服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/10 16:55
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IUserInfoService {
    /**
     * 分页检索用户
     * @param userNo    用户编码
     * @param content   检索内容
     * @param content   检索内容
     * @param start     开始位置
     * @param size      索取长度
     * @return
     */
    ResponseData queryPageSearchUser(String userNo,String content,int start,int size);

    /**
     * 根据用户编码查询用户信息
     * @param userNo    用户编码
     * @return
     */
    UserInfo findByUserNo(String userNo);

    /**
     * 根据用户编码获取用户ID
     * @param userNo    用户编码
     * @return
     */
    ResponseData getUserId(String userNo);
}
