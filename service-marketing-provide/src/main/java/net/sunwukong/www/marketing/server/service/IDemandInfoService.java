package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.DemandInfo;
import net.sunwukong.www.marketing.server.domain.DemandParamPo;

import java.util.Map;

/**
 * Created by tungkang on 2018/6/16.
 */
public interface IDemandInfoService {

    /**
     * 通过用户编码获取的需求列表
     * @param demandParamPo 请求参数
     * @return
     */
    ResponseData getDemandList(DemandParamPo demandParamPo);

    /**
     * 未开始的需求列表
     * @param demandParamPo 请求参数
     * @return ResponseData
     */
    ResponseData getNotStartedList(DemandParamPo demandParamPo);

    /**
     * 在服务中的需求列表
     * @param demandParamPo 请求参数
     * @return ResponseData
     */
    ResponseData getInServiceList(DemandParamPo demandParamPo);

    /**
     * 待结单的需求列表
     * @param demandParamPo 请求参数
     * @return ResponseData
     */
    ResponseData getWaitEndList(DemandParamPo demandParamPo);

    /**
     * 已待结单的需求列表
     * @param demandParamPo 请求参数
     * @return ResponseData
     */
    ResponseData getFinishList(DemandParamPo demandParamPo);

    /**
     * 通过id获取我的需求信息详情
     * @param id
     * @return
     */
    ResponseData getDemandInfoDetail(String id);

    /**
     * 发布需求信息
     * @param demandInfo 需求信息
     * @return
     */
    ResponseData insertDemandInfo(DemandInfo demandInfo);

    /**
     * 撤销需求
     * @param id        需求ID
     * @param userNo    用户编码
     * @return
     */
    ResponseData updateCancelDemandInfo(String id,String userNo);

    /**
     * 确认结单
     * @param demandNo  需求编码
     * @param userNo    用户编码
     * @return
     */
    ResponseData updateConfirmFinish(String demandNo,String userNo);

    /**
     * 获取首页需求列表
     * @param data
     * @return
     */
    ResponseData getHomeDemandinfo(Map<String, String> data);

    /**
     * 修改需求状态
     * @param data
     * @return
     */
    ResponseData updateDemandinfo(DemandInfo data);

    /**
     * 抢单
     * @param userNo    用户编码
     * @param demandNo  需求编码
     * @return
     */
    ResponseData updateSingleDemandInfo(String userNo,String demandNo);

    /**
     * 根据需求编号获取需求信息
     * @param demandNo
     * @return
     */
    ResponseData getDemandInfo(String demandNo);
}
