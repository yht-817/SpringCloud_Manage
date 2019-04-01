package net.sunwukong.www.user.server.service;


import net.sunwukong.www.api.base.BaseService;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserInfo;
import net.sunwukong.www.user.input.RegisterInput;

import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author mick
 * @since 2018-06-06
 */
public interface IUserInfoService extends BaseService<UserInfo> {
    /**
     * 验证用户是否存在
     * @param account   用户账号
     * @return          返回响应对象
     */
    boolean checkAccount(String account);

    /**
     * 添加用户信息
     * @param userInfo
     * @return
     */
    ResponseData addUserInfo(UserInfo userInfo);

    /**
     * 注册用户（校验延签数据）
     * @param registerInput       用户账号    用户类型    验证码 用户昵称    用户密码    返回响应对象
     * @return
     */
    ResponseData register(RegisterInput registerInput);

    /**
     * 用户登录
     * @param account   用户账号
     * @param password  用户密码
     * @param phoneType 设备类型
     * @param deviceNo  设备编号
     * @return          返回响应对象
     */
    ResponseData login(String account, String password, String phoneType, String deviceNo);

    /**
     * 第三方登录
     * @param map       登录对象
     * @return          返回响应对象
     */
    ResponseData otherLogin(Map<String,String> map);

    /**
     * 更新用户资料
     * @param userInfo  待更新对象
     * @return          返回响应对象
     */
    ResponseData updateUserInfo(UserInfo userInfo);

    /**
     * 更新用户头像
     * @param id        用户ID
     * @param userHead  用户头像
     * @return          返回响应对象
     */
    ResponseData updateUserHead(String id,String userHead);


    /**
     * 重置密码
     * @param account   用户账号
     * @param password  用户密码
     * @param code      验证码
     * @return
     */
    ResponseData resetPassword(String account, String password, String code);

    /**
     * 获取用户信息
     * @param id    用户ID
     * @return      返回响应数据
     */
    UserInfo getUserInfo(String id);

    /**
     * 修改用户评分值
     * @param data
     * @return
     */
    ResponseData updateUserEvaluate(Map<String,String> data);

    /**
     * 根据用户编码查询用户信息
     * @param userNo
     * @return
     */
    UserInfo findByUserNo(String userNo);

    /**
     * 通过用户ID登录
     * @param data
     * @return
     */
    ResponseData findByIdUserInfoLogin(Map<String, String> data);

    // 存储用户在线状态统计
    ResponseData addUserState(Map<String, String> userdata);

    /**
     * 发送注册成功通知
     * @param account       登陆名
     * @param accountType   登陆类型(1:电话号码 2：邮箱)
     * @param passwd        用户密码
     */
    void sendSuccessfulNotice(String account,String accountType,String passwd);

    /**
     * 分享注册购买卷
     * @param account       用户账户信息
     * @param accountType   用户账户类型
     * @param code          验证码
     * @return
     */
    ResponseData getVolume(String account, String accountType, String code);

    /**
     * 精选购买添加手机号
     * @param userNo    用户编码
     * @param phoneNo   电话号码
     * @param code      验证码
     * @return
     */
    ResponseData addUserPhoneNo(String userNo, String phoneNo, String code);

    /**
     * 根据用户对象删除用户信息
     * @param userInfo
     * @return
     */
    boolean deleteByUserInfo(UserInfo userInfo);
}
