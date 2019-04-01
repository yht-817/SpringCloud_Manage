package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.UserApplyPhoto;
import net.sunwukong.www.marketing.server.dao.UserApplyPhotoMapper;
import net.sunwukong.www.marketing.server.service.IUserApplyPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 说明:个体/机构申请图片服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/28 17:09
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserApplyPhotoServiceImpl implements IUserApplyPhotoService {
    @Autowired
    UserApplyPhotoMapper userApplyPhotoMapper;

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserApplyPhoto(UserApplyPhoto userApplyPhoto) {
        ResponseData responseData = new ResponseData();
        userApplyPhoto.setId(DataBaseTool.createId());
        int i = userApplyPhotoMapper.insert(userApplyPhoto);
        if (i<=0){
            throw new GrilException("添加用户服务城市失败");
        }
        return responseData;
    }
}
