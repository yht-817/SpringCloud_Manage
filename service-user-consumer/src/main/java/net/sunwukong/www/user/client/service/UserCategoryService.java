package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:用户服务类目服务接口
 *
 * @author Mick
 * @CreateDate 2018/6/23 14:38
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "user-server-1")
@RequestMapping(value = "/usercategory")
public interface UserCategoryService {

    /**
     * 根据编码获取用户服务类目
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getusercategory",method = RequestMethod.POST)
    ResponseData getUserCategory(@RequestBody(required = false) RequestData<Map<String,String>> requestData);
}
