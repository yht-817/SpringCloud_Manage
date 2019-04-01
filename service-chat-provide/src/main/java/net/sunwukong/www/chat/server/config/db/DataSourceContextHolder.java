package net.sunwukong.www.chat.server.config.db;

/**
 * 说明:数据库配置
 *
 * @author Mick
 * CreateDate 2018/6/10/010 9:18
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class DataSourceContextHolder {

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {
        local.set(DataSourceType.read.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        local.set(DataSourceType.write.getType());
    }

    public static String getJdbcType() {
        return local.get();
    }
}
