package net.sunwukong.www.base.util;

import com.sdkinfo.www.core.date.DateUtil;
import com.sdkinfo.www.core.util.ToolUtil;
import net.sunwukong.www.api.crypto.digest.DigestUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 说明:数据库工具类
 *
 * @author Mick
 * CreateDate 2018/6/9/009 21:53
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class DataBaseTool {

    /**
     * 创建数据库ID (32位)
     *
     * @return
     */
    public static String createId() {
        return DigestUtil.md5Hex(UUID.randomUUID().toString());
    }

    /**
     * 创建数据库编码（参数+14位时间+4位随机）
     *
     * @param head 编码头
     * @return
     */
    public static String createNo(String head) {
        return head + DateUtil.getMillis() + ToolUtil.buildRandom(4);
    }

    /**
     * 生成纯数字单号
     *
     * @param length 生成长度 = 6位日期 + 1位服务编号 + 的随机数
     * @return 180617100000355507780
     * @Author kangdong
     */
    public static String createNum(int length) {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 14 代表长度为14
        // d 代表参数为正数型
        length -= 7;//生成随机数长度
        String date = new SimpleDateFormat("yyMMdd").format(new Date());
        return date + machineId + String.format("%0" + length + "d", hashCodeV);
    }

    /**
     * 生产日期  精确日期到秒
     */
    public static String createDate() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return date;
    }

    public static String createTimes() {
        long timeMillis = System.currentTimeMillis() / 1000;
        return String.valueOf(timeMillis);
    }

    /**
     * 创建用户昵称
     *
     * @return
     */
    public static String createNikeName() {
        return "GoKong用户";
    }


    public static String getMonday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        return weekBegin;
    }
}
