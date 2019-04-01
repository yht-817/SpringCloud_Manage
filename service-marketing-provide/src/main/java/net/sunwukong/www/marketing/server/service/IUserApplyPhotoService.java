package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.UserApplyPhoto;

/**
 * 说明:个体/机构申请图片服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/28 17:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IUserApplyPhotoService {
    /**
     * 添加用户证件图片
     * @param userApplyPhoto
     * @return
     */
    ResponseData addUserApplyPhoto(UserApplyPhoto userApplyPhoto);
}
