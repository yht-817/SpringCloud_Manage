package net.sunwukong.www.chat.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.vo.PageVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:服务通知模板
 *
 * @author Mick
 * @CreateDate 2018/7/12 10:39
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@FeignClient(value = "chat-server-1",path = "/chatservicemessage")
public interface IChatServiceMessageService {

    /**
     * 分页获取用户通知列表
     * @param vo    分页VO
     * @return
     */
    @RequestMapping(value = "/querypagechatservicemessage",method = RequestMethod.POST)
    ResponseData queryPageChatServiceMessage(PageVo vo);

    /**
     * 设置服务消息阅读状态
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/setreadstate",method = RequestMethod.POST)
    ResponseData setReadState(RequestData<Map<String,String>> requestData);
}
