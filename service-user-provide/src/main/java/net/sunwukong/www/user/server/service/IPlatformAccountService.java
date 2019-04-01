package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.marketing.bean.PlatformAccount;

/**
 * 说明:平台账户金额服务接口
 *
 * @author Mick
 * CreateDate 2018/7/4/004 0:51
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IPlatformAccountService {

    /**
     * 更新平台金额信息
     * @param platformAccount
     * @return
     */
    ResponseData updatePlatformAccount(PlatformAccount platformAccount);

    /**
     * 赠送蟠桃
     * @param userNo        用户编码
     * @param sendType      赠送类型
     * @param changeMode    资金变动方式
     * @return
     */
    ResponseData sendPeento(String userNo, SysCode sendType, SysCode changeMode);
}
