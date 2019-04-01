package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:需求服务接口服务
 *
 * @author Mick
 * CreateDate 2018/6/13/013 15:17
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "marketing-server-1")
@RequestMapping(value = "/demandserver")
public interface DemandServerService {
    /**
     * 获取我的需求服务列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getDemandServerList",method = RequestMethod.POST)
    ResponseData getDemandServerList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);



    /**
     * 我的已抢到列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getRobbedList",method = RequestMethod.POST)
    ResponseData getRobbedList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * 我的待接单列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getPendingOrderList",method = RequestMethod.POST)
    ResponseData getPendingOrderList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * 我的待审核列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getWaitAuditList",method = RequestMethod.POST)
    ResponseData getWaitAuditList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * 通过用户编码获取服务中的需求列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getInServiceList",method = RequestMethod.POST)
    ResponseData getInServiceList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 通过用户编码获取已完成的需求列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getFinishList",method = RequestMethod.POST)
    ResponseData getFinishList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * 通过用户编码获取已结单的需求列表
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getEndServerList",method = RequestMethod.POST)
    ResponseData getEndServerList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 通过用户编码获取需求服务详情
     * @author kangdong
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getDemandServerDetail",method = RequestMethod.POST)
    ResponseData getDemandServerDetail(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * 开始需求服务
     * 服务机构想自己服务时，点击开始服务按钮，需求和服务状态都处于服务中
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/startServer",method = RequestMethod.POST)
    ResponseData startServer(RequestData<Map<String,String>> requestData);


    /**
     * 审核需求服务
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/auditServer",method = RequestMethod.POST)
    ResponseData auditServer(RequestData<Map<String,String>> requestData);


    /**
     * 分发需求服务
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/distribute",method = RequestMethod.POST)
    ResponseData distribute(RequestData<Map<String,String>> requestData);



    /**
     * 点击确认已经完成服务
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/confirmFinish",method = RequestMethod.POST)
    ResponseData confirmFinish(RequestData<Map<String,String>> requestData);
}
