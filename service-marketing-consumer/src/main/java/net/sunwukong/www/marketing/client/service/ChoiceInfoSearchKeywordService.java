package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tungkang on 2018/7/23.
 */
@FeignClient(value = "marketing-server-1",path = "/choiceinfosearchkeyword")
public interface ChoiceInfoSearchKeywordService {

    /**
     * 热门关键词列表
     * @return
     */
    @RequestMapping(value = "/getHotList",method = RequestMethod.POST)
    ResponseData getHotList();
}
