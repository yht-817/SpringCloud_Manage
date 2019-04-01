package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.UserApplyScope;
import net.sunwukong.www.marketing.server.dao.UserApplyScopeMapper;
import net.sunwukong.www.marketing.server.service.IUserApplyScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 说明:个体/机构申请服务城市服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/3 13:15
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserApplyScopeServiceImpl implements IUserApplyScopeService {

    @Autowired
    UserApplyScopeMapper userApplyScopeMapper;

    @Override
    public ResponseData addUserApplyScope(UserApplyScope userApplyScope) {
        ResponseData responseData = new ResponseData();
        userApplyScope.setId(DataBaseTool.createId());
        int i = userApplyScopeMapper.insert(userApplyScope);
        if (i<=0){
            throw new GrilException("添加用户服务城市失败");
        }
        return responseData;
    }

    @Override
    public ResponseData updateUserApplyScope(UserApplyScope userApplyScope) {
        ResponseData responseData = new ResponseData();
        int i = userApplyScopeMapper.updateByPrimaryKey(userApplyScope);
        if (i<=0){
            throw new GrilException("更新用户服务城市失败");
        }
        return responseData;
    }

    @Override
    public ResponseData getApplyNoAfterAuditScopeList(Map<String, String> map) {
        ResponseData responseData = new ResponseData();
        List<UserApplyScope> byUserNoAndAuditStateList = userApplyScopeMapper.findApplyNoAfterAuditScopeList(map);
        responseData.setData(byUserNoAndAuditStateList);
        return responseData;
    }
}
