package net.sunwukong.www.marketing.server.task;

import net.sunwukong.www.marketing.server.service.IChoiceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Quartz设置项目全局的定时任务
 *
 * @Component注解的意义 泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。一般公共的方法我会用上这个注解
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: OrderTask
 * @Author: kangdong
 * @Date: 2018/7/19 下午7:52
 * @Description: 订单定时任务类
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */

@EnableScheduling       //定时器
@Component
public class OrderTask {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IChoiceInfoService iChoiceInfoService;

    /**
     * 30分钟没有支付订单，系统自动取消订单
     *
     */
//    @Scheduled(cron = "0 */5 * * * ?") // 每2分钟执行一次
//    public void work() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar beforeTime = Calendar.getInstance();
//        beforeTime.add(Calendar.MINUTE, -5);// 3分钟之前的时间
//        Date beforeD = beforeTime.getTime();
//        String res = sdf.format(beforeD);
//        System.out.println("三分钟以前的时间：" + res);
//        List<Map<String, String>> datalist = iChoiceInfoService.getDataList(res);
//        System.out.println("------------" + datalist.toString());
//        for (int i = 0; i < datalist.size(); i++) {
//            // 批量修改
//            // 1.0 现删除在修改
//            // 查询当前订单的支付情况
//            String payasates = iChoiceInfoService.selectPs(datalist.get(i).get("getNo"));
//            if (payasates != null && payasates != "") {
//                if (payasates.equals("10380001")) {
//                    int depayno = iChoiceInfoService.deletePay(datalist.get(i).get("getNo"));
//                    int uppano = iChoiceInfoService.updatePay(datalist.get(i).get("resourceNo"), datalist.get(i).get("couponNo"));
//                }
//            }
//        }
//    }
}
