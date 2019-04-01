package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.UserAccountLog;
import net.sunwukong.www.marketing.server.dao.UserAccountLogMapper;
import net.sunwukong.www.marketing.server.service.IUserAccountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 说明:账户金额变动服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/5 15:10
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserAccountLogServiceImpl implements IUserAccountLogService {

    @Autowired
    UserAccountLogMapper userAccountLogMapper;

    /**
     * 添加用户资金明细
     *
     * @param userAccountLog 用户资金变动对象
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addUserAccountLog(UserAccountLog userAccountLog) {
        ResponseData responseData = new ResponseData();
        userAccountLog.setId(DataBaseTool.createId());
        userAccountLog.setChangeDate(new Date());
        int i = userAccountLogMapper.insertSelective(userAccountLog);
        if (i <= 0) {
            throw new GrilException("添加用户资金明细失败");
        }
        return responseData;
    }
}
