package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.DemandServer;
import net.sunwukong.www.marketing.server.domain.DemandParamPo;

import java.util.Map;

/**
 * 说明:需求服务信息服务接口
 *
 * @author Mick
 * CreateDate 2018/6/13/013 14:44
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IDemandServerService {
    /**
     * 获取我的需求服务列表
     * @param demandParamPo
     * @return
     */
    ResponseData getDemandServerList(DemandParamPo demandParamPo);


    /**
     * 我的已抢单列表
     * @param demandParamPo
     * @return
     */
    ResponseData getRobbedList(DemandParamPo demandParamPo);

    /**
     * 我的待接单列表
     * @param demandParamPo
     * @return
     */
    ResponseData getPendingOrderList(DemandParamPo demandParamPo);


    /**
     * 我的待审核的列表
     * @param demandParamPo
     * @return
     */
    ResponseData getWaitAuditList(DemandParamPo demandParamPo);


    /**
     * 我的服务中的列表
     * @param demandParamPo
     * @return
     */
    ResponseData getInServiceList(DemandParamPo demandParamPo);

    /**
     * 我的已完成的列表
     * @param demandParamPo
     * @return
     */
    ResponseData getFinishList(DemandParamPo demandParamPo);


    /**
     * 我的已结单的列表
     * @param demandParamPo
     * @return
     */
    ResponseData getEndServerList(DemandParamPo demandParamPo);

    /**
     * 通过用户编码获取需求服务详情
     * @param id
     * @return
     */
    ResponseData getDemandServerDetail(String id);

    /**
     * 系统派单接口(24小时未接单的需求)
     * @return
     */
    ResponseData insertSysAllot();

    /**
     * 添加服务详情
     * @param demandServer
     * @return
     */
    ResponseData addDemandServer(DemandServer demandServer);

    /**
     * 根据需求编码获取上级用户编码
     * @param demandNo
     * @return
     */
    ResponseData getDemandServerSuperUserNo(String demandNo);


    /**
     * 开始需求服务
     * 服务机构想自己服务时，点击开始服务按钮，需求和服务状态都处于服务中
     * @param data
     * @return
     */
    ResponseData updateStartServer(Map<String,String> data);


    /**
     * 审核需求服务
     * @param data
     * @return
     */
    ResponseData updateAuditServer(Map<String,String> data);

    /**
     * 分发需求
     * @param userNo    用户编码
     * @param demandNo  需求编码
     * @return
     */
    ResponseData insertDistributeServer(String userNo,String demandNo);


    /**
     * 点击确认已经完成服务
     * @param data
     * @return
     */
    ResponseData updateConfirmFinish(Map<String,String> data);
}
