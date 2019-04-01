package net.sunwukong.www.user.server.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.sdkinfo.www.core.lang.PatternPool;
import com.sdkinfo.www.core.util.ReUtil;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.sms.dao.AliSMSDao;
import net.sunwukong.www.base.sms.enums.SMSTemplateEnum;
import net.sunwukong.www.user.bean.UserInfo;
import net.sunwukong.www.user.server.service.IInviteFriendsService;
import net.sunwukong.www.user.server.web.BaseController;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明:邀请好友服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/23 17:19
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IInviteFriendsServiceImpl extends BaseController implements IInviteFriendsService {

    /**
     * 短信邀请好友
     * @param map
     * @return
     */
    @Override
    public ResponseData smsInvite(Map<String, String> map) {
        ResponseData responseData = new ResponseData();
        try {
            Map<String,String> content = new HashMap<>();
            content.put("code",map.get("code"));
            AliSMSDao.sendSms(map.get("userPhone"),content, SMSTemplateEnum.SMS_141200869);
        } catch (ClientException e) {
            throw new GrilException(e.getErrMsg());
        }
        return responseData;
    }

    /**
     * 接受邀请
     * @param userNo        邀请者的用户编码
     * @param account       接受邀请者的账号
     * @param accountType   账号类型
     * @return
     */
    @Override
    public ResponseData acceptInvitation(String userNo, String account, String accountType) {
        //判断用户是否存在
        boolean checkAccount = iUserInfoService.checkAccount(account);
        if (checkAccount){
            throw new GrilException("当前账户已注册");
        }
        //封装用户信息
        //封装用户信息
        UserInfo userInfo = new UserInfo();
        if (ReUtil.contains(PatternPool.EMAIL,account)){
            //邮箱
            userInfo.setMailboxNo(account);
        }else if (ReUtil.contains(PatternPool.MOBILE,account)){
            //电话号码
            userInfo.setPhoneNo(account);
        }else {
            throw new GrilException("您输入的账号既不是电话号码也不是邮箱");
        }
        //注册用户
        ResponseData responseData = iUserInfoService.addUserInfo(userInfo);

        //返利给邀请者蟠桃
        iPlatformAccountService.sendPeento(userNo, SysCode.CODE_10260002,SysCode.CODE_10050003);
        return responseData;
    }
}
