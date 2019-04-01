package net.sunwukong.www.user.server.service.impl;

import com.sdkinfo.www.core.util.ObjectUtil;
import com.sdkinfo.www.core.util.StrUtil;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.data.DataMap;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.user.bean.UserInfo;
import net.sunwukong.www.user.bean.UserOtherLogin;
import net.sunwukong.www.user.server.dao.chatmess.write.UserInfoMapperChatMessWrite;
import net.sunwukong.www.user.server.dao.marketing.write.UserInfoMapperMarketingWrite;
import net.sunwukong.www.user.server.dao.usercenter.read.UserInfoMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserInfoMapperWrite;
import net.sunwukong.www.user.server.service.IUserSettingService;
import net.sunwukong.www.user.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 说明:用户设置服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/24 14:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserSettingServiceImpl extends BaseController implements IUserSettingService {

    @Autowired
    UserInfoMapperChatMessWrite userInfoMapperChatMessWrite;

    @Autowired
    UserInfoMapperMarketingWrite userInfoMapperMarketingWrite;
    @Autowired
    UserInfoMapperWrite userInfoMapperWrite;

    @Autowired
    UserInfoMapperRead userInfoMapperRead;

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData bindAccount(Map<String, String> map) {
        ResponseData responseData = new ResponseData();


        //检验验证码
        ResponseData checkaCode = iSecurityCodeService.checkaCode(map.get("account"), map.get("code"));
        if (checkaCode.getCode()!= StatusEnum.SUCCESS.getCode()){
            throw new GrilException(checkaCode.getMessage());
        }
        //清除验证码信息
        DataMap.tokenMap.remove(map.get("account"));

        UserInfo byAccount = userInfoMapperRead.findByAccount(map.get("account"));
        if (byAccount!=null){
            if (ObjectUtil.isEmpty(byAccount.getOtherAppId())){
                iUserInfoService.deleteByUserInfo(byAccount);

                UserInfo byUserNo = userInfoMapperRead.selectByPrimaryKey(map.get("id"));
                if (StrUtil.isEmpty(byUserNo.getPhoneNo())) {
                    //绑定手机
                    String accountType = map.get("accountType");
                    if ("1".equals(accountType)){
                        byUserNo.setPhoneNo(map.get("account"));
                    }
                    //绑定邮箱
                    if ("2".equals(accountType)){
                        byUserNo.setMailboxNo(map.get("account"));
                    }
                    iUserInfoService.updateUserInfo(byUserNo);
                }
            }else {
                throw new GrilException("手机号码已被其他人绑定");
            }
        }else {
            throw new GrilException("用户信息不存在");
        }
        return responseData;
    }

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData removeBindAccount(Map<String, String> data) {
        if ("1".equals(data.get("accountType"))){
            data.put("fieldName","phone_no");
        }else {
            data.put("fieldName","mailbox_no");
        }
        data.put("fieldVal","");

        int i = userInfoMapperWrite.removeBindAccount(data.get("id"), data.get("fieldName"), data.get("fieldVal"));
        int i1 = userInfoMapperChatMessWrite.removeBindAccount(data.get("id"), data.get("fieldName"), data.get("fieldVal"));
        int i2 = userInfoMapperMarketingWrite.removeBindAccount(data.get("id"), data.get("fieldName"), data.get("fieldVal"));
        if (i>0&&i1>0&&i2>0){
            return BaseResp.getInstance();
        }else {
            throw new GrilException("解绑账号失败");
        }
    }

    @Override
    public ResponseData getUserOtherBindList(String userNo) {
        return iUserOtherLoginService.getUserOtherBindList(userNo);
    }

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData bindOtherLogin(UserOtherLogin userOtherLogin) {
        //判断ID是否已被绑定
        UserOtherLogin byOtherAppIdAndOtherAppType = iUserOtherLoginService.findByOtherAppIdAndOtherAppType(userOtherLogin.getOtherAppId(), userOtherLogin.getOtherAppType());
        if (byOtherAppIdAndOtherAppType!=null){
            throw new GrilException("当前第三方账号已被其它用户绑定");
        }
        ResponseData responseData = iUserOtherLoginService.addUserOtherLogin(userOtherLogin);
        /*if (responseData.getCode()== StatusEnum.SUCCESS.getCode()){
            ResponseData<UserInfo> byUserNo = iUserInfoService.findByUserNo(userOtherLogin.getUserNo());
            UserInfo userInfo = byUserNo.getData();
            userInfo.setOtherAppId(userOtherLogin.getOtherAppId());
            userInfo.setOtherAppName(userOtherLogin.getOtherAppType());
            iUserInfoService.updateByPrimaryKeySelective(userInfo);
        }else {
            throw new GrilException("绑定第三方登录失败!");
        }*/
        return responseData;
    }

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData removeOtherLogin(String id) {
        return iUserOtherLoginService.removeUserOtherBind(id);
    }
}
