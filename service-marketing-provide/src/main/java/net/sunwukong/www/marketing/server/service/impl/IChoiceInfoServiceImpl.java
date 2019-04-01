package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import com.sdkinfo.www.core.util.ObjectUtil;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.*;
import net.sunwukong.www.marketing.server.dao.*;
import net.sunwukong.www.marketing.server.service.IChoiceInfoService;
import net.sunwukong.www.marketing.server.service.IPlatformAccountLogService;
import net.sunwukong.www.marketing.server.service.IUserAccountService;
import net.sunwukong.www.marketing.server.util.imgtools.CodeImgTools;
import net.sunwukong.www.marketing.vo.ChoiceInfoDetailVo;
import net.sunwukong.www.marketing.vo.ChoiceInfoListVo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IChoiceInfoServiceImpl
 * @Author: kangdong
 * @Date: 2018/7/11 下午5:32
 * @Description: 全球精选信息实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */
@Service
public class IChoiceInfoServiceImpl implements IChoiceInfoService {

    private static final Logger log = LoggerFactory.getLogger(IChoiceInfoServiceImpl.class);

    @Autowired
    ChoiceInfoMapper choiceInfoMapper;

    @Autowired
    CollectionInfoMapper collectionInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    ChoiceApplyMapper choiceApplyMapper;

    @Autowired
    ChoiceUserMapper choiceUserMapper;

    @Autowired
    ChoiceUserPayMapper choiceUserPayMapper;

    @Autowired
    ChoiceUserPayDetailMapper choiceUserPayDetailMapper;

    @Autowired
    IUserAccountService iUserAccountService;

    @Autowired
    public IPlatformAccountLogService iPlatformAccountLogService;

    private Map cartLockMap = new HashMap();

    /**
     * 获取顶部分页精选信息 10360001
     *
     * @param data
     * @return
     */
    public ResponseData getTopList(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        try {
            List<ChoiceInfoListVo> voList = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("start", 0);
            map.put("end", 4);
            map.put("auditState", SysCode.CODE_10070002.getCode());
            map.put("positionNo", SysCode.CODE_10360001.getCode());
            System.out.println("------------------" + data.toString());
            // 地理位置
            String positiongoods = null;
            try {
                positiongoods = data.get("positiongoods");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("获取的地理位置：" + positiongoods);
            if (positiongoods != null && positiongoods != "") {
                if (!positiongoods.equals("中国")) {
                    positiongoods = "海外";
                }
            }
            map.put("positiongoods", positiongoods);
            List<ChoiceInfo> list = choiceInfoMapper.getListByPositionNo(map);
            for (ChoiceInfo info : list) {
                ChoiceInfoListVo vo = new ChoiceInfoListVo();
                vo.setId(info.getId());
                vo.setResourceNo(info.getResourceNo());
                vo.setResourceName(info.getResourceName());
                vo.setPhoto(info.getHomeTopPhoto());//顶部中图
                vo.setChoiceType(info.getChoiceType());
                voList.add(vo);
            }
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(voList);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    /**
     * 中部banner(只有一张)
     *
     * @param data
     * @return
     */
    @Override
    public ResponseData getMiddleList(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        try {
            List<ChoiceInfoListVo> voList = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("start", 0);
            map.put("end", 1);
            map.put("auditState", SysCode.CODE_10070002.getCode());
            map.put("positionNo", SysCode.CODE_10360002.getCode());
            List<ChoiceInfo> list = choiceInfoMapper.getListByPositionNo(map);
            for (ChoiceInfo info : list) {
                ChoiceInfoListVo vo = new ChoiceInfoListVo();
                vo.setId(info.getId());
                vo.setResourceNo(info.getResourceNo());
                vo.setResourceName(info.getResourceName());
                vo.setPhoto(info.getHomeMiddlePhoto());//中部Banner图
                vo.setChoiceType(info.getChoiceType());
                voList.add(vo);
            }
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(voList);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }

    /**
     * 获取底部分页精选信息 10360003
     *
     * @param data
     * @return
     */
    @Override
    public ResponseData getBottomList(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        List<ChoiceInfoListVo> voList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Integer start = ApiTool.getStartPage(Integer.parseInt(data.get("pageNo")), Integer.parseInt(data.get("pageSize")));
        map.put("start", start);
        map.put("end", data.get("pageSize"));
        map.put("keyword", data.get("keyword"));
        map.put("auditState", SysCode.CODE_10070002.getCode());
        map.put("positionNo", SysCode.CODE_10360003.getCode());
        System.out.println("------------------" + data.toString());
        // 地理位置
        String positiongoods = null;
        try {
            positiongoods = data.get("positiongoods");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("获取的地理位置：" + positiongoods);
        if (positiongoods != null && positiongoods != "") {
            if (!positiongoods.equals("中国")) {
                positiongoods = "海外";
            }
        }
        map.put("positiongoods", positiongoods);
        List<ChoiceInfo> list = choiceInfoMapper.getListByPositionNo(map);
        int total = choiceInfoMapper.getTotal(map);
        for (ChoiceInfo info : list) {
            ChoiceInfoListVo vo = new ChoiceInfoListVo();
            vo.setId(info.getId());
            vo.setResourceNo(info.getResourceNo());
            vo.setResourceName(info.getResourceName());
            vo.setPhoto(info.getHomeSmallPhoto());//底部小图
            vo.setPayAmount(info.getPayAmount());//购买金额
            vo.setCostAmount(info.getCostAmount());
            vo.setUnit(info.getUnit());
            vo.setChoiceType(info.getChoiceType());
            //通过用户id获取用户编码
            if (null == data.get("userId")) {
                vo.setCollection(false);
            } else {
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(data.get("userId"));
                if (null != userInfo) {
                    //判断我是否收藏
                    Boolean isCollection = collectionInfoMapper.getCollectionCount(userInfo.getUserNo(), info.getResourceNo(), SysCode.CODE_10110002.getCode());
                    vo.setCollection(isCollection);
                } else {
                    vo.setCollection(false);
                }
            }
            voList.add(vo);
        }
        responseData.setCode(StatusEnum.SUCCESS.getCode());
        responseData.setMessage(StatusEnum.SUCCESS.getMsg());
        responseData.setTotalCount(total);
        responseData.setData(voList);
        return responseData;
    }

    /**
     * 查看精选详情
     *
     * @param resourceNo
     * @param userNo
     * @return
     */
    @Override
    public ResponseData getDetail(String resourceNo, String userNo) {
        ResponseData responseData = new ResponseData();
        ChoiceInfoDetailVo vo = new ChoiceInfoDetailVo();
        ChoiceInfo choiceInfo = choiceInfoMapper.getDetailByResourceNo(resourceNo);
        if (null == choiceInfo) {
            responseData.setCode(StatusEnum.NOT_FOUND.getCode());
            responseData.setMessage(StatusEnum.NOT_FOUND.getMsg());
            responseData.setData(StatusEnum.NOT_FOUND);
            return responseData;
        }
        vo.setId(choiceInfo.getId());
        vo.setUserNo(choiceInfo.getUserNo());
        vo.setResourceNo(choiceInfo.getResourceNo());
        vo.setResourceName(choiceInfo.getResourceName());
        vo.setTitleDesc(choiceInfo.getTitleDesc());
        vo.setCopywritUrl(choiceInfo.getCopywritUrl());
        vo.setDetailPhoto(choiceInfo.getDetailPhoto());// 详情banner轮播图
        vo.setPraiseNum(choiceInfo.getPraiseNum());
        vo.setPayAmount(choiceInfo.getPayAmount());//购买金额
        vo.setChoiceType(choiceInfo.getChoiceType());
        //详情页顶部banner图
        vo.setBannerPhoto(choiceInfo.getBannerPhoto());
        //如果不是广告
        if (!choiceInfo.getChoiceType().equals(SysCode.CODE_10350003.getCode())) {
            if (userNo != null && userNo != "") {
                /** 把分享的图片的返回**/
                String bas = null;
                // 获取分享单号
                String shareNo = DataBaseTool.createNo("FX");
                // 生成购买时间
                String date = DataBaseTool.createDate();
                String url = "https://www.sunwukong.net/h5/components/global-vouchers.html?shareNo=" + shareNo + "&resourceNo=" + resourceNo + "&userNo=" + userNo + "&shareRebateAmount=" + choiceInfo.getShareRebateAmount();
                String imgurl = choiceInfo.getSharephoto();
                // 存入分享信息表choice_share_info

                int insertchoice_share_info = choiceInfoMapper.insertShareInfo(DataBaseTool.createId(), shareNo, userNo, choiceInfo.getChoiceType(), resourceNo, date);
                // 存入精选分享购买信息表
                if (insertchoice_share_info > 0) {
                    responseData.setCode(StatusEnum.SUCCESS.getCode());
                    try {
                        bas = CodeImgTools.backBase64(url, imgurl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                vo.setImgurl(bas);
            }
            vo.setPutNum(choiceInfo.getPutNum());//投放张数
            vo.setPayNum(choiceInfo.getPayNum());//购买张数
            vo.setCouponAmount(choiceInfo.getCouponAmount());//面额
            vo.setCostAmount(choiceInfo.getCostAmount());//券成本金额
            vo.setShareRebateAmount(choiceInfo.getShareRebateAmount());//分享返利金额
            vo.setCutMinAmount(choiceInfo.getCutMinAmount());//砍价底价
            vo.setPayAmountWukb(choiceInfo.getPayAmountWukb());//悟空币
            //商家信息
            vo.setMerchantName(choiceInfo.getMerchantName());
            vo.setMerchantContract(choiceInfo.getMerchantContract());
            vo.setMerchantAddress(choiceInfo.getMerchantAddress());
            vo.setMerchantPhone(choiceInfo.getMerchantPhone());
            // 使用规则
            vo.setUseRules(choiceInfo.getUseRules());
            //使用说明
            vo.setUseExplain(choiceInfo.getUseExplain());
            //每个用户限购数量
            vo.setMaxPayNum(choiceInfo.getMaxPayNum());
            //票券使用时间
            vo.setStartDate(choiceInfo.getStartDate());
            vo.setEndDate(choiceInfo.getEndDate());
            // 查询当前用户的是否有电话号码
            String bo = choiceInfoMapper.getPhone(userNo);
            if (!ObjectUtil.isEmpty(bo)) {
                vo.setPhoneno(true);
            } else {
                vo.setPhoneno(false);
            }
        }
        boolean collectionCount = collectionInfoMapper.getCollectionCount(userNo, resourceNo, SysCode.CODE_10110002.getCode());
        vo.setCollection(collectionCount);
        responseData.setCode(StatusEnum.SUCCESS.getCode());
        responseData.setMessage(StatusEnum.SUCCESS.getMsg());
        responseData.setData(vo);
        return responseData;
    }


    /**
     * 点击收藏或取消收藏精选
     *
     * @param data
     * @return
     */
    @Override
    public ResponseData addOrCancel(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        String resourceNo = data.get("resourceNo");
        if (null == resourceNo) {
            log.warn("请选择收藏的资源!");
            responseData.setCode(StatusEnum.NOT_FOUND.getCode());
            responseData.setMessage("请选择需要收藏的资源");
            responseData.setData(StatusEnum.NOT_FOUND);
            return responseData;
        }
        String userId = data.get("userId");
        if (null == userId) {
            log.warn("用户不能为空，或用户未登录!");
            responseData.setCode(StatusEnum.FAIL.getCode());
            responseData.setMessage("用户不能为空，或用户未登录!");
            responseData.setData(StatusEnum.FAIL);
            return responseData;
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(data.get("userId"));
        if (null == userInfo) {
            log.warn("此用户不存在!");
            responseData.setCode(StatusEnum.FAIL.getCode());
            responseData.setMessage("此用户不存在!");
            responseData.setData(StatusEnum.FAIL);
            return responseData;
        }
        //isCollection 收藏或取消收藏：true表示已经收藏，false表示未收藏
        String isCollection = data.get("isCollection");

        synchronized (getCartLock(userId)) {
            CollectionInfo info = new CollectionInfo();
            if (isCollection.equals("false")) {//表示已经收藏，需要取消收藏
                responseData.setData(collectionInfoMapper.deleteByResourceNoAndUserNo(resourceNo, userInfo.getUserNo()));
            } else {
                info.setId(DataBaseTool.createId());
                info.setUserNo(userInfo.getUserNo());
                info.setContentNo(resourceNo);
                info.setCollectionDate(new Date());
                info.setCollectionType(SysCode.CODE_10110002.getCode());
                responseData.setData(collectionInfoMapper.insert(info));
            }
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
        }
        return responseData;
    }


    /**
     * 购买，用户下单
     */
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addBuy(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        try {
            String userNo = data.get("userNo");
            //本次下单数
            int payNum = Integer.valueOf(data.get("payNum"));
            if (payNum == 1) {
                String resourceNo = data.get("resourceNo");
                if (userNo != null && userNo != "" && payNum != 0 && resourceNo != null && resourceNo != "") {
                    // 查询当前资源编码的数据
                    Map<String, Object> choiceInfo = choiceInfoMapper.getChoiceInfo(resourceNo);
                    log.error("查询的数据：" + choiceInfo.toString());
                    int putnums = (int) choiceInfo.get("putNum");
                    int paynums = (int) choiceInfo.get("payNum");
                    Date endTime = (Date) choiceInfo.get("endDate");
                    long etime = endTime.getTime();
                    System.out.println("商品结束时间为：" + endTime.getTime());
                    // 获取当前时间戳
                    long xtime = System.currentTimeMillis();
                    System.out.println("当前时间戳为：" + System.currentTimeMillis());
                    // 判断结束时间和当前的时间是否存在过期
                    if (xtime <= etime) {
                        // 查看购买的券数据的剩余量
                        int maxsy = putnums - paynums;
                        if (choiceInfo != null) {
                            // 看是否小于现在的发行数量
                            log.error("发行的数量：" + putnums + "已经购买的张数：" + paynums + "还剩余的张数：" + maxsy);
                            if (payNum < putnums) {
                                //判断剩余资源数是否大于下单数、大于才能下单(发行数-购买数)
                                if (payNum <= maxsy) {
                                    // 生成支付订单单号
                                    String payNo = DataBaseTool.createNum(28);
                                    String couponNo = null;
                                    // 查看数据库存在商家给的码没有，没有就自动生成，有就进行使用商家的码
                                    String ifthere = choiceInfoMapper.selectifthere(resourceNo);
                                    if (!ObjectUtil.isEmpty(ifthere)) {
                                        couponNo = ifthere;
                                        // 修改当前码为使用状态
                                        int upcoup = choiceInfoMapper.updatecouponNo(resourceNo, couponNo);
                                        if (upcoup == 0) {
                                            responseData.setCode(StatusEnum.FAIL.getCode());
                                            responseData.setMessage("下单失败,稍后再试");
                                            responseData.setData(StatusEnum.FAIL);
                                            return responseData;
                                        }
                                    } else {
                                        // 生成优惠券单号
                                        couponNo = "GK" + DataBaseTool.createNum(18);
                                    }
                                    // 精选类型
                                    String choicetype = (String) choiceInfo.get("choiceType");
                                    // 悟空币
                                    BigDecimal wukbsum = (BigDecimal) choiceInfo.get("payAmountWukb");
                                    // 使用的金额
                                    BigDecimal payamount = (BigDecimal) choiceInfo.get("payAmount");
                                    // 卷使用情况
                                    String usestate = "10370001";
                                    // 支付情况
                                    String paystate = "10380001";
                                    // 获取时间
                                    String date = DataBaseTool.createDate();
                                    int insertchoiceuser = choiceInfoMapper.insertChoiceuser(DataBaseTool.createId(), userNo, choicetype, resourceNo, payNo, couponNo, date, usestate, payNum);
                                    int insertchoicepay = choiceInfoMapper.insertChoicepay(DataBaseTool.createId(), userNo, payNo, choicetype, resourceNo, wukbsum, payamount, paystate, date);
                                    // 说明所有的信息插入成功返回前端的参数列表
                                    if (insertchoicepay > 0 && insertchoiceuser > 0) {
                                        Map<String, Object> datamap = new HashMap<>();
                                        Long second = (System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime()) / 1000;//相差秒数
                                        if (second >= 30 * 60) {
                                            responseData.setCode(StatusEnum.FAIL.getCode());
                                            responseData.setMessage("订单超时，请重新下单!");
                                            return responseData;
                                        }
                                        //剩余时间
                                        Long seconds = 30 * 60 - second;
                                        datamap.put("countDown", seconds.toString());//倒计时
                                        datamap.put("payNo", payNo);
                                        datamap.put("userNo", userNo);
                                        datamap.put("payNum", payNum);
                                        datamap.put("payDate", new Date().toString());
                                        datamap.put("couponNo", couponNo);
                                        datamap.put("payAmount", payamount);
                                        datamap.put("payAmountWukb", wukbsum);
                                        datamap.put("titleDesc", choiceInfo.get("titleDesc"));
                                        datamap.put("homeSmallPhoto", choiceInfo.get("homeSmallPhoto"));
                                        responseData.setCode(StatusEnum.SUCCESS.getCode());
                                        responseData.setMessage(StatusEnum.SUCCESS.getMsg());
                                        responseData.setData(datamap);
                                        // 修改已购张数
//                                        int uppaynum = choiceInfoMapper.uppayNum(resourceNo);
//                                        if (uppaynum > 0) {
//                                            return responseData;
//                                        } else {
//                                            responseData.setCode(StatusEnum.FAIL.getCode());
//                                            responseData.setMessage("对不起下单失败!");
//                                            responseData.setData(StatusEnum.FAIL);
//                                            return responseData;
//                                        }
                                        return responseData;
                                    }
                                } else {
                                    responseData.setCode(StatusEnum.FAIL.getCode());
                                    responseData.setMessage("抱歉！你购买的资源已售馨");
                                    responseData.setData(StatusEnum.FAIL);
                                    return responseData;
                                }
                            }
                        }
                    }
                }
            }
            responseData.setCode(StatusEnum.FAIL.getCode());
            responseData.setMessage("剩余优惠券不足,请稍后再试!");
            responseData.setData(StatusEnum.FAIL);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    /**
     * 定时查询用户下单未支付的订单
     * 1、30分钟内未支付的订单choice_user_pay
     * 2、取消订单 choice_user_pay（pay_state）
     * 4、精选优惠券数量增加
     *
     * @return
     */
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData cancelBuy() {
        System.out.println("---------------定时进来-----------------");
        ResponseData responseData = new ResponseData();
        return responseData;
    }

    /**
     * 分享精选信息
     * 返回参数：分享单号，购买单号，支付金额，返利金额，图片，标题，倒计时，购买数量
     */
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addShare(Map<String, String> data) {
        try {
            ResponseData responseData = new ResponseData();
            // 获取资源编码
            String resourceNo = data.get("resourceNo");
            ChoiceInfo info = choiceInfoMapper.getDetailByResourceNo(resourceNo);
            // 获取分享着用户编码
            String userNo = data.get("userNo");
            // 获取分享单号
            String shareNo = DataBaseTool.createNo("FX");
            // 生成购买时间
            String date = DataBaseTool.createDate();
            String url = "https://www.sunwukong.net/h5/components/global-vouchers.html?shareNo=" + shareNo + "&resourceNo=" + resourceNo + "&userNo=" + userNo + "&shareRebateAmount=" + info.getShareRebateAmount();
            String imgurl = info.getSharephoto();
            // 存入分享信息表choice_share_info
            int insertchoice_share_info = choiceInfoMapper.insertShareInfo(DataBaseTool.createId(), shareNo, userNo, info.getChoiceType(), resourceNo, date);
            // 存入精选分享购买信息表
            if (insertchoice_share_info > 0) {
                responseData.setCode(StatusEnum.SUCCESS.getCode());
                String bas = CodeImgTools.backBase64(url, imgurl);
                responseData.setData(bas);
                return responseData;
            }
            responseData.setCode(StatusEnum.FAIL.getCode());
            return responseData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 取消订单
     */
    public ResponseData cancelOrder(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        // 订单号
        String orderNo = data.get("orderNo");
        // 资源编码
        String resourceNo = data.get("resourceNo");
        // 券编码
        String couponNo = data.get("couponNo");
        // 取前两个字段判断是否是商家的券
        log.error("--------取消的订单--------" + data.toString());
        if (!couponNo.substring(0, 2).equals("GK")) {
            // 改变商户券的状态
            int upShcoupon = choiceInfoMapper.upCoupon(resourceNo, couponNo);
            if (upShcoupon > 0) {
//                // 修改当前资源可以购买的总个数
//                int buysum = choiceInfoMapper.updateBysum(resourceNo);
//                if (buysum > 0) {
//                    responseData.setCode(StatusEnum.SUCCESS.getCode());
//                    responseData.setData("订单取消成功");
//                    return responseData;
//                } else {
//                    log.error("取消订单减去当前资源的已经购买次数失败：资源编码：" + resourceNo);
//                    responseData.setCode(StatusEnum.SUCCESS.getCode());
//                    responseData.setData("订单取消失败");
//                    return responseData;
//                }
                responseData.setCode(StatusEnum.SUCCESS.getCode());
                responseData.setData("订单取消成功");
                return responseData;
            } else {
                responseData.setCode(StatusEnum.FAIL.getCode());
                responseData.setData("订单取消失败");
                return responseData;
            }
        } else {
            // 修改当前资源可以购买的总个数
            int buysum = choiceInfoMapper.updateBysum(resourceNo);
            if (buysum > 0) {
                responseData.setCode(StatusEnum.SUCCESS.getCode());
                responseData.setData("订单取消成功");
                return responseData;
            } else {
                log.error("取消订单减去当前资源的已经购买次数失败：资源编码：" + resourceNo);
                responseData.setCode(StatusEnum.SUCCESS.getCode());
                responseData.setData("订单取消失败");
                return responseData;
            }
        }
    }

    /**
     * 取消超时的订单
     */
    public List<Map<String, String>> getDataList(String timet) {
        // 准换成时间格式
        return choiceInfoMapper.getDataList(timet);
    }

    // 删除订单
    public int deletePay(String getNo) {
        return choiceInfoMapper.deletePay(getNo);
    }

    // 修改当前的状态
    public int updatePay(String resourceNo, String couponNo) {
        return choiceInfoMapper.updatePay(resourceNo, couponNo);
    }

    // 查询当前的支付状态
    public String selectPs(String getNo) {
        return choiceInfoMapper.selectPs(getNo);
    }

    private synchronized Object getCartLock(String userId) {
        Object lock = cartLockMap.get(userId);
        if (lock == null) {
            lock = new Object();
            cartLockMap.put(userId, lock);
        }
        return lock;
    }
}