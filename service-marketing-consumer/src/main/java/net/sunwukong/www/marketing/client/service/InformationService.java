package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.dto.SaveInformationInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:资讯服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/19 14:49
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "marketing-server-1", path = "/information")
public interface InformationService {

    /**
     * 分页获取资讯信息
     * @return
     */
    @RequestMapping(value = "/getPageList",method = RequestMethod.POST)
    ResponseData getPageList(@RequestBody(required = false) RequestData<Map<String, String>> requestData);

    /**
     * 根据资讯编码获取资讯详情
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getDetails",method = RequestMethod.POST)
    ResponseData getDetails(@RequestBody(required = false) RequestData<Map<String, String>> requestData);

    /**
     * 获取url文章内容
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getarticlecontent",method = RequestMethod.POST)
    ResponseData getArticleContent(RequestData<Map<String, String>> requestData);

    /**
     * 发布资讯
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/saveinformation",method = RequestMethod.POST)
    ResponseData saveInformation(RequestData<SaveInformationInput> requestData);

    /**
     * 获取资讯分类
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getinformationclass",method = RequestMethod.POST)
    ResponseData getInformationClass(RequestData<Map<String,String>> requestData);

    /**
     * 删除资讯
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/delinformation",method = RequestMethod.POST)
    ResponseData delInformation(RequestData<Map<String, String>> requestData);

    /**
     * 修改资讯
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/updateinformation",method = RequestMethod.POST)
    ResponseData updateInformation(RequestData<Map<String, String>> requestData);
}
