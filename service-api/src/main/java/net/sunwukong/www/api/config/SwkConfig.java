package net.sunwukong.www.api.config;


import com.sdkinfo.www.setting.dialect.Props;

import java.util.Properties;

/**
 * @author swk
 * @Description ：配置文件
 * @Date 2018/8/1321:38
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
public class SwkConfig {
    /**
     * FTP地址
     */
    public static String FTP_HOSTNAME;
    /**
     * FTP端口
     */
    public static int FTP_PORT;
    /**
     * FTP用户名
     */
    public static String FTP_USERNAME;
    /**
     * FTP密码
     */
    public static String FTP_PASSWORD;
    /**
     * 生成URL地址前缀
     */
    public static String FTP_PATH;
    /**
     * H5文件主域名
     */
    public static String HTML_PATH;

    static {
        Properties prop = Props.getProp("swk.properties");
        FTP_HOSTNAME = prop.getProperty("swk.ftp.hostname");
        FTP_PORT = Integer.parseInt(prop.getProperty("swk.ftp.port"));
        FTP_USERNAME = prop.getProperty("swk.ftp.username");
        FTP_PASSWORD = prop.getProperty("swk.ftp.password");
        FTP_PATH = prop.getProperty("swk.ftp.path");
        HTML_PATH = prop.getProperty("swk.host.html");
    }
}
