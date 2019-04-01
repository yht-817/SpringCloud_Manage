package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.TimeUtil;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.DemandInfo;
import net.sunwukong.www.marketing.bean.DemandServer;
import net.sunwukong.www.marketing.bean.UserApply;
import net.sunwukong.www.marketing.bean.UserInfo;
import net.sunwukong.www.marketing.server.dao.DemandInfoMapper;
import net.sunwukong.www.marketing.server.dao.DemandServerMapper;
import net.sunwukong.www.marketing.server.dao.UserApplyMapper;
import net.sunwukong.www.marketing.server.dao.UserInfoMapper;
import net.sunwukong.www.marketing.server.domain.DemandParamPo;
import net.sunwukong.www.marketing.server.service.IDemandServerService;
import net.sunwukong.www.marketing.server.web.BaseController;
import net.sunwukong.www.marketing.vo.MyDemandDetailVo;
import net.sunwukong.www.marketing.vo.MyDemandServerListVo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 说明:需求服务信息服务接口实现
 *
 * @author Mick 、kangdong
 * CreateDate 2018/6/13/013 14:46
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IDemandServerServiceImpl extends BaseController implements IDemandServerService {

    private static final Logger log = LoggerFactory.getLogger(IDemandServerServiceImpl.class);

    @Autowired
    DemandServerMapper demandServerMapper;

    @Autowired
    DemandInfoMapper demandInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserApplyMapper userApplyMapper;

    /**
     * 我的需求服务列表
     * @author kangdong
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getDemandServerList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {
            demandParamPo.setState("");
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
     * 已抢单列表,10290001
     * @author kangdong
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getRobbedList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {

            demandParamPo.setState(SysCode.CODE_10290001.getCode());
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
     * 我的待接单列表、10290005
     * 服务机构抢单、然后分发后
     * @author kangdong
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getPendingOrderList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {
            demandParamPo.setState(SysCode.CODE_10290005.getCode());
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
     * 待审核列表,10290006
     * @author kangdong
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getWaitAuditList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {
            demandParamPo.setState(SysCode.CODE_10290006.getCode());
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
     * 我的服务中的需求列表,10290002
     * @author kangdong
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getInServiceList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {
            demandParamPo.setState(SysCode.CODE_10290002.getCode());
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
     * 我的已完成的列表、10290003
     * 已完成待需求方结单
     * @author kangdong
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getFinishList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {
            demandParamPo.setState(SysCode.CODE_10290003.getCode());
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
     * 我的已结单的列表,10290004
     * 需求方结单后。服务已完成
     * @author kangdong
     * @param demandParamPo
     * @return
     */
    @Override
    public ResponseData getEndServerList(DemandParamPo demandParamPo) {
        ResponseData responseData = new ResponseData();
        try {
            demandParamPo.setState(SysCode.CODE_10290004.getCode());
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
        //查询需求主表
        List<DemandInfo> list = demandInfoMapper.getDemandListByUserNoAndServerState(demandParamPo);
        List<MyDemandServerListVo> voList = new ArrayList();
        for(DemandInfo server : list) {
//            String userNo = server.getRobUser();
//            //如果服务状态是"已抢到"，则取需求方id，其他情况显示服务方id
//            if(SysCode.CODE_10290001.getCode().equals(server.getServerState())) {
//                userNo = server.getUserNo();
//            }
//            if(null != server.getServiceUser()) {
//                userNo = server.getServiceUser();
//            }
            //UserInfo userInfo = userInfoMapper.getUserByUserNo(userNo);

            MyDemandServerListVo vo = new MyDemandServerListVo();
            vo.setId(server.getId());
            //vo.setUserId(userInfo.getId());
            vo.setDemandNo(server.getDemandNo());
            vo.setSpecialRemark(server.getSpecialRemark());
            vo.setDateTime(TimeUtil.getChatTimeStr(server.getSubmitDate().getTime()));
            vo.setStateCode(server.getServerState());
            vo.setStateName(SysCode.fromCodeName(server.getServerState()).getMsg());
            voList.add(vo);
        }
        return voList;
    }



    /**
     * 通过id获取我的需求服务详情
     * @author kangdong
     * @param id id标识
     * @return
     */
    @Override
    public ResponseData getDemandServerDetail(String id) {
        ResponseData responseData = new ResponseData();
        try {
            DemandInfo demandInfo = demandInfoMapper.selectByPrimaryKey(id);
            if (demandInfo==null){
                throw new GrilException("当前需求不存在");
            }


            if(demandInfo != null) {
                MyDemandDetailVo vo = new MyDemandDetailVo();
                //设置需求信息
                vo.setId(id);
                vo.setDemandNo(demandInfo.getDemandNo());
                vo.setSpecialRemark(demandInfo.getSpecialRemark());
                vo.setPayNum(demandInfo.getPayNum());
                //vo.setDemandState(demandInfo.getDemandState());
                vo.setCityName(demandInfo.getCityName());
                vo.setCategoryOneName(demandInfo.getCategoryOneName());
                vo.setCategoryTwoName(demandInfo.getCategoryTwoName());
                vo.setSubmitDate(TimeUtil.getChatTimeStr(demandInfo.getSubmitDate().getTime()));
                vo.setPhoneNo(demandInfo.getPhoneNo());
                if (demandInfo.getServiceUser()!=null && demandInfo.getServiceUser().equals(demandInfo.getRobUser())){
                    //服务机构自己服务的情况下（所有的顶部详情信息全部显示需求者的信息）
                    setUserInfo(vo, demandInfo, demandInfo.getUserNo());
                }else {
                    if (demandInfo.getServerState().equals(SysCode.CODE_10290001.getCode())){
                        //已抢单
                        setUserInfo(vo, demandInfo, demandInfo.getUserNo());
                    }else if (demandInfo.getServerState().equals(SysCode.CODE_10290006.getCode())){
                        //待接单
                        setUserInfo(vo, demandInfo, demandInfo.getUserNo());
                    }else if (demandInfo.getServerState().equals(SysCode.CODE_10290005.getCode())){
                        //待审核
                        setUserInfo(vo, demandInfo, demandInfo.getServiceUser());
                    }else if (demandInfo.getServerState().equals(SysCode.CODE_10290002.getCode())){
                        //服务中
                        setUserInfo(vo, demandInfo, demandInfo.getServiceUser());
                    }else if (demandInfo.getServerState().equals(SysCode.CODE_10290003.getCode())){
                        //已完成
                        setUserInfo(vo, demandInfo, demandInfo.getServiceUser());
                    }else if (demandInfo.getServerState().equals(SysCode.CODE_10290004.getCode())){
                        //已结单
                        setUserInfo(vo, demandInfo, demandInfo.getServiceUser());
                    }
                }

                //如果服务机构自己服务（服务方是服务机构本人）
                /*if(demandInfo.getServiceUser()!=null && demandInfo.getServiceUser().equals(demandInfo.getRobUser())) {//机构自己服务，显示需求方信息
                    setUserInfo(vo, demandInfo, demandInfo.getUserNo());
                }else {//显示服务方或需求方信息
                    if(null == demandInfo.getServerState()) {//已抢单
                        setUserInfo(vo, demandInfo, demandInfo.getUserNo());

                    }else if(demandInfo.getServerState().equals(SysCode.CODE_10290001.getCode())) {//已抢单
                        setUserInfo(vo, demandInfo, demandInfo.getUserNo());

                    }else if(demandInfo.getServerState().equals(SysCode.CODE_10290005.getCode())) {//待接单
                        setUserInfo(vo, demandInfo, demandInfo.getUserNo());

                    }else {
                        setUserInfo(vo, demandInfo, demandInfo.getServiceUser());
                    }
                }*/
                //设置服务信息
                vo.setServerState(demandInfo.getServerState());
                vo.setServerDate(TimeUtil.getChatTimeStr(demandInfo.getChangeDate().getTime()));
                //如果查看详情的当前用户是服务机构，且服务状态处于分发中。服务机构服务详情页面是没有“我已完成服务按钮”
                String serviceUser = demandInfo.getServiceUser() != null ? demandInfo.getServiceUser() : null;
                String robUser = demandInfo.getRobUser() != null ? demandInfo.getRobUser() : null;

                if(!Objects.equals(serviceUser,robUser) && demandInfo.getServerState().equals(SysCode.CODE_10290002.getCode())) {
                    vo.setShowButton(false);
                }

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
     * 封装用户信息
     * @param vo
     * @param userNo
     * @return
     */
    public MyDemandDetailVo setUserInfo(MyDemandDetailVo vo, DemandInfo demandInfo, String userNo) {

        UserInfo userInfo = userInfoMapper.getUserByUserNo(userNo);
        vo.setUserId(userInfo != null ? userInfo.getId() : "");
        vo.setUserNo(userInfo != null ? userInfo.getUserNo() : "");
        vo.setUserHead(userInfo != null ? userInfo.getUserHead() : "");
        vo.setUserName(userInfo != null ? userInfo.getUserName() : "");
        vo.setUserNikeName(userInfo != null ? userInfo.getNikeName() : "");
        //vo.setDemandAddress();
        if(userInfo.getUserType().equals(SysCode.CODE_10020003.getCode()) || userInfo.getUserType().equals(SysCode.CODE_10020004.getCode())) {
            UserApply userApply =  userApplyMapper.findByUserNoAndAuditState(demandInfo.getServiceUser(),SysCode.CODE_10070002.getCode());
            if(userApply!=null) {
                vo.setPhoneNo(userApply.getPhoneNo());
            }
        }

        return vo;
    }


    /**
     * 开始需求服务
     * 服务机构想自己服务时，点击开始服务按钮，需求和服务状态都处于服务中
     * @param data
     * @return
     */
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData updateStartServer(Map<String,String> data) {
        ResponseData responseData = new ResponseData();
        try {
            DemandInfo demandInfo = demandInfoMapper.getDemandInfoByDemandNo(data.get("demandNo"));
            //服务表信息
            DemandServer demandServer = new DemandServer();
            demandServer.setId(DataBaseTool.createId());
            demandServer.setUserNo(data.get("userNo"));
            demandServer.setSuperUserNo(demandInfo.getRobUser());
            demandServer.setDemandNo(data.get("demandNo"));
            demandServer.setChangeDate(new Date());
            //需求表信息
            //DemandInfo demandInfo = new DemandInfo();
            demandInfo.setDemandNo(data.get("demandNo"));
            //服务状态为已抢单 或 待接单 状态下，且操作用户为服务机构，才可以开始服务
            if (demandInfo.getServerState().equals(SysCode.CODE_10290001.getCode())
                    || demandInfo.getServerState().equals(SysCode.CODE_10290005.getCode())) {
                demandServer.setDemandState(SysCode.CODE_10080002.getCode());

                //更新需求表的服务信息
                demandInfo.setServiceUser(data.get("userNo"));
                demandInfo.setDemandState(SysCode.CODE_10080002.getCode());
                demandInfo.setServerState(SysCode.CODE_10290002.getCode());
                demandInfoMapper.updateByDemandNoSelective(demandInfo);

                responseData.setCode(StatusEnum.SUCCESS.getCode());
                responseData.setMessage(StatusEnum.SUCCESS.getMsg());
                responseData.setData(demandServerMapper.insertSelective(demandServer));
            } else {
                responseData.setCode(StatusEnum.FAIL.getCode());
                responseData.setMessage(StatusEnum.FAIL.getMsg());
                responseData.setData("服务机构在已抢单状态下才能开始服务");
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
     * 审核需求服务
     * 审核通过，新增服务表信息，更新需求表的服务状态
     * 审核不通过，新增服务表信息，更新需求表的服务用户和服务状态
     * @param data
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData updateAuditServer(Map<String,String> data) {
        ResponseData responseData = new ResponseData();
        try {
            //服务表信息
            DemandServer demandServer = new DemandServer();
            demandServer.setId(DataBaseTool.createId());
            demandServer.setUserNo(data.get("userNo"));
            demandServer.setSuperUserNo(data.get("userNo"));
            demandServer.setDemandNo(data.get("demandNo"));
            demandServer.setChangeDate(new Date());
            //需求表信息
            DemandInfo demandInfo = new DemandInfo();
            demandInfo.setDemandNo(data.get("demandNo"));
            //审核
            if(data.get("auditState").equals(SysCode.CODE_10070002.getCode())) {//服务中
                demandServer.setDemandState(SysCode.CODE_10290002.getCode());
                //更新需求表的服务信息
                demandInfo.setServerState(SysCode.CODE_10290002.getCode());
                demandInfo.setDemandState(SysCode.CODE_10080002.getCode());
            }else {//不通过,待接单状态
                //demandInfo.setServiceUser("");
                demandServer.setDemandState(SysCode.CODE_10290005.getCode());
                demandInfo.setServerState(SysCode.CODE_10290005.getCode());
            }
            demandInfoMapper.updateByDemandNoSelective(demandInfo);

            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(demandServerMapper.insertSelective(demandServer));
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    /**
     * 分发需求
     * @param userNo    用户编码
     * @param demandNo  需求编码
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData insertDistributeServer(String userNo,String demandNo) {
        ResponseData responseData = new ResponseData();
        try {
            //更改需求表的服务状态
            DemandInfo demandInfo = new DemandInfo();
            demandInfo.setDemandNo(demandNo);
            demandInfo.setServerState(SysCode.CODE_10290005.getCode());
            demandInfo.setDemandState(SysCode.CODE_10080006.getCode());

            int count = demandInfoMapper.updateByDemandNoSelective(demandInfo);
            if(count > 0) {
                DemandServer demandServer = new DemandServer();
                demandServer.setId(DataBaseTool.createId());
                demandServer.setUserNo(userNo);
                demandServer.setSuperUserNo(userNo);
                demandServer.setDemandNo(demandNo);
                demandServer.setChangeDate(new Date());
                demandServer.setDemandState(SysCode.CODE_10290005.getCode());

                responseData.setCode(StatusEnum.SUCCESS.getCode());
                responseData.setMessage(StatusEnum.SUCCESS.getMsg());
                responseData.setData(demandServerMapper.insertSelective(demandServer));
            }else {
                responseData.setCode(StatusEnum.FAIL.getCode());
                responseData.setMessage(StatusEnum.FAIL.getMsg());
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
     * 点击确认已经完成服务
     * @param data
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData updateConfirmFinish(Map<String,String> data) {
        ResponseData responseData = new ResponseData();
        try {
            DemandInfo demandInfo = demandInfoMapper.getDemandInfoByDemandNo(data.get("demandNo"));
            //判断是本人的服务单,且处于服务中状态
            if(!Objects.equals(null,demandInfo)
                    && demandInfo.getServiceUser().equals(data.get("userNo"))
                    && demandInfo.getServerState().equals(SysCode.CODE_10290002.getCode())) {
                DemandServer server = new DemandServer();
                server.setId(DataBaseTool.createId());
                server.setDemandNo(data.get("demandNo"));
                server.setDemandState(SysCode.CODE_10290003.getCode());
                server.setSuperUserNo(demandInfo.getRobUser());
                server.setUserNo(data.get("userNo"));
                server.setChangeDate(new Date());
                int i = demandServerMapper.insert(server);
                if (i<=0){
                    throw new GrilException("点击确认完成服务失败");
                }

                demandInfo.setDemandState(SysCode.CODE_10080003.getCode());//完成服务待接单
                demandInfo.setServerState(SysCode.CODE_10290003.getCode());

                responseData.setCode(StatusEnum.SUCCESS.getCode());
                responseData.setMessage(StatusEnum.SUCCESS.getMsg());
                responseData.setData(demandInfoMapper.updateByPrimaryKeySelective(demandInfo));
            }else {
                responseData.setCode(StatusEnum.NOT_FOUND.getCode());
                responseData.setMessage(StatusEnum.NOT_FOUND.getMsg());
                responseData.setData(false);
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
     * 系统派单接口(24小时没人接单时，系统自动派单)，这属于定时器任务方法
     * 1.获取需要分派的需求单号(获取该需求单号)
     * 2.系统自动派单给系统的默认服务机构
     * 新增服务表纪录，先新增一条已抢单纪录、再新增一条分发后待接单纪录
     * 更新需求表的需求状态和服务状态及服务用户等信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData insertSysAllot() {
        ResponseData responseData = new ResponseData();
        try {
            //1.获取24小时内无人接单的需求单,SELECT * FROM demand_info WHERE DATEDIFF(submit_date,NOW()) > 24 AND demand_state = '10080001'
            List<DemandInfo> list = demandInfoMapper.getNotStartedDemandListByTimeLength(24);
            if(list.size()==0) {
                log.info("========暂无需要自动派单的需求信息========");
                responseData.setCode(StatusEnum.NOT_FOUND.getCode());
                responseData.setMessage(StatusEnum.NOT_FOUND.getMsg());
                return responseData;
            }
            for (DemandInfo demandInfo : list) {
                //新增服务表纪录(已抢单)
                DemandServer demandServer = new DemandServer();
                demandServer.setId(DataBaseTool.createId());
                demandServer.setDemandNo(demandInfo.getDemandNo());
                demandServer.setDemandState(SysCode.CODE_10290001.getCode());
                demandServer.setUserNo(SysCode.SYS_SERVICE.getCode());
                demandServer.setSuperUserNo(SysCode.SYS_SERVICE.getCode());
                demandServer.setChangeDate(new Date());
                demandServerMapper.insertSelective(demandServer);

                //新增服务表纪录(分发后待接单)
                demandServer.setId(DataBaseTool.createId());
                demandServer.setDemandState(SysCode.CODE_10290005.getCode());
                demandServer.setChangeDate(new Date());
                demandServerMapper.insertSelective(demandServer);

                //更新需求表
                demandInfo.setDemandState(SysCode.CODE_10080006.getCode());
                demandInfo.setServerState(SysCode.CODE_10290005.getCode());
                demandInfo.setRobUser(SysCode.SYS_SERVICE.getCode());
                demandInfo.setServiceUser(SysCode.SYS_SERVICE.getCode());
                demandInfo.setChangeDate(new Date());
                demandInfoMapper.updateByDemandNoSelective(demandInfo);
                log.info("需求单号:"+demandInfo.getDemandNo()+"派单成功!");
            }
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
        }catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }


    @Override
    public ResponseData addDemandServer(DemandServer demandServer) {
        ResponseData responseData = new ResponseData();
        demandServer.setId(DataBaseTool.createId());
        demandServer.setChangeDate(new Date());
        int i = demandServerMapper.insert(demandServer);
        if (i<=0){
            throw new GrilException("添加服务详情失败");
        }
        return responseData;
    }

    @Override
    public ResponseData getDemandServerSuperUserNo(String demandNo) {
        ResponseData responseData = new ResponseData();
        DemandServer byDemandNoOrderByDate = demandServerMapper.findByDemandNoOrderByDate(demandNo);
        responseData.setData(byDemandNoOrderByDate);
        return responseData;
    }
}
