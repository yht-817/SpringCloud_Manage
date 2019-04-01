package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.CityMangerApply;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by tungkang on 2018/6/21.
 */
@FeignClient(value = "marketing-server-1",path = "/citymangerapply")
public interface CityMangerApplyService {
    /**
     * 申请成为城市管理者
     * @param cityMangerApply
     * @return
     */
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    ResponseData apply(@RequestBody(required = false) CityMangerApply cityMangerApply);


    /**
     * 运营官下面的服务机构列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getServiceList",method = RequestMethod.POST)
    ResponseData getServiceList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * 城市运营官待审核的服务机构申请列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getApplyList",method = RequestMethod.POST)
    ResponseData getApplyList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * 城市运营官审核服务机构
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/auditService",method = RequestMethod.POST)
    ResponseData auditService(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 运营官管理界面接口
     * 1.运营下的服务机构的服务需求投诉列表
     * 2.运营官所在城市下未开始的需求列表
     */
    @RequestMapping(value = "/getManagerList",method = RequestMethod.POST)
    ResponseData getManagerList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * 通过申请编码查看待审核的服务机构详情
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getOrganizationDetail",method = RequestMethod.POST)
    ResponseData getOrganizationDetail(@RequestBody(required = false) RequestData<Map<String,String>> requestData);



    /**
     * 运营官查看需求详情
     * 已结单的要关联需求服务表、服务机构表、评价表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getDemandDetail",method = RequestMethod.POST)
    ResponseData getDemandDetail(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * PC审核城市运营官
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/updateAudit",method = RequestMethod.POST)
    ResponseData updateAudit(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 获取未开始的需求列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getnotstartdemandlist",method = RequestMethod.POST)
    ResponseData getNotStartDemandList(RequestData<Map<String, String>> requestData);

    /**
     * 获取未开始需求的详情
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getnotstartdemanddetail",method = RequestMethod.POST)
    ResponseData getNotStartDemandDetail(RequestData<Map<String, String>> requestData);

    /**
     * 运营官派单
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/sendorders",method = RequestMethod.POST)
    ResponseData sendOrders(RequestData<Map<String, String>> requestData);
}
