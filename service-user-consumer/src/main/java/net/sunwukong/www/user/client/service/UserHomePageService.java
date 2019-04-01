package net.sunwukong.www.user.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserHomePage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:个人主页服务接口
 *
 * @author Mick
 * CreateDate 2018/6/12/012 12:01
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "user-server-1")
@RequestMapping(value = "/home")
public interface UserHomePageService {

    /**
     * 修改个人主页接口
     * @param userNo            用户编码
     * @param selfEvaluation    自我介绍
     * @return                  返回响应对象
     */
    @RequestMapping(value = "/updatedata",method = RequestMethod.POST)
    ResponseData updateData(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 获取个人主页信息
     * @param userNo        用户编码
     * @return              返回响应对象
     */
    @RequestMapping(value = "/getuserhomepage",method = RequestMethod.POST)
    ResponseData getUserHomePage(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 添加个人主页图片
     * @param userNo        用户编码
     * @param userPhoto     图片内容
     * @return
     */
    @RequestMapping(value = "/addimg",method = RequestMethod.POST)
    ResponseData addImg(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 删除个人主页图片
     * @param userNo        用户编码
     * @param imgNo         图片index
     * @return              返回响应对象
     */
    @RequestMapping(value = "/delimg",method = RequestMethod.POST)
    ResponseData delImg(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 修改个人图片
     * @param id            ID
     * @param imgNo         图片index
     * @param homePhoto     图片内容byte[]
     * @return              返回响应对象
     */
    @RequestMapping(value = "/updateimg",method = RequestMethod.POST)
    ResponseData updateImg(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 获取个人主页访问量
     * @param userNo        用户编码
     * @return              返回响应对象
     */
    @RequestMapping(value = "/getvisit",method = RequestMethod.POST)
    ResponseData getVisit(@RequestBody(required = false) RequestData<Map<String,String>> requestData);

    /**
     * 设置个人主页封面
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/sethomecover",method = RequestMethod.POST)
    ResponseData setHomeCover(RequestData<UserHomePage> requestData);

    /**
     * 获取粉丝列表
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/getuserfanslist",method = RequestMethod.POST)
    ResponseData getUserFansList(RequestData<Map<String, String>> requestData);
}
