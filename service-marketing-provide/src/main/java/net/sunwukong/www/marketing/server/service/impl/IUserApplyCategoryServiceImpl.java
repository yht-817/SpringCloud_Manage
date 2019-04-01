package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.UserApplyCategory;
import net.sunwukong.www.marketing.server.dao.UserApplyCategoryMapper;
import net.sunwukong.www.marketing.server.service.IUserApplyCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 说明:个体/机构申请类目服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/28 16:54
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserApplyCategoryServiceImpl implements IUserApplyCategoryService {
    @Autowired
    UserApplyCategoryMapper userApplyCategoryMapper;

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserApplyCategory(UserApplyCategory userApplyCategory) {
        ResponseData responseData = new ResponseData();
        userApplyCategory.setId(DataBaseTool.createId());
        int i = userApplyCategoryMapper.insertSelective(userApplyCategory);
        if (i<=0){
            throw new GrilException("添加用户服务类目失败");
        }
        return responseData;
    }

    @Override
    public ResponseData getUserApplyCategory(String applyNo) {
        ResponseData responseData = new ResponseData();
        List<UserApplyCategory> byApplyNo = userApplyCategoryMapper.findByApplyNo(applyNo);
        responseData.setData(byApplyNo);
        return responseData;
    }

    @Override
    public ResponseData<List<UserApplyCategory>> getApplyNoAfterAuditCategoryList(Map<String, String> map) {
        ResponseData responseData = new ResponseData();
        List<UserApplyCategory> byApplyNo = userApplyCategoryMapper.findApplyNoAfterAuditCategoryList(map);
        responseData.setData(byApplyNo);
        return responseData;
    }
}
