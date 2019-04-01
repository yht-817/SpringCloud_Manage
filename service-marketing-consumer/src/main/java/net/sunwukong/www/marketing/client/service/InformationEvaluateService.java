package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.InformationReply;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by tungkang on 2018/7/25.
 */
@FeignClient(value = "marketing-server-1", path = "/informationevaluate")
public interface InformationEvaluateService {

    /**
     * 分页获取资讯评论列表
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/evaluateList", method = RequestMethod.POST)
    ResponseData evaluateList(@RequestBody(required = false) RequestData<Map<String, String>> requestData);


    /**
     * 获取一条评论
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getOneEvaluate", method = RequestMethod.POST)
    ResponseData getOneEvaluate(@RequestBody(required = false) RequestData<Map<String, String>> requestData);


    /**
     * 评论或回复
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/evaluateOrReply", method = RequestMethod.POST)
    ResponseData evaluateOrReply(@RequestBody(required = false) RequestData<Map<String, String>> requestData);


    /**
     * 点赞和取消点赞
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    ResponseData addOrCancel(RequestData<Map<String, String>> requestData);

    /**
     * 获取子评论的评论
     * @param data
     */
    @RequestMapping(value = "/getchildcomments", method = RequestMethod.POST)
    ResponseData getChildComments(RequestData<Map<String, String>> data);

    /**
     * 添加评论回复
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/addcommentsreply", method = RequestMethod.POST)
    ResponseData addCommentsReply(RequestData<InformationReply> requestData);
}
