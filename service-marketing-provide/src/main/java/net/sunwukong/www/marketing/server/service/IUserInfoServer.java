package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.UserInfo;

/**
 * Created by tungkang on 2018/6/16.
 */
public interface IUserInfoServer {

    /**
     * 根据用户编码获取用户信息
     * @param userNo
     * @return
     */
    ResponseData getUserByUserNo(String userNo);

    /**
     * 检查用户信息是否完善
     * @param userNo
     * @return
     */
    ResponseData getUserMsgIsokPerfect(String userNo);


    /**
     * 通过用户编码更新用户库、运营库、消息库的用户表信息
     * @Author: kangdong
     * @param userInfo
     * @return
     */
    ResponseData updateByUserNoSelective(UserInfo userInfo);
}
