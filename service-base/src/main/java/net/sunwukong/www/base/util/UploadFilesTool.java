package net.sunwukong.www.base.util;

import com.jcraft.jsch.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.UUID;
import java.util.Vector;

/**
 * SFTP上传文件
 * 引入包 jsch-0.1.52.jar
 * 将图片上传到另一台服务器处理
 *
 * 开发人员: kangdong
 * 版本日志: 1.0
 * 创建日期: 2018/6/10
 *
 */
public class UploadFilesTool {

    //host 主机
    private static String host;
    //port 端口
    private static int port;
    //username 用户名
    private static String username;
    //password 密码
    private static String password;
    //图片上传所在文件
    private static String directory;


    /**
     * 接sftp
     * @return
     */
    public static ChannelSftp connect(String host, int port, String username, String password) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return sftp;
    }

    /**
     * 执行上传文件
     * @param directory 上传的目录
     * @param uploadFile 要上传的文件
     * @param sftp
     */
    public static void upload(String directory, String uploadFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file = new File(uploadFile);
            sftp.put(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 下载文件
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     * @param sftp
     */
    public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 列出目录的文件
     * @param directory 要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * 重命名
     * @param fileName
     * @return
     */
    public static String generateFileName(String fileName) {
        String name = UUID.randomUUID().toString();
        int position = fileName.lastIndexOf(".");
        String extension = fileName.substring(position);
        return name  + extension;
    }


    /**
     * 上传文件
     * @param file 文件
     * @return
     */
    /*public static String  uploadfiles(File file) {
        //判断上传文件大小
        //1M=1024k=1048576
        long size = file.length();
        if (size > 1048576*3) return "文件不能超过3M";

            String filename = file.getName();

            try {
                Properties prop = Props.getProp("ftp.properties");
                Properties server = PropertyControl.decrypt(prop, false);

                host = server.getProperty("server.host");
                port = Integer.parseInt(server.getProperty("server.port"));
                username = server.getProperty("server.username");
                password = server.getProperty("server.password");
                directory = server.getProperty("server.directory");

                ChannelSftp sftp = UploadFilesTool.connect(host, port, username, password);
                UploadFilesTool.upload(directory, file.getPath(), sftp);
                System.out.println("成功");
                //http://39.105.38.xxx/home/ + newname
                return directory + filename;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }*/
}
