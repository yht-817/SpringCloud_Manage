package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import com.sdkinfo.www.core.util.ObjectUtil;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.config.SwkConfig;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.MsgTitleEnum;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.api.util.TimeUtil;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.*;
import net.sunwukong.www.marketing.server.dao.*;
import net.sunwukong.www.marketing.server.domain.DemandParamPo;
import net.sunwukong.www.marketing.server.service.IDemandInfoService;
import net.sunwukong.www.marketing.server.service.IMarketingSendSetService;
import net.sunwukong.www.marketing.server.service.IPlatformAccountService;
import net.sunwukong.www.marketing.server.web.BaseController;
import net.sunwukong.www.marketing.vo.MyDemandDetailVo;
import net.sunwukong.www.marketing.vo.MyDemandServerListVo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IDemandInfoServiceImpl
 * @Author: kangdong
 * @Date: 2018/6/16 下午8:02
 * @Description: 需求信息实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class IDemandInfoServiceImpl extends BaseController implements IDemandInfoService {

    private static final Logger log = LoggerFactory.getLogger(IDemandInfoServiceImpl.class);

    @Autowired
    DemandInfoMapper demandInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    IPlatformAccountService iPlatformAccountService;

    @Autowired
    PlatformAccountLogMapper platformAccountLogMapper;

    @Autowired
    PlatformAccountMapper platformAccountMapper;


    @Autowired
    UserAccountLogMapper userAccountLogMapper;

    @Autowired
    UserAccountMapper userAccountMapper;

    @Autowired
    IMarketingSendSetService iMarketingSendSetService;

    @Autowired
    UserApplyMapper userApplyMapper;

    @Autowired
    CityMangerApplyMapper cityMangerApplyMapper;


    /**
     * 获取用户需求列表
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getDemandList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {
            //demandParamPo.setState("");
            //封装返回数据
            List<MyDemandServerListVo> voList = setMyDemandServerListVo(demandParamPo);
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
     * 未开始的需求列表(包含待接单和已抢单)
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getNotStartedList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {
            //封装返回数据
            List<DemandInfo> list =  demandInfoMapper.getNotStartListByUserNo(demandParamPo);
            List<MyDemandServerListVo> voList = new ArrayList();
            for(DemandInfo info : list) {
                MyDemandServerListVo vo = new MyDemandServerListVo();
                vo.setId(info.getId());
                vo.setDemandNo(info.getDemandNo());
                vo.setSpecialRemark(info.getSpecialRemark());
                vo.setDateTime(TimeUtil.getChatTimeStr(info.getSubmitDate().getTime()));
                vo.setStateCode(info.getDemandState());
                vo.setStateName(SysCode.fromCodeName(info.getDemandState()).getMsg());
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
     * 服务中的需求列表
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getInServiceList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {
            demandParamPo.setState(SysCode.CODE_10080002.getCode());
            //封装返回数据
            List<MyDemandServerListVo> voList = setMyDemandServerListVo(demandParamPo);

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
     * 待结单的需求列表
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getWaitEndList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {
            demandParamPo.setState(SysCode.CODE_10080003.getCode());
            //封装返回数据
            List<MyDemandServerListVo> voList = setMyDemandServerListVo(demandParamPo);

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
     * 已结单的需求列表
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getFinishList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {
            demandParamPo.setState(SysCode.CODE_10080004.getCode());
            //封装返回数据
            List<MyDemandServerListVo> voList = setMyDemandServerListVo(demandParamPo);

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
     * 我的服务列表：封装返回数据
     * @param demandParamPo
     * @return
     */
    public List<MyDemandServerListVo> setMyDemandServerListVo(DemandParamPo demandParamPo) {
        List<DemandInfo> list =  demandInfoMapper.getDemandListByUserNoAndState(demandParamPo);
        List<MyDemandServerListVo> voList = new ArrayList();
        for(DemandInfo info : list) {
            MyDemandServerListVo vo = new MyDemandServerListVo();

            vo.setId(info.getId());
            vo.setDemandNo(info.getDemandNo());
            vo.setSpecialRemark(info.getSpecialRemark());
            vo.setDateTime(TimeUtil.getChatTimeStr(info.getSubmitDate().getTime()));
            vo.setStateCode(info.getDemandState());
            vo.setStateName(SysCode.fromCodeName(info.getDemandState()).getMsg());
            voList.add(vo);
        }
        return voList;
    }



    /**
     * 通过id获取我的需求信息详情
     * @param id id标识
     * @return
     */
    @Override
    public ResponseData getDemandInfoDetail(String id) {
        ResponseData responseData = new ResponseData();
        try {
            DemandInfo demandInfo = demandInfoMapper.selectByPrimaryKey(id);
            if(demandInfo != null) {
                MyDemandDetailVo vo = new MyDemandDetailVo();
                //设置需求信息
                vo.setId(id);
                vo.setDemandNo(demandInfo.getDemandNo());
                vo.setSpecialRemark(demandInfo.getSpecialRemark());
                vo.setPayNum(demandInfo.getPayNum());
                vo.setDemandState(demandInfo.getDemandState());
                vo.setCityName(demandInfo.getCityName());
                vo.setCategoryOneName(demandInfo.getCategoryOneName());
                vo.setCategoryTwoName(demandInfo.getCategoryTwoName());
                vo.setSubmitDate(TimeUtil.getChatTimeStr(demandInfo.getSubmitDate().getTime()));

                //设置需求方信息
                UserInfo userInfo = userInfoMapper.getUserByUserNo(demandInfo.getUserNo());
                vo.setPhoneNo(demandInfo.getPhoneNo());
                //只有未开始的需求状态，需求方查看需求详情时才显示自己的信息，其他状态显示服务方信息
                if(!demandInfo.getDemandState().equals(SysCode.CODE_10080001.getCode()) && !demandInfo.getDemandState().equals(SysCode.CODE_10080006.getCode())) {
                    userInfo = userInfoMapper.getUserByUserNo(demandInfo.getServiceUser());
                    //获取服务方申请的电话
                    UserApply userApply =  userApplyMapper.findByUserNoAndAuditState(demandInfo.getServiceUser(),SysCode.CODE_10070002.getCode());
                    if(userApply!=null) {
                        vo.setPhoneNo(userApply.getPhoneNo());
                    }
                }
//                if(!demandInfo.getDemandState().equals(SysCode.CODE_10080001.getCode()) || !demandInfo.getDemandState().equals(SysCode.CODE_10080006.getCode())) {
//                    //if(null == demandInfo.getServiceUser()) {//服务机构抢单，还没有服务方
//                    //    userInfo = userInfoMapper.getUserByUserNo(demandInfo.getRobUser());
//                    //}else {
//                    userInfo = userInfoMapper.getUserByUserNo(demandInfo.getServiceUser());
//                    vo.setUserNo(userInfo.getUserNo());
//                    vo.setPhoneNo(userInfo.getPhoneNo());
//                    //}
//                }
                vo.setUserId(userInfo.getId());
                vo.setUserNo(demandInfo.getUserNo());
                vo.setUserHead(userInfo.getUserHead());
                vo.setUserName(userInfo.getUserName());
                vo.setUserNikeName(userInfo.getNikeName());
                //vo.setDemandAddress();

                responseData.setCode(StatusEnum.SUCCESS.getCode());
                responseData.setMessage(StatusEnum.SUCCESS.getMsg());
                responseData.setData(vo);
            }else {
                responseData.setCode(StatusEnum.NOT_FOUND.getCode());
                responseData.setMessage(StatusEnum.NOT_FOUND.getMsg());
                responseData.setData(null);
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }

    /**
     * 发布需求信息
     * 发布需求涉及到 扣除蟠桃问题，下面针对用户的两个钱包做一个说明
     *
     * 需求编码：xq_money1_money1_......
     *
     * 这种情况在撤销需求的时候会用到 直接判断单号来原路返回蟠桃
     * @param demandInfo 需求信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData insertDemandInfo(DemandInfo demandInfo){
        ResponseData responseData = new ResponseData();
        //判断接单奖励是否是10的倍数
        if (demandInfo.getPayNum().intValue()%10!=0){
            throw new GrilException("接单奖励只能是10的倍数");
        }
        //1。获取用户个人账户
        UserAccount userAccount = iUserAccountService.getUserAccount(demandInfo.getUserNo());
        //判断用户账户是否存在
        if (userAccount==null){
            throw new GrilException("当前用户账户信息为空");
        }
        BigDecimal money1 = new BigDecimal(0);
        BigDecimal money2 = new BigDecimal(0);


        if (demandInfo.getPayNum().compareTo(userAccount.getAccountAmount().add(userAccount.getCashAccountAmount()))==1) {
            throw new GrilException("账户余额不足");
        }else if (demandInfo.getPayNum().compareTo(userAccount.getAccountAmount())!=1){
            //账户一满足需求
            money1 = demandInfo.getPayNum();
        }else if (demandInfo.getPayNum().compareTo(userAccount.getCashAccountAmount())!=1){
            //账户二满足需求
            money2 = demandInfo.getPayNum();
        }else {
            //账户一和账户二加起来才瞒住
            money1 = userAccount.getAccountAmount();
            money2 = demandInfo.getPayNum().subtract(money1);
        }

        demandInfo.setDemandNo(DataBaseTool.createNo("xq_"+money1+"_"+money2+"_"));

        //3.保存需求信息
        demandInfo.setId(DataBaseTool.createId());
        demandInfo.setSubmitDate(new Date());
        demandInfo.setDemandState(SysCode.CODE_10080001.getCode());//等待接单
        demandInfo.setServerState(SysCode.CODE_10290007.getCode());//未抢单
        int i = demandInfoMapper.insert(demandInfo);
        if (i<=0){
            throw new GrilException("发布需求失败");
        }

        //4.修改账户金额
        if (money1.intValue()>0){
            iUserAccountService.writePayPeach(demandInfo.getUserNo(),money1.negate(),false,SysCode.CODE_10050002);
        }
        if (money2.intValue()>0){
            iUserAccountService.writePayPeach(demandInfo.getUserNo(),money2.negate(),true,SysCode.CODE_10050002);
        }


        return responseData;
    }

    /**
     * 撤销需求
     * @param id        需求ID
     * @param userNo    用户编码
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData updateCancelDemandInfo(String id,String userNo) {
        DemandInfo demandInfo = demandInfoMapper.selectByPrimaryKey(id);
        //判断用户拆销的订单是本人的需求订单，且处于待接单状态
        if(ObjectUtil.isNull(demandInfo)) {
            throw new GrilException("当前需求不存在");
        }
        if(!userNo.equals(demandInfo.getUserNo())) {
            throw new GrilException("只能撤销自己的需求单");
        }
        if(!demandInfo.getDemandState().equals(SysCode.CODE_10080001.getCode())) {
            throw new GrilException("该需求状态下已不能撤销");
        }

        String[] split = demandInfo.getDemandNo().split("_");
        if (split.length>0){
            BigDecimal money1 = new BigDecimal(split[1]);
            BigDecimal money2 = new BigDecimal(split[2]);
            //4.修改账户金额
            if (money1.intValue()>0){
                iUserAccountService.writePayPeach(demandInfo.getUserNo(),money1,false,SysCode.CODE_10050009);
            }
            if (money2.intValue()>0){
                iUserAccountService.writePayPeach(demandInfo.getUserNo(),money2,true,SysCode.CODE_10050009);
            }
        }else {
            iUserAccountService.writePayPeach(demandInfo.getUserNo(),demandInfo.getPayNum(),false,SysCode.CODE_10050009);
        }

        //修改需求信息
        demandInfo.setDemandState(SysCode.CODE_10080005.getCode());
        demandInfo.setChangeDate(new Date());
        demandInfoMapper.updateByPrimaryKeySelective(demandInfo);
        return BaseResp.getInstance();
    }

    /**
     * 确认结单
     * 需求方确认结单，需要把金额按比例分成给个体服务方、服务机构、城市运营官、平台等
     * @param demandNo  需求编码
     * @param userNo    用户编码
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData updateConfirmFinish(String demandNo,String userNo) {
        DemandInfo demandInfo = demandInfoMapper.getDemandInfoByDemandNo(demandNo);
        if (demandInfo==null){
            throw new GrilException("需求不存在");
        }
        if (!demandInfo.getUserNo().equals(userNo)) {
            throw new GrilException("当前用户身份与需求发布者身份不同，无法结单");
        }

        if (!demandInfo.getDemandState().equals(SysCode.CODE_10080003.getCode())
                && !demandInfo.getServerState().equals(SysCode.CODE_10290003.getCode())){
            throw new GrilException("当前需求单状态无法结单");
        }
        //需求发布金额
        BigDecimal payNum = demandInfo.getPayNum();

        //获取分成配置
        SysDemandDivide sysDemandDivide = iSysDemandDivideService.selectOne();
        if (sysDemandDivide == null) {
            throw new GrilException("分成配置未查询到记录");
        }
        //分成给需求者
        iUserAccountService.writePayPeach(demandInfo.getUserNo(), payNum.multiply(sysDemandDivide.getDemandDivide()), true, SysCode.CODE_10050010);

        //分成给抢单者
        iUserAccountService.writePayPeach(demandInfo.getRobUser(), payNum.multiply(sysDemandDivide.getReceiptDivide()), true, SysCode.CODE_10050010);

        //分成给服务者
        iUserAccountService.writePayPeach(demandInfo.getServiceUser(), payNum.multiply(sysDemandDivide.getServiceDivide()), true, SysCode.CODE_10050010);

        //分成给城市运营官
        //1.查询当前订单对应的城市运营官
        UserApply userApply = iUserApplyService.findByUserNoAndAuditState(demandInfo.getRobUser(), SysCode.CODE_10070002.getCode());
        if (userApply!=null){
            iUserAccountService.writePayPeach(userApply.getUserNoManger(), payNum.multiply(sysDemandDivide.getMangerDivide()), true, SysCode.CODE_10050010);
        }

        //结单
        demandInfo.setDemandState(SysCode.CODE_10080004.getCode());
        demandInfo.setServerState(SysCode.CODE_10290004.getCode());
        demandInfoMapper.updateByPrimaryKey(demandInfo);
        return BaseResp.getInstance();
    }



    /**
     * 获取首页需求列表
     * @param data
     * @return
     */
    @Override
    public ResponseData getHomeDemandinfo(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        Map<String,Object> map = new HashMap<>();
        //查询用户身份
        UserInfo userInfo = userInfoMapper.getUserByUserNo(data.get("userNo"));
        map.put("start", String.valueOf(ApiTool.getStartPage(Integer.parseInt(data.get("pageNo")), Integer.parseInt(data.get("pageSize")))));
        map.put("end",data.get("pageSize"));
        if (userInfo!=null){
            String userType = userInfo.getUserType();
            //运营官
            if (userType.equals(SysCode.CODE_10020002.getCode())) {
                //获取城市运营官服务的城市
                CityMangerApply cityMangerApplyByUserNo = cityMangerApplyMapper.findByUserNoAndAuditState(userInfo.getUserNo(),SysCode.CODE_10070002.getCode());
                List<String> citys = new ArrayList<>();
                citys.add(cityMangerApplyByUserNo.getCityNo());
                map.put("citys",citys);
                map.put("demandState",SysCode.CODE_10080001.getCode());
            }
            //服务机构
            if (userType.equals(SysCode.CODE_10020003.getCode())){
                List<String> categoryTwoNos = new ArrayList<>();
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("userNo",data.get("userNo"));
                stringMap.put("auditState",SysCode.CODE_10070002.getCode());
                ResponseData<List<UserApplyCategory>> applyNoAfterAuditCategoryList = iUserApplyCategoryService.getApplyNoAfterAuditCategoryList(stringMap);
                List<UserApplyCategory> userApplyCategories = applyNoAfterAuditCategoryList.getData();
                //将二级类目转换为list
                userApplyCategories.forEach(userApplyCategory -> {
                    categoryTwoNos.add(userApplyCategory.getCategoryTwoNo());
                });
                if(categoryTwoNos.size()>0){
                    map.put("categoryTwoNos",categoryTwoNos);
                }
                //需求状态为等待接单
                map.put("demandState",SysCode.CODE_10080001.getCode());
            }
            //普通用户
            if (userType.equals(SysCode.CODE_10020001.getCode())){
                map.put("serverState",SysCode.CODE_10290005.getCode());
            }
            //个体服务
            if(userType.equals(SysCode.CODE_10020004.getCode())){
                //查询服务类目
                /*Map<String, Object> mapAccount = new HashMap<>();
                mapAccount.put("userNo",data.get("userNo"));
                RequestData requestDataAccount = new RequestData();
                requestDataAccount.setUserNo(data.get("userNo"));
                requestDataAccount.setData(AES128UtilBase64.encryptAES(JSONUtil.map2json(mapAccount)));
                HttpRequest httpRep = HttpRequest.post(ServiceUrl.GET_SERVER_DETAILS).body(JSONUtil.bean2json(requestDataAccount)).contentType("application/json");
                ResponseData<ServerDetails> httpResponseData = JSON.parseObject(httpRep.execute().body(), new TypeReference<ResponseData<ServerDetails>>() {
                });
                if (httpResponseData.getCode()!=StatusEnum.SUCCESS.getCode()){
                    throw new GrilException("获取用户类目失败");
                }
                //将二级类目转换为list
                List<String> categoryTwoNos = new ArrayList<>();
                ServerDetails serverDetails = httpResponseData.getData();
                List<UserCategory> userCategories = serverDetails.getUserCategories();*/
                //获取用户审核通过的服务类目
                List<String> categoryTwoNos = new ArrayList<>();
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("userNo",data.get("userNo"));
                stringMap.put("auditState",SysCode.CODE_10070002.getCode());
                ResponseData<List<UserApplyCategory>> applyNoAfterAuditCategoryList = iUserApplyCategoryService.getApplyNoAfterAuditCategoryList(stringMap);
                List<UserApplyCategory> userApplyCategories = applyNoAfterAuditCategoryList.getData();
                //将二级类目转换为list
                userApplyCategories.forEach(userApplyCategory -> {
                    categoryTwoNos.add(userApplyCategory.getCategoryTwoNo());
                });
                /*List<String> citys = new ArrayList<>();
                //查询服务城市(注释原因-取消所在城市筛选)
                List<UserCity> userCitys = serverDetails.getUserCitys();
                userCitys.forEach(userCity -> {
                    citys.add(userCity.getCityNo());
                });
                map.put("citys",citys);*/
                if(categoryTwoNos.size()>0){
                    map.put("categoryTwoNos",categoryTwoNos);
                }
                map.put("serverState",SysCode.CODE_10290005.getCode());
            }
        }else {
            //如果用户没有登录 默认展示分发需求 并且只显示10条数据
            /*map.put("start", 0);
            map.put("end",10);*/
            map.put("serverState",SysCode.CODE_10290005.getCode());
        }
        List<DemandInfo> homeDemandinfo = demandInfoMapper.findHomeDemandinfo(map);
        responseData.setData(homeDemandinfo);
        return responseData;
    }

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updateDemandinfo(DemandInfo demandInfo) {
        ResponseData responseData = new ResponseData();
        //添加服务详情表记录
        DemandServer demandServer = new DemandServer();
        if (demandInfo.getServerState().equals(SysCode.CODE_10290001.getCode())
                || demandInfo.getServerState().equals(SysCode.CODE_10290005.getCode())){
            demandServer.setUserNo(demandInfo.getRobUser());
        }else {
            demandServer.setUserNo(demandInfo.getServiceUser());
        }
        demandServer.setDemandNo(demandInfo.getDemandNo());
        demandServer.setDemandState(demandInfo.getServerState());
        //查询上级编码
        ResponseData<DemandServer> demandServerSuperUserNo = iDemandServerService.getDemandServerSuperUserNo(demandInfo.getDemandNo());
        if (demandServerSuperUserNo.getData()!=null){
            demandServer.setSuperUserNo(demandServerSuperUserNo.getData().getSuperUserNo());
        }else {
            demandServer.setSuperUserNo("null");
        }
        iDemandServerService.addDemandServer(demandServer);

        int i = demandInfoMapper.updateByPrimaryKeySelective(demandInfo);
        if (i<=0){
            throw new GrilException("修改需求信息");
        }
        return responseData;
    }

    /**
     * 抢单
     * @param userNo    用户编码
     * @param demandNo  需求编码
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updateSingleDemandInfo(String userNo,String demandNo) {
        //抢单后需要跳转的详情地址
        String url = "";
        ResponseData responseData = new ResponseData();

        //查询抢单者的用户身份
        ResponseData<UserInfo> userByUserNo = iUserInfoServer.getUserByUserNo(userNo);
        UserInfo userInfo = userByUserNo.getData();
        //查询需求信息
        ResponseData<DemandInfo> info = getDemandInfo(demandNo);
        DemandInfo demandInfo = info.getData();

        if (userInfo.getUserType().equals(SysCode.CODE_10020002.getCode())){
            //如果当前是身份是运营官
            throw new GrilException("运营官无法抢单");
        }else if (userInfo.getUserType().equals(SysCode.CODE_10020001.getCode())){
            //如果当前身份是普通用户
            throw new GrilException("需要完善服务资料");
        }else if (userInfo.getUserType().equals(SysCode.CODE_10020003.getCode())) {
            //如果当前身份是服务机构
            //判断当前用户是否可以抢
            if (!demandInfo.getDemandState().equals(SysCode.CODE_10080001.getCode())) {
                throw new GrilException("已被抢啦!");
            }
            //需求主表设置抢单用户
            demandInfo.setRobUser(userNo);
            //需求主表设置服务状态(已抢单)
            demandInfo.setServerState(SysCode.CODE_10290001.getCode());

        }else if (userInfo.getUserType().equals(SysCode.CODE_10020004.getCode())) {
            //如果当前身份是个体服务者
            //判断当前用户是否可以抢
            if (!demandInfo.getServerState().equals(SysCode.CODE_10290005.getCode())) {
                throw new GrilException("已被抢啦!");
            }

            //判断抢单者是否是默认服务机构
            if (demandInfo.getRobUser().equalsIgnoreCase(SysCode.CODE_10430002.getCode())){
                //如果抢单者是默认服务机构 则无需审核(服务中)
                demandInfo.setServerState(SysCode.CODE_10290002.getCode());
            }else {
                //如果抢单者不是默认服务机构 需要审核(待审核)
                demandInfo.setServerState(SysCode.CODE_10290006.getCode());
            }
            //需求主表设置服务用户
            demandInfo.setServiceUser(userNo);
        }

        demandInfo.setDemandState(ApiTool.getDemandState(demandInfo.getServerState()));
        //修改需求信息
        demandInfo.setChangeDate(new Date());   //修改状态时间
        updateDemandinfo(demandInfo);

        //封装服务通知对象
        ChatServiceMessage chatServiceMessage = new ChatServiceMessage();
        //如果是服务机构
        if (userInfo.getUserType().equals(SysCode.CODE_10020003.getCode())){
            url = SwkConfig.HTML_PATH+ "group-demand.html?demandid=" + demandInfo.getId() + "&demandNo=" + demandInfo.getDemandNo() + "&status=10290001";
            //发送通知给需求者
            chatServiceMessage.setSendName(userInfo.getNikeName());
            chatServiceMessage.setSendText("您的需求已被 "+userInfo.getNikeName()+" 成功抢到!");
            chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
            chatServiceMessage.setUserNo(demandInfo.getUserNo());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10013.getMsg());
            iChatServiceMessageService.addChatServiceMessage(chatServiceMessage);

            //发送通知给抢单者
            //查询需求者信息
            ResponseData<UserInfo> userInfoResponseData = iUserInfoServer.getUserByUserNo(demandInfo.getUserNo());
            UserInfo demandUser = userInfoResponseData.getData();
            chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setSendName(SysCode.SYS_SWK.getMsg());
            chatServiceMessage.setSendText("恭喜您,成功抢到 "+demandUser.getNikeName()+" 的服务单!");
            chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
            chatServiceMessage.setUserNo(userInfo.getUserNo());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10005.getMsg());
            iChatServiceMessageService.addChatServiceMessage(chatServiceMessage, 2);
        }
        //如果是个体服务者
        if (userInfo.getUserType().equals(SysCode.CODE_10020004.getCode())){
            url = SwkConfig.HTML_PATH+"person-demand.html?demandid=" + demandInfo.getId() + "&demandNo=" + demandInfo.getDemandNo() + "&status=10290006";

            //发送通知给服务机构
            chatServiceMessage.setSendName(userInfo.getNikeName());
            chatServiceMessage.setSendText("您派出的服务单已被 "+userInfo.getNikeName()+" 成功抢到!");
            chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
            chatServiceMessage.setUserNo(demandInfo.getRobUser());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10013.getMsg());
            iChatServiceMessageService.addChatServiceMessage(chatServiceMessage);

            //发送通知给抢单者
            //查询需求者信息
            ResponseData<UserInfo> userInfoResponseData = iUserInfoServer.getUserByUserNo(demandInfo.getUserNo());
            UserInfo demandUser = userInfoResponseData.getData();
            chatServiceMessage = new ChatServiceMessage();
            chatServiceMessage.setSendName(userInfo.getNikeName());
            chatServiceMessage.setSendText("恭喜您,成功抢到 "+demandUser.getNikeName()+" 的服务单!");
            chatServiceMessage.setNoticeType(SysCode.CODE_10390001.getCode());
            chatServiceMessage.setUserNo(demandInfo.getUserNo());
            chatServiceMessage.setSendTitle(MsgTitleEnum.CODE_10005.getMsg());
            iChatServiceMessageService.addChatServiceMessage(chatServiceMessage, 2);
        }

        responseData.setData(url);
        return responseData;
    }

    @Override
    public ResponseData getDemandInfo(String demandNo) {
        ResponseData responseData = new ResponseData();
        DemandInfo demandInfoByDemandNo = demandInfoMapper.getDemandInfoByDemandNo(demandNo);
        responseData.setData(demandInfoByDemandNo);
        return responseData;
    }

}
