package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.*;
import net.sunwukong.www.marketing.server.dao.*;
import net.sunwukong.www.marketing.server.service.IChoiceShareDetailService;
import net.sunwukong.www.marketing.server.service.IUserAccountService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IChoiceShareDetailServiceImpl
 * @Author: kangdong
 * @Date: 2018/7/17 下午4:22
 * @Description: 分享购买信息实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class IChoiceShareDetailServiceImpl implements IChoiceShareDetailService {

    private static final Logger log = LoggerFactory.getLogger(IChoiceShareDetailServiceImpl.class);

    @Autowired
    ChoiceShareInfoMapper choiceShareInfoMapper;

    @Autowired
    ChoiceUserMapper choiceUserMapper;

    @Autowired
    ChoiceInfoMapper choiceInfoMapper;

    @Autowired
    ChoiceUserPayMapper choiceUserPayMapper;

    @Autowired
    ChoiceUserPayDetailMapper choiceUserPayDetailMapper;

    @Autowired
    IUserAccountService iUserAccountService;


    /**
     *  分享购买(用户购买券，现金 +  蟠桃支付 )
     *  先调用现金支付接口，成功后调研此接口
     *  1、变更个人账户信息(蟠桃数量减少)
     *  2、变更商户账户(蟠桃数量加)
     *  3、新增现金支付表
     *  4、新增蟠桃支付表
     *  5、变更精选表（已购张数增加pay_num）
     *  6、新增精选券表
     *  7、分享反利票券
     * @param data
     * @return
     */
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addShareBuy(Map<String,String> data) {
        ResponseData responseData = new ResponseData();
        try {
            //通过分享编码查询分享的用户和分享的资源信息
            ChoiceShareInfo shareInfo = choiceShareInfoMapper.getByShareNo(data.get("shareNo"));
            if(null == shareInfo) {
                log.error("没有找到分享信息");
                responseData.setCode(StatusEnum.FAIL.getCode());
                responseData.setMessage(StatusEnum.FAIL.getMsg());
                responseData.setData(StatusEnum.FAIL);
                return responseData;
            }
            ChoiceInfo choiceInfo = choiceInfoMapper.getDetailByResourceNo(shareInfo.getResourceNo());
            if(null == choiceInfo) {
                responseData.setCode(StatusEnum.NOT_FOUND.getCode());
                responseData.setMessage(StatusEnum.NOT_FOUND.getMsg());
                responseData.setData(StatusEnum.NOT_FOUND);
                return responseData;
            }

            //1变更个人账户蟠桃(个人账户支出)
            Map<String, String> map = new HashMap<>();
            map.put("type","1");
            map.put("changeNo",DataBaseTool.createNo("dh"));
            map.put("changeMode",SysCode.CODE_10050010.getCode());
            map.put("changeRemark",SysCode.CODE_10050010.getMsg());
            map.put("userNo", data.get("userNo"));//分享后购买的用户
            map.put("amount", "-"+data.get("peach"));//支付蟠桃数
            iUserAccountService.updateUserAccount(map);

            //2变更商家账户蟠桃(商户账户收入)
            map.put("userNo", choiceInfo.getUserNo());//商家用户
            map.put("amount", data.get("peach"));//支付蟠桃数
            iUserAccountService.updateUserAccount(map);

            //3新增蟠桃支付表记录
            ChoiceUserPay userPay = new ChoiceUserPay();
            userPay.setId(DataBaseTool.createId());
            userPay.setPayNo(data.get("getNo"));
            userPay.setUserNo(data.get("userNo"));//支付用户
            userPay.setResourceNo(shareInfo.getResourceNo());
            userPay.setChoiceType(choiceInfo.getChoiceType());
            userPay.setPayAmount(new BigDecimal(data.get("peach")));//蟠桃支付
            userPay.setPayDate(new Date());
            userPay.setPayState(SysCode.CODE_10380002.getCode());
            int i = choiceUserPayMapper.insert(userPay);
            if(i<0) {
                log.error("保存蟠桃支付记录失败!");
                responseData.setCode(StatusEnum.FAIL.getCode());
                responseData.setMessage(StatusEnum.FAIL.getMsg());
                responseData.setData(StatusEnum.FAIL);
                return responseData;
            }

            //4新增现金支付表记录
            ChoiceUserPayDetail payDetail = new ChoiceUserPayDetail();
            payDetail.setId(DataBaseTool.createId());
            payDetail.setPayNo(data.get("getNo"));
            payDetail.setUserNo(data.get("userNo"));//支付用户
            payDetail.setPayModeNo(data.get("payModeNo"));
            payDetail.setPayAmount(new BigDecimal(data.get("amount")));//网络支付
            int j = choiceUserPayDetailMapper.insert(payDetail);
            if(j<0) {
                log.error("保存现金支付记录失败!");
                responseData.setCode(StatusEnum.FAIL.getCode());
                responseData.setMessage(StatusEnum.FAIL.getMsg());
                responseData.setData(StatusEnum.FAIL);
                return responseData;
            }

            //5已购数量增加
            choiceInfo.setPayNum(choiceInfo.getPayNum()+1);
            choiceInfoMapper.updateByPrimaryKeySelective(choiceInfo);

            //6新增优惠券张数
            ChoiceUser choiceUser = new ChoiceUser();
            choiceUser.setId(DataBaseTool.createId());
            choiceUser.setUserNo(data.get("userNo"));//购买用户
            choiceUser.setResourceNo(shareInfo.getResourceNo());
            choiceUser.setGetNo(data.get("getNo"));
            choiceUser.setCouponNo(data.get("couponNo"));//DataBaseTool.createNum(18)
            choiceUser.setGetDate(new Date());
            choiceUser.setUseState(SysCode.CODE_10370001.getCode());
            choiceUser.setChoiceType(choiceInfo.getChoiceType());
            choiceUserMapper.insertSelective(choiceUser);

            //返利给分享用户（反30元的票券）
            ChoiceUser shareUser = new ChoiceUser();
            shareUser.setId(DataBaseTool.createId());
            shareUser.setUserNo(shareInfo.getUserNo());//分享用户
            shareUser.setResourceNo(shareInfo.getResourceNo());
            //shareUser.setGetNo();
            shareUser.setCouponNo(DataBaseTool.createNum(18));
            shareUser.setChoiceType(choiceInfo.getChoiceType());
            shareUser.setUseState(SysCode.CODE_10370001.getCode());
            shareUser.setGetDate(new Date());

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(choiceUserMapper.insertSelective(shareUser));
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }
}
