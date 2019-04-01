package net.sunwukong.www.user.server.service.impl;

import com.sdkinfo.www.core.util.TimeUtil;
import com.sdkinfo.www.core.util.ToolUtil;
import com.sdkinfo.www.extra.mail.MailUtil;
import com.sdkinfo.www.log.StaticLog;
import net.sunwukong.www.api.data.DataMap;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.captcha.CaptchaUtil;
import net.sunwukong.www.base.captcha.CircleCaptcha;
import net.sunwukong.www.base.sms.dao.AliSMSDao;
import net.sunwukong.www.base.sms.enums.SMSTemplateEnum;
import net.sunwukong.www.user.server.service.ISecurityCodeService;
import net.sunwukong.www.user.server.web.BaseController;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明:验证码服务接口实现类
 *
 * @author Mick
 * CreateDate 2018/6/10/010 9:20
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class ISecurityCodeServiceImpl extends BaseController implements ISecurityCodeService {

    /**
     * 获取验证码
     * @param account       用户账号
     * @param accountType   账户类型 1：电话 2：邮箱
     * @return
     */
    @Override
    public ResponseData getCode(String account, String accountType) {
        ResponseData responseData = new ResponseData();
        switch (accountType){
            case "1":
                if (sendSms(account)){
                    responseData.setMessage("发送验证码成功");
                }else {
                    responseData.setStatus(StatusEnum.CODE_SEND_FAIL);
                }
                break;
            case "2":
                if (sendMail(account)){
                    responseData.setMessage("发送验证码成功");
                }else {
                    responseData.setStatus(StatusEnum.CODE_SEND_FAIL);
                }
                break;
                default:
                    responseData.setStatus(StatusEnum.PARAM_ERROR);
                    break;
        }
        return responseData;
    }

    /**
     * 校验验证码
     * @param account       用户账号
     * @param code          验证代码
     * @return
     */
    @Override
    public ResponseData checkaCode(String account, String code) {
        ResponseData responseData = new ResponseData();
        //验证内存中的验证码数量
        if (DataMap.securityCodeMap.size()<=0){
            responseData.setStatus(StatusEnum.CODE_TIMEOUT);
            return responseData;
        }
        //获取内存中的验证码,并校验
        Map<String, String> map = DataMap.securityCodeMap.get(account);
        StaticLog.info("code"+code+"map:"+map);
        if (map == null){
            throw new GrilException(StatusEnum.CODE_ERROR.getMsg());
        }else if (!code.equals(map.get("code"))){
            throw new GrilException(StatusEnum.CODE_ERROR.getMsg());
        }
        //验证是否超时
        long startTime = Long.parseLong(map.get("time"));
        long endTime = TimeUtil.getCurrentTimestamp();
        long time = (endTime-startTime)/60;
        if (time>=5){
            responseData.setStatus(StatusEnum.CODE_TIMEOUT);
            return responseData;
        }
        responseData.setMessage("验证成功");
        //清除内存中的验证码信息
        DataMap.securityCodeMap.remove(account);
        return responseData;
    }

    /**
     * 发送短信
     *
     * @param account 电话号码
     * @return true 发送成功 false 发送失败
     */
    private boolean sendSms(String account) {
        //封装验证码信息
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("code", String.valueOf(ToolUtil.buildRandom(4)));
        map.put("time", String.valueOf(TimeUtil.getCurrentTimestamp()));
        //保存验证码信息
        DataMap.securityCodeMap.put(account, map);
        try {
            Map<String,String> content = new HashMap<>();
            content.put("code",map.get("code"));
            //调用发送验证码接口
            String code = AliSMSDao.sendSms(account,content, SMSTemplateEnum.SMS_136387226).getCode();
            if (!"OK".equals(code)) {
                return false;
            }
            StaticLog.info("手机验证码发送成功：{}", map.get("code"));
            //SpeechUtil.play(map.get("code"));
        } catch (Exception e) {
            throw new GrilException(e.getMessage());
        }
        return true;
    }

    /**
     * 发送邮箱
     * @param account   邮箱地址
     * @return  true 发送成功 false 发送失败
     */
    private boolean sendMail(String account) {
        //封装验证码信息
        Map<String,String> map = new HashMap<String, String>(16);
        map.put("code", String.valueOf(ToolUtil.buildRandom(4)));
        map.put("time", String.valueOf(TimeUtil.getCurrentTimestamp()));
        //保存验证码信息
        DataMap.securityCodeMap.put(account,map);
        //调用发送验证码接口
        MailUtil.send(account,"孙悟空华人网-获取验证码","尊敬的用户：<br/>您的验证码："+map.get("code")+"，5分钟内有效，请勿泄漏。",true);
        StaticLog.info("邮箱验证码发送成功：{}",map.get("code"));
        return true;
    }


    /**
     * 获取图片验证码
     * @return
     */
    @Override
    public ResponseData getImgCode() {
        ResponseData responseData = new ResponseData();
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100);
        Map<String,String> map = new HashMap<String, String>(16);
        map.put("code", captcha.getCode());
        map.put("time", String.valueOf(TimeUtil.getCurrentTimestamp()));
        DataMap.securityCodeMap.put(map.get("code"),map);
        StaticLog.info("图片验证码发送成功：{}-{}",map.get("code"),map);
        responseData.setData(captcha.getImageBase64());
        return responseData;
    }

    /**
     * 校验图片验证码
     * @param account       用户账号
     * @param accountType   账户类型 1手机 2邮箱
     * @param code          验证码
     * @return
     */
    @Override
    public ResponseData checkaImgCode(String account, String accountType, String code) {
        //检查用户是否存在
        boolean isok = iUserInfoService.checkAccount(account);
        if (!isok) {
            throw new GrilException(StatusEnum.USER_NOT_EXIST.getMsg());
        }
        //验证验证码
        ResponseData responseData = checkaCode(code, code);
        if (responseData.getCode() != StatusEnum.SUCCESS.getCode()) {
            responseData.setStatus(StatusEnum.CODE_ERROR);
            return responseData;
        }
        responseData = getCode(account, accountType);
        return responseData;
    }
}
