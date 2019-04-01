package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.api.util.TimeUtil;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.*;
import net.sunwukong.www.marketing.server.dao.*;
import net.sunwukong.www.marketing.server.service.ICityMangerApplyService;
import net.sunwukong.www.marketing.server.web.BaseController;
import net.sunwukong.www.marketing.vo.ApplyListVo;
import net.sunwukong.www.marketing.vo.DemandDetailVo;
import net.sunwukong.www.marketing.vo.ManagerDemandListVo;
import net.sunwukong.www.marketing.vo.ServiceList;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ICityMangerApplyServiceImpl
 * @Author: kangdong
 * @Date: 2018/6/20 下午2:32
 * @Description: 城市管理者申请实现类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@Service
public class ICityMangerApplyServiceImpl extends BaseController implements ICityMangerApplyService {

    private static final Logger log = LoggerFactory.getLogger(ICityMangerApplyServiceImpl.class);

    @Autowired
    CityMangerApplyMapper cityMangerApplyMapper;

    @Autowired
    DemandInfoMapper demandInfoMapper;

    @Autowired
    DemandServerMapper demandServerMapper;

    @Autowired
    DemandEvaluateMapper demandEvaluateMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserApplyMapper userApplyMapper;

    @Autowired
    UserApplyCityMapper userApplyCityMapper;

    @Autowired
    UserApplyCategoryMapper userApplyCategoryMapper;

    @Autowired
    UserApplyPhotoMapper userApplyPhotoMapper;

    @Override
    public ResponseData addCityManagerApply(CityMangerApply cityMangerApply) {
        cityMangerApply.setId(DataBaseTool.createId());
        cityMangerApply.setApplyDate(new Date());
        cityMangerApply.setAuditState(SysCode.CODE_10070001.getCode());
        int i = cityMangerApplyMapper.insert(cityMangerApply);
        if (i<=0){
            throw new GrilException("保存申请失败");
        }
        return BaseResp.getInstance();
    }

    /**
     * 申请成为城市管理者(城市运营官),PC审核
     * @param applyinfo
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData applyCityMangerApply(CityMangerApply applyinfo){
        //判断是否已经申请 并且审核通过
        CityMangerApply cityMangerApply = cityMangerApplyMapper.findByUserNoAndAuditState(applyinfo.getUserNo(),SysCode.CODE_10070002.getCode());
        if(cityMangerApply != null) {
            throw new GrilException("你已是城市管理者");
        }

        //判断用户是否已经申请 并且待审核
        CityMangerApply cityMangerApply1 = cityMangerApplyMapper.findByUserNoAndAuditState(applyinfo.getUserNo(),SysCode.CODE_10070001.getCode());
        if(cityMangerApply1 != null) {
            throw new GrilException("已提交申请，请耐心等待");
        }

        addCityManagerApply(applyinfo);
        return BaseResp.getInstance();
    }


    /**
     * PC审核城市运营官
     * @param data
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updateAudit(Map<String,String> data) {
        ResponseData responseData = new ResponseData();
        try {
            //获取申请用户信息
            CityMangerApply cityMangerApply = cityMangerApplyMapper.getCityMangerApplyByUserNo(data.get("userNo"));
            //判断用户申请信息是否为空
            if (cityMangerApply==null){
                throw new GrilException("该用户暂无审核信息");
            }
            //如果用户申请状态不为"待审核"
            if (!cityMangerApply.getAuditState().equals(SysCode.CODE_10070001.getCode())){
                throw new GrilException("当前状态不需要审核");
            }
            //赋值新的状态
            cityMangerApply.setAuditState(data.get("auditState"));
            cityMangerApply.setAuditDate(new Date());
            //判断是通过还是驳回
            if (cityMangerApply.getAuditState().equals(SysCode.CODE_10070002.getCode())){
                //审核通过(更新用户信息)
                UserInfo userInfo = new UserInfo();
                userInfo.setUserNo(cityMangerApply.getUserNo());
                userInfo.setSexNo(cityMangerApply.getSexNo());
                userInfo.setPhoneNo(cityMangerApply.getPhoneNo());
                userInfo.setUserType(SysCode.CODE_10020002.getCode());
                int i = userInfoMapper.updateByUserNoSelective(userInfo);
                if (i <= 0){
                    throw new GrilException("用户信息更新失败");
                }
            }else if (cityMangerApply.getAuditState().equals(SysCode.CODE_10070003.getCode())){
                //审核驳回(发送通知给用户)
                //  TODO
            }
            //修改申请信息
            cityMangerApplyMapper.updateByPrimaryKeySelective(cityMangerApply);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }



    /**
     * 运营官下面的服务机构列表
     * getServiceAgencyList改为getUserApply
     * @param userNo 运营官编码
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ResponseData getServiceList(String userNo, String pageNo, String pageSize) {
        ResponseData responseData = new ResponseData();
        try {
            List<ServiceList> datalist = new ArrayList<ServiceList>();
            int start = ApiTool.getStartPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
            //1、获取运营官下面的服务机构列表(猴王表)
            //List<UserApply> list = merchanApplyMapper.getMerchanListByUserNoManagerAndState(userNo, start, Integer.parseInt(pageSize), SysCode.CODE_10070002.getCode());
            List<UserApply> list = userApplyMapper.findByUserNoManagerAndAuditState(userNo, start, Integer.parseInt(pageSize), SysCode.CODE_10070002.getCode());
            //2、获取每个服务机构已完成的需求单数
            for (UserApply ua : list) {
                ServiceList vo = new ServiceList();
                //获取该服务机构下的已完成服务订单数
                HashMap<String,Object> map = demandInfoMapper.getCountsByServerNoAndState(ua.getUserNo(), SysCode.CODE_10290004.getCode());
                //List<DemandServer> serverList =  demandServerMapper.getDemandServerListByUserNoAndState(ua.getUserNo(), SysCode.CODE_10290004.getCode());
                if(null != map) {
                    vo.setFinishCount(map.get("counts").toString());//serverCount
                }else {
                    vo.setFinishCount(String.valueOf(0));//serverCount
                }
                UserInfo userInfo = userInfoMapper.getUserByUserNo(ua.getUserNo());
                if(userInfo != null) {
                    vo.setUserId(userInfo.getId());
                    vo.setUserName(userInfo.getNikeName());
                    vo.setUserHead(userInfo.getUserHead());
                    vo.setUserType(userInfo.getUserType());
                    vo.setUserGrade(userInfo.getUserGrade());
                }
                vo.setId(ua.getId());
                vo.setUserNo(ua.getUserNo());

                //服务机构服务星数
                ResponseData userApply = iUserApplyService.getUserStar(ua.getUserNo());
                //ResponseData merchanApply = iMerchanApplyService.getMerchanStar(ua.getUserNo());
                if (userApply.getCode()!= StatusEnum.SUCCESS.getCode()){
                    throw new GrilException(userApply.getMessage());
                }
                vo.setUserStar(userApply.getData().toString());

                datalist.add(vo);
            }

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(datalist);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    /**
     * 运营官管理界面接口
     * 1.运营下的服务机构的服务需求投诉列表
     * 2.运营官所在城市下未开始的需求列表
     */
    @Override
    public ResponseData getManagerList(String userNo, String pageNo, String pageSize) {
        ResponseData responseData = new ResponseData();
        try {
            Integer start = ApiTool.getStartPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
            List<ManagerDemandListVo> mdlist = new ArrayList<ManagerDemandListVo>();

            //获取运营官下的服务机构被投诉的需求服务列表
            List<UserApply> list = userApplyMapper.findByUserNoManagerAndAuditState(userNo, start, Integer.parseInt(pageSize), SysCode.CODE_10070002.getCode());
            for (UserApply ua : list) {
                //通过服务机构编码获取评价为投诉的需求单
                List<DemandEvaluate> evaluates = demandEvaluateMapper.getEvaluateByMerchanNo(ua.getUserNo(), SysCode.CODE_10280002.getCode());
                for (DemandEvaluate evaluate : evaluates) {
                    //通过订单查询需求表
                    DemandInfo demandInfo = demandInfoMapper.getDemandInfoByDemandNo(evaluate.getDemandNo());
                    ManagerDemandListVo vo = setManagerDemandListVo(demandInfo);
                    vo.setStateCode(SysCode.CODE_10280002.getCode());
                    vo.setStateName(SysCode.CODE_10280002.getMsg());
                    mdlist.add(vo);
                }
            }

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(mdlist);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }

    /**
     * 设置返回数据ManagerDemandListVo
     * @param demandInfo
     * @return
     */
    public ManagerDemandListVo setManagerDemandListVo(DemandInfo demandInfo) {
        ManagerDemandListVo vo = new ManagerDemandListVo();
        vo.setId(demandInfo.getId());
        vo.setUserNo(demandInfo.getUserNo());
        vo.setDemandNo(demandInfo.getDemandNo());
        vo.setSpecialRemark(demandInfo.getSpecialRemark());

        //获取用户或服务机构名称
        UserInfo userInfo = userInfoMapper.getUserByUserNo(demandInfo.getUserNo());
        vo.setUserName(userInfo.getNikeName());
        vo.setUserHead(userInfo.getUserHead());
        vo.setUserType(userInfo.getUserType());
        return vo;
    }


    /**
     * 城市运营官待审核的服务机构申请列表
     * @param userNo
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ResponseData getApplyList(String userNo, String pageNo, String pageSize) {
        ResponseData responseData = new ResponseData();
        try {
            List<ApplyListVo> listvo = new ArrayList<ApplyListVo>();
            int start = ApiTool.getStartPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
            //获取待审核的服务机构
            List<UserApply> list = userApplyMapper.findByUserNoManagerAndAuditState(userNo, start, Integer.parseInt(pageSize), SysCode.CODE_10070001.getCode());
            for(UserApply ua : list) {
                ApplyListVo vo = new ApplyListVo();
                vo.setId(ua.getId());
                vo.setApplyNo(ua.getApplyNo());
                vo.setUserNo(ua.getUserNo());
                UserInfo userInfo = userInfoMapper.getUserByUserNo(ua.getUserNo());
                if(null != userInfo) {
                    vo.setUserName(userInfo.getNikeName());
                    vo.setUserHead(userInfo.getUserHead());
                }
                vo.setStateName(SysCode.fromCodeName(ua.getAuditState()).getMsg());
                vo.setDatetime(ua.getApplyDate());
                listvo.add(vo);
            }
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(listvo);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    /**
     * 城市运营官审核服务机构
     * @param id
     * @param auditState
     * @return
     */
    @Override
    public ResponseData updateAuditServiceAgency(String id, String auditState) {
        ResponseData responseData = new ResponseData();
        try {
            UserApply userApply = new UserApply();
            userApply.setId(id);
            userApply.setAuditState(auditState);

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(userApplyMapper.updateByPrimaryKey(userApply));
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }



    /**
     * 通过申请编码查看待审核的服务机构详情
     * @author kangdong
     * @param data
     * @return
     */
    @Override
    public ResponseData getOrganizationDetail(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        try {
            Map<String, Object> map = new HashMap<>();
            UserApply userApply = userApplyMapper.finByApplyNo(data.get("applyNo"));
            if(userApply == null) {
                responseData.setCode(StatusEnum.NOT_FOUND.getCode());
                responseData.setMessage(StatusEnum.NOT_FOUND.getMsg());
                return responseData;
            }
            //查询用户表，获取机构的头像、昵称等
            UserInfo userInfo = userInfoMapper.getUserByUserNo(userApply.getUserNo());
            if(userApply != null) {
                map.put("userName", userInfo.getUserName());
                map.put("userHead", userInfo.getUserHead());
                map.put("userGrade" ,userInfo.getUserGrade());
            }

            //查询用户城市表、获取机构服务城市
            List<UserApplyCity> cityList = userApplyCityMapper.findByApplyNo(data.get("applyNo"));
            map.put("cityList", cityList);
            //查询类别表、获取机构服务类别
            List<UserApplyCategory> categoryList = userApplyCategoryMapper.findByApplyNo(data.get("applyNo"));
            map.put("categoryList", categoryList);
            //查询图片资料表、获取用户资质图片等
            List<UserApplyPhoto> photoList = userApplyPhotoMapper.findByApplyNo(data.get("applyNo"));
            map.put("photoList", photoList);

            map.put("id",userApply.getId());
            map.put("applyNo",userApply.getApplyNo());
            map.put("address",userApply.getAddress());
            map.put("phoneNo",userApply.getPhoneNo());
            map.put("applyDate",userApply.getApplyDate());
            responseData.setData(map);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }

    /**
     * 运营官查看需求详情(未开始、投诉需求详情)
     * 已结单的要关联需求服务表、服务机构表、评价表
     * @param demandNo
     * @param demandState
     * @return
     */
    @Override
    public ResponseData getDemandDetail(String demandNo, String demandState){
        DemandDetailVo vo = new DemandDetailVo();
        ResponseData responseData = new ResponseData();
        try {
            DemandInfo demandInfo = demandInfoMapper.getDemandInfoByDemandNo(demandNo);
            if (null == demandInfo) {
                responseData.setCode(StatusEnum.NOT_FOUND.getCode());
                responseData.setMessage(StatusEnum.NOT_FOUND.getMsg());
                return responseData;
            } else {
                //获取用户或服务机构名称
                vo.setId(demandInfo.getId());
                vo.setDemandNo(demandInfo.getDemandNo());
                vo.setDemandUserNo(demandInfo.getUserNo());
                vo.setCityName(demandInfo.getCityName());
                vo.setSpecialRemark(demandInfo.getSpecialRemark());
                vo.setCategoryOneName(demandInfo.getCategoryOneName());
                vo.setCategoryTwoName(demandInfo.getCategoryTwoName());
                vo.setSubmitDate(demandInfo.getSubmitDate());
                vo.setPayNum(demandInfo.getPayNum());
                vo.setDemandState(demandInfo.getDemandState());

                //需求者信息
                UserInfo userInfo = userInfoMapper.getUserByUserNo(demandInfo.getUserNo());
                vo.setDemandUserHead(userInfo.getUserHead());
                vo.setDemandUserName(userInfo.getUserName());
                vo.setDemandUserNikeName(userInfo.getNikeName());

                //如果是未开始的需求订单，直接返回需求信息详情
                if(demandState.equals(SysCode.CODE_10080001.getCode())) {
                    responseData.setData(vo);
                    return responseData;
                }else {//投诉中
                    //评价信息
                    DemandEvaluate demandEvaluate = demandEvaluateMapper.getEvaluateByDemandNo(demandInfo.getDemandNo());
                    if (null != demandEvaluate) {
                        vo.setPresonStar(demandEvaluate.getPresonStar());
                        vo.setQualityStat(demandEvaluate.getQualityStat());
                        vo.setTimeStar(demandEvaluate.getTimeStar());
                        vo.setComplainState(demandEvaluate.getComplainState());
                        vo.setEvaluateRemark(demandEvaluate.getEvaluateRemark());
                        vo.setEvaluateDate(demandEvaluate.getEvaluateDate());
                    }

                    //服务机构信息
                    vo.setServerState(demandInfo.getServerState());

                    //服务机构信息
                    UserInfo serverInfo = userInfoMapper.getUserByUserNo(demandInfo.getRobUser());
                    vo.setServerUserNo(demandInfo.getRobUser());
                    vo.setServerUserHead(serverInfo.getUserHead());
                    vo.setServerUserName(serverInfo.getUserName());
                    vo.setServerUserNikeName(serverInfo.getNikeName());

                    //获取服务机构
                    UserApply userApply = userApplyMapper.findByUserNoAndAuditState(demandInfo.getRobUser(),SysCode.CODE_10070002.getCode());
                    if (userApply!=null){
                        vo.setAddress(userApply.getAddress());
                        vo.setPhoneNo(userApply.getPhoneNo());
                    }

                }
            }
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(vo);
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }

    /**
     * 获取城市运营下面所有未开始的需求列表
     * @param userNo    城市运营官编码
     * @param start     开始位置
     * @param size      页面长度
     * @return
     */
    @Override
    public ResponseData getNotStartDemandList(String userNo, int start, int size) {
        CityMangerApply cityMangerApplyByUserNo = cityMangerApplyMapper.getCityMangerApplyByUserNo(userNo);
        List<Map<String, Object>> byCityNoAndDemandState = demandInfoMapper.findByCityNoAndDemandState(cityMangerApplyByUserNo.getCityNo(), SysCode.CODE_10080001.getCode(),start,size);
        return BaseResp.getInstance(byCityNoAndDemandState);
    }

    /**
     * 获取未开始需求详情
     * @param demandNo  需求编码
     * @return
     */
    @Override
    public ResponseData getNotStartDemandDetail(String demandNo) {
        Map<String, Object> notStartDemandDetail = demandInfoMapper.getNotStartDemandDetail(demandNo);
        notStartDemandDetail.put("submitDate", TimeUtil.getChatTimeStr(((Date)notStartDemandDetail.get("submitDate")).getTime()));
        return BaseResp.getInstance(notStartDemandDetail);
    }

    /**
     * 运营官派单
     * @param demandNo  需求编码
     * @param userNo    运营官编码
     * @return
     */
    @Override
    public ResponseData getSendOrders(String demandNo, String userNo) {
        //查询运营官下面所有的服务机构
        List<UserApply> byUserNoMangerAndAuditState = iUserApplyService.findByUserNoManagerAndAuditState(userNo, SysCode.CODE_10070002.getCode());
        if (byUserNoMangerAndAuditState.size()<=0){
            throw new GrilException("没有可以派单的服务机构！");
        }
        //获取即将分发的服务机构编码
        String serverUserNo = byUserNoMangerAndAuditState.get(0).getUserNo();
        //将需求派给指定的服务机构
        iDemandInfoService.updateSingleDemandInfo(serverUserNo,demandNo);

        //服务机构自动派单
        //iDemandServerService.insertDistributeServer(serverUserNo,demandNo);
        return BaseResp.getInstance();
    }
}



