package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.DemandInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by tungkang on 2018/6/19
 */
@FeignClient(value = "marketing-server-1", path = "/demandinfo")
public interface DemandInfoService {

    /**
     * 通过用户编码获取的需求列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getDemandList",method = RequestMethod.POST)
    ResponseData getDemandList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 未开始的需求列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getNotStartedList",method = RequestMethod.POST)
    ResponseData getNotStartedList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 服务中的需求列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getInServiceList",method = RequestMethod.POST)
    ResponseData getInServiceList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 待结单的需求列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getWaitEndList",method = RequestMethod.POST)
    ResponseData getWaitEndList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 已待结单的需求列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getFinishList",method = RequestMethod.POST)
    ResponseData getFinishList(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 通过id获取我的需求信息详情
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getDemandInfoDetail",method = RequestMethod.POST)
    ResponseData getDemandInfoDetail(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 发布需求信息
     * @param demandInfo
     * @return
     */
    @RequestMapping(value = "/publishDemandInfo",method = RequestMethod.POST)
    ResponseData publishDemandInfo(@RequestBody(required = false) DemandInfo demandInfo);

    /**
     * 拆销订单
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/cancelDemandInfo",method = RequestMethod.POST)
    ResponseData cancelDemandInfo(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 确认结单
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/confirmFinish",method = RequestMethod.POST)
    ResponseData confirmFinish(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 获取首页列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/gethomedemandinfo",method = RequestMethod.POST)
    ResponseData getHomeDemandinfo(RequestData<Map<String, String>> requestData);

    /**
     * 抢单
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/grabsingledemandinfo",method = RequestMethod.POST)
    ResponseData grabSingleDemandinfo(RequestData<DemandInfo> requestData);
}
