package net.sunwukong.www.user.server.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.sdkinfo.www.core.lang.PatternPool;
import com.sdkinfo.www.core.util.ObjectUtil;
import com.sdkinfo.www.core.util.RandomUtil;
import com.sdkinfo.www.core.util.ReUtil;
import com.sdkinfo.www.core.util.StrUtil;
import com.sdkinfo.www.extra.mail.MailUtil;
import com.sdkinfo.www.im.easemob.dao.IMUser;
import com.sdkinfo.www.log.StaticLog;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.crypto.digest.DigestUtil;
import net.sunwukong.www.api.data.DataMap;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.DefualtEnum;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.base.sms.dao.AliSMSDao;
import net.sunwukong.www.base.sms.enums.SMSTemplateEnum;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.bean.*;
import net.sunwukong.www.user.input.RegisterInput;
import net.sunwukong.www.user.server.dao.chatmess.write.UserInfoMapperChatMessWrite;
import net.sunwukong.www.user.server.dao.marketing.write.UserInfoMapperMarketingWrite;
import net.sunwukong.www.user.server.dao.usercenter.read.UserInfoMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserInfoMapperWrite;
import net.sunwukong.www.user.server.service.IUserInfoService;
import net.sunwukong.www.user.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明:
 *
 * @author Mick
 * CreateDate 2018/6/7 11:53
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IUserInfoServiceImpl extends BaseController implements IUserInfoService {
    //第三方登录默认密码
    private static final String defultPass = "123456";

    @Autowired
    UserInfoMapperWrite userInfoMapperWrite;

    @Autowired
    UserInfoMapperChatMessWrite userInfoMapperChatMessWrite;

    @Autowired
    UserInfoMapperMarketingWrite userInfoMapperMarketingWrite;

    @Autowired
    UserInfoMapperRead userInfoMapperRead;

    @Override
    public int deleteByPrimaryKey(Serializable serializable) {
        return userInfoMapperWrite.deleteByPrimaryKey(serializable);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo userInfo) {
        return userInfoMapperWrite.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public int updateByPrimaryKey(UserInfo userInfo) {
        return userInfoMapperWrite.updateByPrimaryKey(userInfo);
    }

    @Override
    public UserInfo selectByPrimaryKey(Serializable serializable) {
        return userInfoMapperWrite.selectByPrimaryKey(serializable);
    }

    @Override
    public int insert(UserInfo userInfo) {
        return userInfoMapperWrite.insert(userInfo);
    }

    @Override
    public int insertSelective(UserInfo userInfo) {
        return userInfoMapperWrite.insertSelective(userInfo);
    }

    @Override
    public List<UserInfo> queryAllPage(int i, int i1) {
        return userInfoMapperWrite.queryAllPage(i, i);
    }

    @Override
    public List<UserInfo> queryAll() {
        return userInfoMapperWrite.queryAll();
    }

    /**
     * 检验用户是否存在
     *
     * @param account 用户账号
     * @return
     */
    @Override
    public boolean checkAccount(String account) {
        UserInfo userInfo = userInfoMapperRead.findByAccount(account);
        if (userInfo != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseData addUserInfo(UserInfo userInfo) {
        //生成密码
        String passwd = RandomUtil.randomString(6);

        userInfo.setId(DataBaseTool.createId());
        userInfo.setUserNo(DataBaseTool.createNo("yh"));
        userInfo.setRegionDate(new Date());
        //累计评价分值
        userInfo.setEvaluateDividing(0);
        //普通用户
        userInfo.setUserType(SysCode.CODE_10020001.getCode());
        //性别
        if (StrUtil.isEmpty(userInfo.getSexNo())) {
            userInfo.setSexNo("10010001");
        }
        //用户昵称
        if (StrUtil.isEmpty(userInfo.getNikeName())){
            userInfo.setNikeName(DataBaseTool.createNikeName());
        }
        //用户头像
        if (StrUtil.isEmpty(userInfo.getUserHead())){
            userInfo.setUserHead(DefualtEnum.CODE_10010001.getUrl());
        }
        //用户密码
        if (StrUtil.isEmpty(userInfo.getPassWord())){
            userInfo.setPassWord(passwd);
        }else {
            passwd = userInfo.getPassWord();
        }
        userInfo.setPassWord(DigestUtil.md5Hex(userInfo.getPassWord()));
        //用户状态
        userInfo.setUserState(SysCode.CODE_10030001.getCode());
        //用户等级
        userInfo.setUserGrade("10040001");
        //1.保存用户信息
        int i = userInfoMapperWrite.insert(userInfo);
        int i1 = userInfoMapperMarketingWrite.insert(userInfo);
        int i2 = userInfoMapperChatMessWrite.insert(userInfo);
        if (i<=0){
            throw new GrilException("添加用户信息失败");
        }
        if (i1<=0){
            throw new GrilException("添加用户信息失败");
        }
        if (i2<=0){
            throw new GrilException("添加用户信息失败");
        }

        //2.注册环信
        boolean createUser = IMUser.createUser(userInfo.getUserNo(), userInfo.getPassWord());
        if (!createUser){
            throw new GrilException("注册环信失败");
        }

        //3.初始化用户账户信息
        UserAccount account = new UserAccount();
        account.setUserNo(userInfo.getUserNo());
        iUserAccountService.addUserAccount(account);

        //4.赠送蟠桃
        iPlatformAccountService.sendPeento(userInfo.getUserNo(), SysCode.CODE_10260001,SysCode.CODE_10050001);

        //5.发送注册成功通知
        if (StrUtil.isEmpty(userInfo.getPhoneNo()) && StrUtil.isEmpty(userInfo.getMailboxNo())){
            StaticLog.error("微信注册");
        }else if (StrUtil.isNotEmpty(userInfo.getPhoneNo()) && StrUtil.isEmpty(userInfo.getMailboxNo())){
            StaticLog.error("电话号码注册");
            sendSuccessfulNotice(userInfo.getPhoneNo(),"1",passwd);
        }else if (StrUtil.isEmpty(userInfo.getPhoneNo()) && StrUtil.isNotEmpty(userInfo.getMailboxNo())){
            StaticLog.error("邮箱注册");
            sendSuccessfulNotice(userInfo.getMailboxNo(),"2",passwd);
        }

        //登陆用户
        UserInfo byIdInfo = userInfoMapperRead.findByIdInfo(userInfo.getId());

        return BaseResp.getInstance(byIdInfo);
    }

    /**
     * 校验、封装注册信息
     *
     * @param data 用户账号    用户类型    验证码 用户昵称    用户密码    返回响应对象
     * @return
     */
    @Override
    //@Transactional(rollbackFor = {GrilException.class})
    public ResponseData register(RegisterInput data) {
        //判断用户是否存在
        if (checkAccount(data.getAccount())){
            throw new GrilException("用户已存在");
        }
        //验证验证码
        ResponseData responseData = iSecurityCodeService.checkaCode(data.getAccount(), data.getCode());
        if (responseData.getCode() != StatusEnum.SUCCESS.getCode()) {
            throw new GrilException(responseData.getMessage());
        }

        //清除验证码信息
        DataMap.tokenMap.remove(data.getAccount());

        //封装用户信息
        UserInfo userInfo = new UserInfo();
        if (ReUtil.contains(PatternPool.EMAIL,data.getAccount())){
            //邮箱
            userInfo.setMailboxNo(data.getAccount());
        }else if (ReUtil.contains(PatternPool.MOBILE,data.getAccount())){
            //电话号码
            userInfo.setPhoneNo(data.getAccount());
        }else {
            throw new GrilException("您输入的账号既不是电话号码也不是邮箱");
        }
        userInfo.setNikeName(data.getNikeName());
        userInfo.setPassWord(data.getPassword());

        //执行注册操作
        ResponseData<UserInfo> addUserInfo = addUserInfo(userInfo);
        UserInfo addUserInfoData = addUserInfo.getData();

        //保存设备信息
        UserDevice userDevice = new UserDevice();
        userDevice.setPhoneType(data.getPhoneType());
        userDevice.setDeviceNo(data.getDeviceNo());
        userDevice.setUserNo(addUserInfoData.getUserNo());
        iUserDeviceService.saveDevice(userDevice);

        return BaseResp.getInstance(addUserInfoData);
    }

    /**
     * 发送注册成功通知
     * @param account       登陆名
     * @param accountType   登陆类型(1:电话号码 2：邮箱)
     * @param passwd        用户密码
     */
    @Override
    public void sendSuccessfulNotice(String account,String accountType,String passwd){
        StaticLog.error("号码：{}，密码：{}",account,passwd);
        try {
            //发送注册成功通知
            if ("1".equals(accountType)) {
                //发送短信通知
                Map<String,String> content = new HashMap<>();
                content.put("username",account);
                content.put("passwd",passwd);
                AliSMSDao.sendSms(account,content, SMSTemplateEnum.SMS_142951710);
            } else {
                //发送邮箱通知
                MailUtil.send(account,"GoKong-注册成功通知","恭喜您注册成功，您的用户名为"+account+",登录密码为"+passwd+",请注意妥善保管。",false);
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     *
     * @param account   用户账号
     * @param password  用户密码
     * @param phoneType 设备类型
     * @param deviceNo  设备编号
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData login(String account, String password, String phoneType, String deviceNo) {
        //根据账号密码查询用户信息
        UserInfo userInfo = userInfoMapperRead.findByAccountAndPassword(account, DigestUtil.md5Hex(password));
        if (userInfo == null) {
            throw new GrilException("账号或密码错误");
        }
        //查询设备信息
        UserDevice byUserNo = iUserDeviceService.findByUserNo(userInfo.getUserNo());
        //如果数据库存在设备信息 就更新数据 否则就新增
        if (byUserNo != null) {
            byUserNo.setPhoneType(phoneType);
            byUserNo.setDeviceNo(deviceNo);
            iUserDeviceService.updateDevice(byUserNo);
        } else {
            UserDevice userDevice = new UserDevice();
            userDevice.setPhoneType(phoneType);
            userDevice.setUserNo(userInfo.getUserNo());
            userDevice.setDeviceNo(deviceNo);
            iUserDeviceService.saveDevice(userDevice);
        }
        return BaseResp.getInstance(getUserInfo(userInfo.getId()));
    }

    /**
     * 第三方登录
     *
     * @param map 登录对象
     * @return
     */
    @Override
    public ResponseData otherLogin(Map<String, String> map) {
        //封装用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setSexNo(map.get("sexNo"));
        userInfo.setNikeName(map.get("nikeName"));
        userInfo.setUserHead(map.get("userHead"));
        userInfo.setUserName(map.get("nikeName"));
        userInfo.setOtherAppId(map.get("otherAppId"));
        userInfo.setOtherAppName(map.get("otherAppName"));
        //查询第三方账号是否有绑定的用户
        UserOtherLogin byOtherAppIdAndOtherAppType = iUserOtherLoginService.findByOtherAppIdAndOtherAppType(userInfo.getOtherAppId(), ApiTool.getOtherLoginCode(userInfo.getOtherAppName()));
        if (byOtherAppIdAndOtherAppType != null) {
            UserInfo byUserNo = findByUserNo(byOtherAppIdAndOtherAppType.getUserNo());
            UserInfo byIdInfo = userInfoMapperRead.findByIdInfo(byUserNo.getId());
            return BaseResp.getInstance(byIdInfo);
        } else {
            //保存用户信息
            userInfo.setPassWord(defultPass);
            ResponseData<UserInfo> responseData = addUserInfo(userInfo);
            userInfo = responseData.getData();

            //如果是第三方登录默认绑定第三方
            UserOtherLogin userOtherLogin = new UserOtherLogin();
            userOtherLogin.setOtherAppId(userInfo.getOtherAppId());
            userOtherLogin.setOtherAppType(ApiTool.getOtherLoginCode(userInfo.getOtherAppName()));
            userOtherLogin.setUserNo(userInfo.getUserNo());
            iUserOtherLoginService.addUserOtherLogin(userOtherLogin);

            //封装设备信息
            UserDevice userDevice = new UserDevice();
            userDevice.setPhoneType(map.get("phoneType"));
            userDevice.setDeviceNo(map.get("deviceNo"));
            userDevice.setUserNo(userInfo.getUserNo());
            iUserDeviceService.saveDevice(userDevice);

            return BaseResp.getInstance(userInfo);
        }
    }

    /**
     * 修改用户资料
     *
     * @param userInfo 待更新对象
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData updateUserInfo(UserInfo userInfo) {
        //更新用户数据
        int i = userInfoMapperWrite.updateByPrimaryKeySelective(userInfo);
        if (i <= 0) {
            throw new GrilException("更新用户信息失败");
        }
        //更新运营库、消息库数据
        //插入chatmess库
        userInfoMapperChatMessWrite.updateByPrimaryKeySelective(userInfo);
        //插入marketing库
        userInfoMapperMarketingWrite.updateByPrimaryKeySelective(userInfo);
        IMUser.updateUserNickName(userInfo.getUserNo(),userInfo.getNikeName());
        return BaseResp.getInstance();
    }

    /**
     * 修改用户头像
     *
     * @param id       用户ID
     * @param userHead 用户头像
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData updateUserHead(String id, String userHead) {
        ResponseData responseData = new ResponseData();
        int i = userInfoMapperWrite.updateUserHead(id, userHead);
        if (i <= 0) {
            responseData.setStatus(StatusEnum.FAIL);
            return responseData;
        }
        //更新运营库、消息库数据
        //插入chatmess库
        userInfoMapperChatMessWrite.updateUserHead(id, userHead);
        //插入marketing库
        userInfoMapperMarketingWrite.updateUserHead(id, userHead);
        return responseData;
    }

    /**
     * 重置用户密码
     *
     * @param account  用户账号
     * @param password 用户密码
     * @param code     验证码
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData resetPassword(String account, String password, String code) {
        //校验验证码
        ResponseData responseData = iSecurityCodeService.checkaCode(account, code);
        if (responseData.getCode() != StatusEnum.SUCCESS.getCode()) {
            throw new GrilException("校验验证码失败");
        }
        //更新用户密码
        int i = userInfoMapperWrite.updaetByAccount(account, DigestUtil.md5Hex(password));
        if (i <= 0) {
            throw new GrilException("未查询到绑定信息");
        }
        //更新运营库、消息库数据
        //插入chatmess库
        userInfoMapperChatMessWrite.updaetByAccount(account, DigestUtil.md5Hex(password));
        //插入marketing库
        userInfoMapperMarketingWrite.updaetByAccount(account, DigestUtil.md5Hex(password));

        UserInfo byAccount = userInfoMapperRead.findByAccount(account);
        //修改环信密码
        IMUser.resetUserPwd(byAccount.getUserNo(),DigestUtil.md5Hex(password));
        return responseData;
    }

    /**
     * 获取用户信息（多表联查后的数据）
     *
     * @param id 用户ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public UserInfo getUserInfo(String id) {
        UserInfo byIdInfo = userInfoMapperRead.findByIdInfo(id);
        if (byIdInfo == null) {
            throw new GrilException(StatusEnum.USER_NOT_EXIST.getMsg());
        }
        return byIdInfo;
    }

    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData updateUserEvaluate(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        //获取用户信息
        UserInfo userInfo = userInfoMapperRead.findByUserNo(data.get("userNo"));
        userInfo.setEvaluateDividing(userInfo.getEvaluateDividing() + Integer.parseInt(data.get("evaluateNo")));
        //根据累计评分获取等级信息
        ResponseData<SysUserGrade> sysUserGradeWhereEvaluate = iSysUserGradeService.getSysUserGradeWhereEvaluate(userInfo.getEvaluateDividing());
        userInfo.setUserGrade(sysUserGradeWhereEvaluate.getData().getUserGrade());
        userInfo.setUserGradeChangeDate(new Date());
        int i = userInfoMapperWrite.updateByPrimaryKey(userInfo);
        if (i <= 0) {
            throw new GrilException("修改用户等级失败");
        }
        return responseData;
    }

    @Override
    public UserInfo findByUserNo(String userNo) {
        UserInfo byUserNo = userInfoMapperRead.findByUserNo(userNo);
        return byUserNo;
    }

    @Override
    public ResponseData findByIdUserInfoLogin(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        UserInfo userInfo = getUserInfo(data.get("id"));
        if (userInfo == null) {
            throw new GrilException("未查询的到用户ID：" + data.get("id") + "的信息");
        }
        Map<String, Object> map = null;
        try {
            map = ObjectUtil.objectToMapObj(userInfo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        map.put("otherLogin", iUserOtherLoginService.getUserOtherBindList(userInfo.getUserNo()).getData());
        responseData.setData(map);
        return responseData;
    }

    // 统计用户的在线数据
    public ResponseData addUserState(Map<String, String> userdata) {
        ResponseData responseData = new ResponseData();
        String userNo = userdata.get("userNo");
        String date = DataBaseTool.createDate();
        String log = userdata.get("logCode");
        String lat = userdata.get("latCode");
        int up = userInfoMapperWrite.addUserStates(userNo, date, log, lat);
        if (up > 0) {
            responseData.setCode(200);
            return responseData;
        }
        responseData.setCode(201);
        return responseData;
    }

    @Override
    public ResponseData getVolume(String account, String accountType, String code) {
        //判断用户是否存在
        UserInfo byAccount = userInfoMapperRead.findByAccount(account);
        if (byAccount!=null){
            UserInfo byIdInfo = userInfoMapperRead.findByIdInfo(byAccount.getId());
            return BaseResp.getInstance(byIdInfo);
        }else {
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
            userInfo.setNikeName(DataBaseTool.createNikeName());

            //执行注册操作
            ResponseData<UserInfo> addUserInfo = addUserInfo(userInfo);
            UserInfo addUserInfoData = addUserInfo.getData();
            return BaseResp.getInstance(addUserInfoData);
        }
    }

    @Override
    public ResponseData addUserPhoneNo(String userNo, String phoneNo, String code) {
        //验证验证码
        ResponseData responseData = iSecurityCodeService.checkaCode(phoneNo, code);
        if (responseData.getCode() != StatusEnum.SUCCESS.getCode()) {
            throw new GrilException(responseData.getMessage());
        }
        //清除验证码信息
        DataMap.tokenMap.remove(phoneNo);
        UserInfo byAccount = userInfoMapperRead.findByAccount(phoneNo);
        if (byAccount!=null){
            if (ObjectUtil.isEmpty(byAccount.getOtherAppId())){
                deleteByUserInfo(byAccount);
                UserInfo byUserNo = userInfoMapperRead.findByUserNo(userNo);
                if (StrUtil.isEmpty(byUserNo.getPhoneNo())) {
                    byUserNo.setPhoneNo(phoneNo);
                    updateUserInfo(byUserNo);
                }
            }else {
                throw new GrilException("手机号码已被其他人绑定");
            }
        }else {
            throw new GrilException("用户信息不存在");
        }
        return BaseResp.getInstance();
    }

    @Override
    public boolean deleteByUserInfo(UserInfo userInfo) {
        userInfoMapperWrite.deleteByPrimaryKey(userInfo.getId());
        userInfoMapperChatMessWrite.deleteByPrimaryKey(userInfo.getId());
        userInfoMapperMarketingWrite.deleteByPrimaryKey(userInfo.getId());
        return IMUser.delUser(userInfo.getUserNo());
    }
}
