package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.server.dao.UserCategoryMapper;
import net.sunwukong.www.marketing.server.service.IUserCategoryService;
import net.sunwukong.www.user.bean.UserCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 说明:用户服务类目服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/23 14:39
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserCategoryServiceImpl implements IUserCategoryService {

    @Autowired
    UserCategoryMapper userCategoryMapper;

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserCategory(UserCategory userCategory) {
        ResponseData responseData = new ResponseData();
        userCategory.setId(DataBaseTool.createId());
        int i = userCategoryMapper.insert(userCategory);
        if (i<=0){
            throw new GrilException("添加用户服务类目失败");
        }
        return responseData;
    }
}
