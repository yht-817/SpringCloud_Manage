package net.sunwukong.www.marketing.server.task;

import net.sunwukong.www.marketing.server.service.IChatServiceMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Quartz设置项目全局的定时任务
 * @Component注解的意义 泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。一般公共的方法我会用上这个注解
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ServiceMessageTask
 * @Author: kangdong
 * @Date: 2018/6/20 下午3:45
 * @Description: 系统服务未支付通知消息定时任务类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */

//@EnableScheduling       //定时器
@Component
public class ServiceMessageTask {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IChatServiceMessageService iChatServiceMessageService;

    /**
     * 定时查询5分钟未完成支付的订单，生成未支付消息
     * @throws Exception
     */
//    @Scheduled(cron = "0 0/2 * * * ?") // 每10分钟执行一次
//    public void work() throws Exception {
//        //逻辑代码
//        iChatServiceMessageService.addOrderServiceMessage();
//    }
}
