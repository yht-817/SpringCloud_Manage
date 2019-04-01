package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:收藏信息服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/22 12:05
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "marketing-server-1", path = "/collectioninfo")
public interface CollectionInfoService {
    /**
     * 获取用户收藏信息
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getusercollectioninfopage",method = RequestMethod.POST)
    ResponseData getUserCollectionInfoPage(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * 设置用户收藏
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/setusercollection",method = RequestMethod.POST)
    ResponseData setUserCollection(@RequestBody(required = false) RequestData<Map<String,String>> requestData);
}
