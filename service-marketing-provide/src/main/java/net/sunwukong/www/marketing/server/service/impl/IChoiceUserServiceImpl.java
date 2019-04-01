package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.*;
import net.sunwukong.www.marketing.server.dao.*;
import net.sunwukong.www.marketing.server.service.IChoiceUserService;
import net.sunwukong.www.marketing.server.service.IPlatformAccountLogService;
import net.sunwukong.www.marketing.server.service.IUserAccountService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.google.zxing.client.j2se.MatrixToImageWriter.toBufferedImage;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IChoiceUserServiceImpl
 * @Author: kangdong
 * @Date: 2018/7/13 下午2:32
 * @Description: 用户精选券信息实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */
@Service
public class IChoiceUserServiceImpl implements IChoiceUserService {

    private static final Logger log = LoggerFactory.getLogger(IChoiceUserServiceImpl.class);

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

    @Autowired
    public IPlatformAccountLogService iPlatformAccountLogService;

    @Autowired
    UserInfoMapper userInfoMapper;


    /**
     * 添加用户精选券信息(用户购买券，现金 +  蟠桃支付 )
     * 先调用现金支付接口，成功后调研此接口
     * 1、变更个人账户信息(蟠桃数量减少)
     * 2、变更商户账户(蟠桃数量加)
     * 3、新增现金支付表
     * 4、新增蟠桃支付表
     * 5、变更精选表（已购张数增加pay_num）
     * 6、新增精选券表
     */
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addChoiceUser(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        try {
            ChoiceInfo choiceInfo = choiceInfoMapper.getDetailByResourceNo(data.get("resourceNo"));
            if (null == choiceInfo) {
                responseData.setCode(StatusEnum.NOT_FOUND.getCode());
                responseData.setMessage(StatusEnum.NOT_FOUND.getMsg());
                responseData.setData(StatusEnum.NOT_FOUND);
                return responseData;
            }
            //1变更个人账户蟠桃(个人账户支出)
            Map<String, String> map = new HashMap<>();
            map.put("type", "1");
            map.put("changeNo", DataBaseTool.createNo("dh"));
            map.put("changeMode", SysCode.CODE_10050010.getCode());
            map.put("changeRemark", SysCode.CODE_10050010.getMsg());
            map.put("userNo", data.get("userNo"));
            map.put("amount", "-" + data.get("peach"));//支付蟠桃数
            iUserAccountService.updateUserAccount(map);
            //2变更商家账户蟠桃(商户账户收入)
            map.put("userNo", choiceInfo.getUserNo());
            map.put("amount", data.get("peach"));//支付蟠桃数
            iUserAccountService.updateUserAccount(map);
            //3新增蟠桃支付表记录
            ChoiceUserPay userPay = new ChoiceUserPay();
            userPay.setId(DataBaseTool.createId());
            userPay.setPayNo(data.get("getNo"));
            userPay.setUserNo(data.get("userNo"));
            userPay.setResourceNo(data.get("resourceNo"));
            userPay.setChoiceType(choiceInfo.getChoiceType());
            userPay.setPayAmount(new BigDecimal(data.get("peach")));//蟠桃支付
            userPay.setPayDate(new Date());
            userPay.setPayState(SysCode.CODE_10380002.getCode());
            int i = choiceUserPayMapper.insert(userPay);
            if (i < 0) {
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
            payDetail.setUserNo(data.get("userNo"));
            payDetail.setPayModeNo(data.get("payModeNo"));
            payDetail.setPayAmount(new BigDecimal(data.get("amount")));//网络支付
            int j = choiceUserPayDetailMapper.insert(payDetail);
            if (j < 0) {
                log.error("保存现金支付记录失败!");
                responseData.setCode(StatusEnum.FAIL.getCode());
                responseData.setMessage(StatusEnum.FAIL.getMsg());
                responseData.setData(StatusEnum.FAIL);
                return responseData;
            }
            //5已购数量增加
            choiceInfo.setPayNum(choiceInfo.getPayNum() + 1);
            choiceInfoMapper.updateByPrimaryKeySelective(choiceInfo);
            //6新增优惠券张数
            ChoiceUser choiceUser = new ChoiceUser();
            choiceUser.setId(DataBaseTool.createId());
            choiceUser.setUserNo(data.get("userNo"));
            choiceUser.setResourceNo(data.get("resourceNo"));
            choiceUser.setGetNo(data.get("getNo"));
            choiceUser.setCouponNo(data.get("couponNo"));//DataBaseTool.createNum(18)
            choiceUser.setGetDate(new Date());
            choiceUser.setUseState(SysCode.CODE_10370001.getCode());
            choiceUser.setChoiceType(choiceInfo.getChoiceType());
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(choiceUserMapper.insertSelective(choiceUser));
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    /**
     * 我的未使用的精选券列表
     *
     * @param map
     * @return
     */
    public ResponseData getNotUsedList(Map<String, String> map) {
        ResponseData responseData = new ResponseData();
        String userNo = map.get("userNo");
        String date = DataBaseTool.createDate();
        List<Map<String, Object>> choiceUser = choiceUserMapper.getChoiceUserL(userNo, date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> mapList : choiceUser) {
            // 获取用户订单判断是否是自己的订单
            String couponno = (String) mapList.get("couponNo");
            if (!couponno.substring(0, 2).equals("GK")) {
                mapList.put("exists", false);
            } else {
                mapList.put("exists", true);
            }
            mapList.put("startDate", sdf.format(mapList.get("startDate")));
            mapList.put("endDate", sdf.format(mapList.get("endDate")));
        }
        responseData.setCode(StatusEnum.SUCCESS.getCode());
        responseData.setMessage(StatusEnum.SUCCESS.getMsg());
        responseData.setTotalCount(choiceUser.size());
        responseData.setData(choiceUser);
        return responseData;
    }


    /**
     * 我已使用的精选券列表
     *
     * @param map
     * @return
     */
    public ResponseData getUsedList(Map<String, String> map) {
        ResponseData responseData = new ResponseData();
        String userNo = map.get("userNo");
        List<Map<String, Object>> choiceUser = choiceUserMapper.getChoiceUserSy(userNo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> mapList : choiceUser) {
            mapList.put("useDate", sdf.format(mapList.get("useDate")));
        }
        responseData.setCode(StatusEnum.SUCCESS.getCode());
        responseData.setMessage(StatusEnum.SUCCESS.getMsg());
        responseData.setTotalCount(choiceUser.size());
        responseData.setData(choiceUser);
        return responseData;
    }


    /**
     * 获取我的已过期的票券列表
     *
     * @param map
     * @return
     */

    public ResponseData getOverdueList(Map<String, String> map) {
        ResponseData responseData = new ResponseData();
        String userNo = map.get("userNo");
        String date = DataBaseTool.createDate();
        List<Map<String, Object>> choiceUser = choiceUserMapper.getChoiceUserGq(userNo, date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> mapList : choiceUser) {
            mapList.put("startDate", sdf.format(mapList.get("startDate")));
            mapList.put("endDate", sdf.format(mapList.get("endDate")));
        }
        responseData.setCode(StatusEnum.SUCCESS.getCode());
        responseData.setMessage(StatusEnum.SUCCESS.getMsg());
        responseData.setTotalCount(choiceUser.size());
        responseData.setData(choiceUser);
        return responseData;
    }


    /**
     * 获取二维码
     *
     * @param data
     * @return 返回Base64
     */
    public ResponseData getShowCode(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        try {
            String couponNo = data.get("couponNo");
            ChoiceUser choiceUser = choiceUserMapper.getCouponNo(couponNo);
            if (null == choiceUser) {
                log.warn("can't find ChoiceInfo:" + data.get("couponNo"));
                responseData.setCode(StatusEnum.NOT_FOUND.getCode());
                responseData.setMessage(StatusEnum.NOT_FOUND.getMsg());
                responseData.setStatus(StatusEnum.NOT_FOUND);
                return responseData;
            }
            if (!choiceUser.getUseState().equals(SysCode.CODE_10370001.getCode())) {
                log.warn("无效优惠券，已被使用");
                responseData.setCode(StatusEnum.FAIL.getCode());
                responseData.setMessage(StatusEnum.FAIL.getMsg());
                responseData.setStatus(StatusEnum.FAIL);
                return responseData;
            }
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            //设置二维码四周白色区域的大小
            hints.put(EncodeHintType.MARGIN, 1);
            //设置二维码的容错性
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //画二维码
            BitMatrix bitMatrix = multiFormatWriter.encode(couponNo, BarcodeFormat.QR_CODE, 400, 400, hints);
            BufferedImage image = toBufferedImage(bitMatrix);
            //注意此处拿到字节数据
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //输出二维码图片流
            ImageIO.write(image, "png", outputStream);
            //Base64编码
            String base64String = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            Map<String, String> map = new HashMap<>();
            map.put("qrCode", base64String);
            map.put("couponNo", choiceUser.getCouponNo());
            map.put("payNum", choiceUser.getPayNum().toString());
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    /**
     * 消费券（使用券）
     *
     * @param data
     * @return
     */
    public ResponseData updateUseState(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        try {
            ChoiceUser choiceUser = choiceUserMapper.getCouponNo(data.get("couponNo"));
            if (null == choiceUser) {
                log.warn("can't find ChoiceInfo:" + data.get("couponNo"));
                responseData.setCode(StatusEnum.NOT_FOUND.getCode());
                responseData.setMessage(StatusEnum.NOT_FOUND.getMsg());
                responseData.setStatus(StatusEnum.NOT_FOUND);
                return responseData;
            }
            if (!choiceUser.getUseState().equals(SysCode.CODE_10370002.getCode())) {
                log.warn("无效优惠券，已被使用");
                responseData.setCode(StatusEnum.FAIL.getCode());
                responseData.setMessage(StatusEnum.FAIL.getMsg());
                responseData.setStatus(StatusEnum.FAIL);
                return responseData;
            }
            choiceUser.setUseState(SysCode.CODE_10370002.getCode());
            choiceUser.setUseDate(new Date());

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(choiceUserMapper.updateByPrimaryKeySelective(choiceUser));
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    /**
     * 点击未支付订单消息，跳转支付页面，去支付
     * 判断是否超时已取消订单
     *
     * @param data
     * @return 用户编码、订单号、支付金额、蟠桃数
     */
    public ResponseData goPay(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        try {
            ChoiceUserPay choiceUserPay = choiceUserPayMapper.getNotPayByPayNo(data.get("payNo"));
            if (null == choiceUserPay) {
                responseData.setCode(StatusEnum.NOT_FOUND.getCode());
                responseData.setMessage("订单不存在或订单已超时，请重新下单!");
                return responseData;
            }
            //返回距有效支付时间，秒
            Long second = (System.currentTimeMillis() - choiceUserPay.getPayDate().getTime()) / 1000;//相差秒数
            if (second >= 30 * 60) {
                responseData.setCode(StatusEnum.FAIL.getCode());
                responseData.setMessage("订单超时，请重新下单!");
                return responseData;
            }
            //剩余时间
            Long seconds = 30 * 60 - second;
            data.put("countDown", seconds.toString());//倒计时
            ChoiceInfo choiceInfo = choiceInfoMapper.getDetailByResourceNo(choiceUserPay.getResourceNo());
            data.put("payAmount", choiceInfo.getPayAmount().toString());
            data.put("costAmount", choiceInfo.getCostAmount().toString());
            data.put("payAmountWukb", choiceInfo.getPayAmountWukb().toString());
            data.put("payNum", String.valueOf(choiceUserPay.getPayNum()));
            data.put("homeSmallPhoto", choiceInfo.getHomeSmallPhoto());
            data.put("titleDesc", choiceInfo.getTitleDesc());
            data.put("shareNo", data.get("shareNo"));
            data.put("shareRebateAmount", choiceInfo.getShareRebateAmount().toString());

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(data);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }
}