package net.sunwukong.www.base.util;


import com.sdkinfo.www.core.date.DateUtil;
import com.sdkinfo.www.core.util.ToolUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 岳虹廷 on  2018/7/8
 * <p>
 * FTP 连接 上传文件信息
 */
public class FtpClientTools {
    public static final String hostpath = "http://47.89.8.14/static_img/";
    private static final String hostname = "47.89.8.14";
    private static final int port = 8222;
    private static final String username = "sunwukongroot";
    private static final String password = "qazxsw";
    private static final String userinfoheadpath = "userinfo/head/";
    private static final String userinfobackpath = "userinfo/back/";
    private static final String useridenpath = "useriden/";
    private static final String articlepath = "article/";

    // 连接FTP获取结果对象
    public static String uploadFile(MultipartFile img, String type) {
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.setControlEncoding("GBK"); // 设置字符集
            ftpClient.connect(hostname, port);
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode();// 验证FTP服务器是否登录成功
            if (FTPReply.isPositiveCompletion(replyCode)) {
                if (type.equals("1")) {
                    return saveImg(img, userinfoheadpath, ftpClient);
                } else if (type.equals("2")) {
                    return saveImg(img, userinfobackpath, ftpClient);
                } else if (type.equals("3")) {
                    return saveImg(img, useridenpath, ftpClient);
                } else if (type.equals("4")) {
                    return saveImg(img, articlepath, ftpClient);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    // 图片根据不同的目录进行上传
    public static String saveImg(MultipartFile img, String savepath, FTPClient ftpClient) {
        String name = DateUtil.getMillis() + ToolUtil.buildRandom(4) + img.getOriginalFilename().lastIndexOf(".");
        boolean success = false;
        // 进行FTP目录切换
        try {
            ftpClient.makeDirectory(savepath);
            if (ftpClient.changeWorkingDirectory(savepath)) {
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                success = ftpClient.storeFile(name, img.getInputStream());
                if (success) {
                    offFtp(ftpClient);
                    return savepath + name;
                }
                offFtp(ftpClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    // 连接FTP获取结果对象
    public static List<String> uploadFile(MultipartFile[] img, String type) {
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.setControlEncoding("GBK"); // 设置字符集
            ftpClient.connect(hostname, port);
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode();// 验证FTP服务器是否登录成功
            if (FTPReply.isPositiveCompletion(replyCode)) {
                if (type.equals("1")) {
                    return saveImg(img, userinfoheadpath, ftpClient);
                } else if (type.equals("2")) {
                    return saveImg(img, userinfobackpath, ftpClient);
                } else if (type.equals("3")) {
                    return saveImg(img, useridenpath, ftpClient);
                } else if (type.equals("4")) {
                    return saveImg(img, articlepath, ftpClient);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    // 图片根据不同的目录进行上传
    public static List<String> saveImg(MultipartFile[] img, String savepath, FTPClient ftpClient) {
        try {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < img.length; i++) {
                String name = DateUtil.getMillis() + ToolUtil.buildRandom(4) + img[i].getOriginalFilename().lastIndexOf(".");
                boolean success = false;
                ftpClient.makeDirectory(savepath);
                if (ftpClient.changeWorkingDirectory(savepath)) {
                    ftpClient.setBufferSize(1024);
                    ftpClient.setControlEncoding("UTF-8");
                    ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                    ftpClient.enterLocalPassiveMode();
                    success = ftpClient.storeFile(name, img[i].getInputStream());
                    if (success) {
                        list.add(hostpath + savepath + name);
                    }
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            offFtp(ftpClient);
        }

        return null;
    }

    // 销毁FTP的对象
    public static void offFtp(FTPClient ftpClient) {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
