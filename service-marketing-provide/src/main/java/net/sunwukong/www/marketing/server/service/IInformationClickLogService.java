package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.InformationClickLog;

import java.util.Map;

/**
 * 说明:资讯信息表点击记录服务接口
 *
 * @author Mick
 * CreateDate 2018/6/13/013 14:20
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IInformationClickLogService {

    /**
     * 添加资讯粉丝
     * @param informationClickLog   粉丝点击对象
     * @return                      返回响应对象
     */
    ResponseData addUserFans(InformationClickLog informationClickLog);
}
