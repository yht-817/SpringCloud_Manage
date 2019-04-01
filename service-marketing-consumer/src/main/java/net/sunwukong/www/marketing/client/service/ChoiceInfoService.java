package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/12.
 */
@FeignClient(value = "marketing-server-1", path = "/choiceinfo")
public interface ChoiceInfoService {


    /**
     * 首页顶部精选信息
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getTopList", method = RequestMethod.POST)
    ResponseData getTopList(@RequestBody(required = false) RequestData<Map<String, String>> requestData);

    /**
     * 中间banner列表
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getMiddleList", method = RequestMethod.POST)
    ResponseData getMiddleList(@RequestBody(required = false) RequestData<Map<String, String>> requestData);


    /**
     * 首页底部精选分页列表信息
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getBottomList", method = RequestMethod.POST)
    ResponseData getBottomList(@RequestBody(required = false) RequestData<Map<String, String>> requestData);


    /**
     * 查看详情
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    ResponseData getDetail(@RequestBody(required = false) RequestData<Map<String, String>> requestData);


    /**
     * 点击添加或取消收藏精选
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/addOrCancel", method = RequestMethod.POST)
    ResponseData addOrCancel(@RequestBody(required = false) RequestData<Map<String, String>> requestData);

    /**
     * 下单（未支付）
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/addBuy", method = RequestMethod.POST)
    ResponseData addBuy(RequestData<Map<String, String>> requestData);

    // 分享精选
    @RequestMapping(value = "/share", method = RequestMethod.POST)
    ResponseData addShare(RequestData<Map<String, String>> requestData);

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    ResponseData cancelOrderS(RequestData<Map<String, String>> requestData);
}
