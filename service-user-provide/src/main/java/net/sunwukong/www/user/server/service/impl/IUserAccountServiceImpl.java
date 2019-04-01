package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.bean.UserAccount;
import net.sunwukong.www.user.server.dao.usercenter.read.UserAccountMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserAccountMapperWrite;
import net.sunwukong.www.user.server.service.IUserAccountService;
import net.sunwukong.www.user.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明:用户账户服务实现
 *
 * @author Mick
 * CreateDate 2018/6/15 15:08
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IUserAccountServiceImpl extends BaseController implements IUserAccountService {

    @Autowired
    UserAccountMapperRead userAccountMapperRead;

    @Autowired
    UserAccountMapperWrite userAccountMapperWrite;

    /**
     * 根据用户编码获取用户金额
     * @param userNo    用户编码
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData getallCoin(String userNo) {
        UserAccount userAccount = userAccountMapperRead.findByUserNoAccount(userNo);
        Map<String,Object> map = new HashMap<>();
        if (userAccount!=null){
            map.put("allCoin",userAccount.getAccountAmount().add(userAccount.getCashAccountAmount()).intValue());
        }else {
            map.put("allCoin",0);
        }
        return BaseResp.getInstance(map);
    }

    /**
     * 添加用户账户信息
     * @param userAccount
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserAccount(UserAccount userAccount) {
        userAccount.setId(DataBaseTool.createId());
        userAccount.setAccountAmount(new BigDecimal(0));
        userAccount.setForzenAccountAmount(new BigDecimal(0));
        userAccount.setCashAccountAmount(new BigDecimal(0));
        int insert = userAccountMapperWrite.insert(userAccount);
        if (insert<=0){
            throw new GrilException("添加用户账户信息失败");
        }
        return BaseResp.getInstance();
    }

    /**
     * 获取个人账户余额信息
     * @param userNo    用户编码
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public UserAccount getUserAccount(String userNo) {
        UserAccount userAccount = userAccountMapperRead.findByUserNoAccount(userNo);
        if (userAccount==null){
            UserAccount account = new UserAccount();
            account.setUserNo(userNo);
            addUserAccount(account);
            return account;
        }else {
            return userAccount;
        }
    }

    /**
     * 修改个人账户余额信息
     * @param userAccount
     * @return
     */
    @Override
    public ResponseData updateUserAccount(UserAccount userAccount) {
        int i = userAccountMapperWrite.updateByPrimaryKey(userAccount);
        if (i<=0){
            throw new GrilException("修改用户账户信息失败");
        }
        return BaseResp.getInstance();
    }
}
