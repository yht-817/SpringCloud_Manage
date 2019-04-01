package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;

import java.util.List;

/**
 * 说明:目录服务接口
 *
 * @author Mick
 * CreateDate 2018/6/22/022 22:00
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface ICategoryService {
    /**
     * 获取服务目录列表
     * @return
     */
    ResponseData getCategoryList();

    ResponseData getCategoryOneConnectCategoryTwo(String categoryOneNo);

    /**
     * 根据多个一级目录获取数据信息
     * @param oneListNew
     * @return
     */
    ResponseData getCategoryOneConnectCategoryTwo(List<String> oneListNew);
}
