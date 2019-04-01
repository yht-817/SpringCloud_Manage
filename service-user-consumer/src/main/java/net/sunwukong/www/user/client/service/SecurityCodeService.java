package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:验证码服务接口
 *
 * @author Mick
 * CreateDate 2018/6/10/010 9:18
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "user-server-1")
@RequestMapping(value = "/securitycode")
public interface SecurityCodeService {

    /**
     * 获取验证码
     * @param account       用户账号
     * @param accountType   账户类型 1：电话 2：邮箱
     * @return              返回响应对象
     */
    @RequestMapping(value = "/getcode",method = RequestMethod.POST)
    ResponseData getCode(@RequestBody(required = false) RequestData<Map<String,String>> requestData);


    /**
     * 校验短信验证码
     * @param account       用户账号
     * @param code          验证代码
     * @return              返回响应对象
     */
    @RequestMapping(value = "/checkacode",method = RequestMethod.POST)
    ResponseData checkaCode(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 获取图片验证码
     * @return              返回响应对象
     */
    @RequestMapping(value = "/getimgcode",method = RequestMethod.POST)
    ResponseData getImgCode();

    /**
     * 校验图片验证码
     * @param requestData   请求对象
     * @return              返回响应对象
     */
    @RequestMapping(value = "/checkaimgcode",method = RequestMethod.POST)
    ResponseData checkaImgCode(RequestData<Map<String,String>> requestData);
}
