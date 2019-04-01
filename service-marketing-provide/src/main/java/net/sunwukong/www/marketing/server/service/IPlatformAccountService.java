package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.PlatformAccount;

import java.util.Map;

/**
 * Created by tungkang on 2018/6/20.
 */
public interface IPlatformAccountService {

    /**
     * 获取平台账户金额
     * @return
     */
    ResponseData getPlatformAccount();


    /**
     * 更新平台账户表
     * @param platformAccount
     * @return
     */
    ResponseData updatePlatformAccount(PlatformAccount platformAccount);


    /**
     * 平台入账(+)
     * 变更平台账户及账户日志纪录
     * @param map
     * @return
     */
    ResponseData updatePlatformAccountIncome(Map<String,String> map);


    /**
     * 平台账户支出(-)
     * 变更平台账户及账户日志纪录
     * @param map
     * @return
     */
    ResponseData updatePlatformAccountPay(Map<String,String> map);
}
