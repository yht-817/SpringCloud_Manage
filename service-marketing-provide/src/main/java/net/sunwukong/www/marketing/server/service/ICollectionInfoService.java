package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.CollectionInfo;

import java.util.Map;

/**
 * 说明:收藏信息服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/22 12:05
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface ICollectionInfoService {
    /**
     * 获取用户收藏信息
     * @param userNo    用户编码
     * @param pageNo    当前页面
     * @param pageSize  页面长度
     * @param collectionType  收藏类型
     * @return
     */
    ResponseData getUserCollectionInfoPage(String userNo,int pageNo,int pageSize,String collectionType);


    /**
     * 设置用户收藏
     * @param data
     * @return
     */
    ResponseData setUserCollection(Map<String, String> data);

    /**
     * 添加收藏
     * @param collectionInfo
     * @return
     */
    ResponseData addCollectionInfo(CollectionInfo collectionInfo);
}
