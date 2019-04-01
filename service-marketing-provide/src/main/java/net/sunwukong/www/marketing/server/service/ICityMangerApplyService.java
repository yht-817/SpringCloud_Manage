package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.CityMangerApply;

import java.util.Map;

/**
 * Created by tungkang on 2018/6/16.
 */
public interface ICityMangerApplyService {

    /**
     * 保存数据
     * @param cityMangerApply
     * @return
     */
    ResponseData addCityManagerApply(CityMangerApply cityMangerApply);

    /**
     * 申请成为城市管理者
     * @param cityMangerApply
     * @return
     */
    ResponseData applyCityMangerApply(CityMangerApply cityMangerApply);


    /**
     * PC审核城市运营官
     * @param data
     * @return
     */
    ResponseData updateAudit(Map<String,String> data);


    /**
     * 运营官下面的服务机构列表
     * @param userNo
     * @param pageNo
     * @param pageSize
     * @return
     */
    ResponseData getServiceList(String userNo, String pageNo, String pageSize);


    /**
     * 城市运营官待审核的服务机构申请列表
     * @param userNo
     * @param pageNo
     * @param pageSize
     * @return
     */
    ResponseData getApplyList(String userNo, String pageNo, String pageSize);


    /**
     * 城市运营官审核服务机构
     * @param id
     * @param auditState
     * @return
     */
    ResponseData updateAuditServiceAgency(String id, String auditState);


    /**
     * 管理接口
     * 1.运营下的服务机构的服务需求投诉列表
     * 2.运营官所在城市下未开始的需求列表
     * @param userNo
     * @param pageNo
     * @param pageSize
     * @return
     */
    ResponseData getManagerList(String userNo, String pageNo, String pageSize);


    /**
     * 通过申请编码查看待审核的服务机构详情
     * @author kangdong
     * @param data
     * @return
     */
    ResponseData getOrganizationDetail(Map<String, String> data);


    /**
     * 运营官查看需求详情
     * 已结单的要关联需求服务表、服务机构表、评价表
     * @param demandNo
     * @param demandState
     * @return
     */
    ResponseData getDemandDetail(String demandNo, String demandState);

    /**
     * 获取城市运营下面所有未开始的需求列表
     * @param userNo    城市运营官编码
     * @param start     开始位置
     * @param pageSize  页面长度
     * @return
     */
    ResponseData getNotStartDemandList(String userNo, int start, int pageSize);

    /**
     * 获取未开始的需求详情
     * @param demandNo  需求编码
     * @return
     */
    ResponseData getNotStartDemandDetail(String demandNo);

    /**
     * 运营官派单
     * @param demandNo  需求编码
     * @param userNo    运营官编码
     * @return
     */
    ResponseData getSendOrders(String demandNo, String userNo);
}
