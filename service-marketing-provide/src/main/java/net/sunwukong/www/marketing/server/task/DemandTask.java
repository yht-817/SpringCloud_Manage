package net.sunwukong.www.marketing.server.task;

import net.sunwukong.www.marketing.server.service.IDemandServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Quartz设置项目全局的定时任务
 * @Component注解的意义 泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。一般公共的方法我会用上这个注解
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: DemandTask
 * @Author: kangdong
 * @Date: 2018/6/19 下午7:52
 * @Description: 需求定时任务类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */

//@EnableScheduling       //定时器
@Component
public class DemandTask {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IDemandServerService iDemandServerService;

    /**
     * 24小时没人接单时，系统自动派单给系统默认服务机构
     * @throws Exception
     */
    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟执行一次
    public void work() throws Exception {
        //逻辑代码
        iDemandServerService.insertSysAllot();
    }
}
