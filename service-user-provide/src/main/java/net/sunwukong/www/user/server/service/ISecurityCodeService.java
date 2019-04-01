package net.sunwukong.www.user.server.service;


import net.sunwukong.www.api.entity.ResponseData;

/**
 * 说明:验证码服务接口
 *
 * @author Mick
 * CreateDate 2018/6/10/010 9:18
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface ISecurityCodeService {

    /**
     * 获取验证码
     * @param account       用户账号
     * @param accountType   账户类型 1：电话 2：邮箱
     * @return              返回响应对象
     */
    ResponseData getCode(String account, String accountType);


    /**
     * 校验短信验证码
     * @param account       用户账号
     * @param code          验证代码
     * @return              返回响应对象
     */
    ResponseData checkaCode(String account, String code);

    /**
     * 获取图片验证码
     * @return
     */
    ResponseData getImgCode();

    /**
     * 校验图片验证码
     * @param account       用户账号
     * @param accountType   账户类型 1手机 2邮箱
     * @param code          验证码
     * @return              返回响应对象
     */
    ResponseData checkaImgCode(String account, String accountType, String code);
}
