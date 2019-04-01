package net.sunwukong.www.base.ftp;

import com.sdkinfo.www.log.StaticLog;
import net.sunwukong.www.api.config.SwkConfig;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

/**
 * 说明:FTP配置文件
 *
 * @author Mick
 * @CreateDate 2018/7/26 11:02
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
public class FtpConfig {

    private static String hostName;

    private static int port;

    private static String userName;

    private static String password;

    public static String path;

    private static FTPClient ftpClient;

    static {
        hostName = SwkConfig.FTP_HOSTNAME;
        port = SwkConfig.FTP_PORT;
        userName = SwkConfig.FTP_USERNAME;
        password = SwkConfig.FTP_PASSWORD;
        path = SwkConfig.FTP_PATH;
    }

    /**
     * 获取FTP实例
     * @return
     */
    public static FTPClient getFtpClient(){
        /*try {
            if (ftpClient == null) {
                synchronized (FtpConfig.class) {
                    if (null == ftpClient) {
                        ftpClient = new FTPClient();
                        //设置编码
                        ftpClient.setControlEncoding("utf-8");
                        //连接ftp服务器
                        ftpClient.connect(hostName, port);
                        //登录ftp服务器
                        ftpClient.login(userName, password);
                        //是否成功登录服务器
                        int replyCode = ftpClient.getReplyCode();
                        //开通一个端口来传输数据
                        ftpClient.enterLocalPassiveMode();
                        if(!FTPReply.isPositiveCompletion(replyCode)){
                            StaticLog.error("connect failed...ftp服务器:"+hostName+":"+port);
                        }else {
                            StaticLog.error("connect success...ftp服务器:"+hostName+":"+port);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            ftpClient = new FTPClient();
            //设置编码
            ftpClient.setControlEncoding("utf-8");
            //连接ftp服务器
            ftpClient.connect(hostName, port);
            //登录ftp服务器
            ftpClient.login(userName, password);
            //是否成功登录服务器
            int replyCode = ftpClient.getReplyCode();
            //开通一个端口来传输数据
            ftpClient.enterLocalPassiveMode();
            //设置连接超时
            ftpClient.setConnectTimeout(60*1000*10);
            if(!FTPReply.isPositiveCompletion(replyCode)){
                StaticLog.error("connect failed...ftp服务器:"+hostName+":"+port);
            }else {
                StaticLog.error("connect success...ftp服务器:"+hostName+":"+port);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }
}
