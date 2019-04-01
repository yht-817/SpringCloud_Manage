package net.sunwukong.www.base.ftp;

import com.sdkinfo.www.core.date.DateUtil;
import com.sdkinfo.www.core.util.ToolUtil;
import com.sdkinfo.www.log.StaticLog;
import net.sunwukong.www.api.exception.GrilException;
import org.apache.commons.net.ftp.FTPClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明:FTP工具类
 *
 * @author Mick
 * @CreateDate 2018/7/26 11:15
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class FTPUtil {

    //private static FTPClient ftpClient = FtpConfig.getFtpClient();

    public static void main(String[] args) {
        String s = uploadStringFile(DirectoryEnums.CODE_100010004, "");
        System.out.println(s);
    }

    /**
     * 单个上传文件
     * @param directoryEnums    ftp服务保存目录
     * @param str               字符串
     * @return
     */
    public static String uploadStringFile(DirectoryEnums directoryEnums,String str){
        FTPClient ftpClient = FtpConfig.getFtpClient();
        String directory = directoryEnums.getDirectory();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //设置缓存大小
            ftpClient.setBufferSize(1024);
            //创建目录
            ftpClient.makeDirectory(directory);
            StaticLog.error("切换至FTP目录:{}",directory);
            //切换至FTP目录
            if(ftpClient.changeWorkingDirectory(directory)){
                String fileName = createFileName(null);
                try {

                    boolean b = ftpClient.storeFile(fileName, new ByteArrayInputStream(str.getBytes()));
                    StaticLog.error("FTP上传文件:{} 剩余:{}",b,0);
                    return FtpConfig.path+directory+fileName;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                StaticLog.error("切换目录失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return null;
    }

    /**
     * 批量上传文件
     * @param directoryEnums    ftp服务保存目录
     * @param strings           字符串
     * @return
     */
    public static List<String> uploadStringFile(DirectoryEnums directoryEnums,List<String> strings){
        FTPClient ftpClient = FtpConfig.getFtpClient();
        List<String> paths = new ArrayList<>();
        String directory = directoryEnums.getDirectory();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //创建目录
            ftpClient.makeDirectory(directory);
            //切换至FTP目录
            if(ftpClient.changeWorkingDirectory(directory)){
                StaticLog.error("切换至FTP目录:{}",directory);
                //循环上传
                int index = 0;
                int size = strings.size();
                for (int i = 0; i < size; i++) {
                    String fileName = createFileName(null);
                    paths.add(FtpConfig.path+directory+fileName);
                    try {
                        boolean b = ftpClient.storeFile(fileName, new ByteArrayInputStream(strings.get(i).getBytes()));
                        StaticLog.error("FTP上传={}=文件:{} 剩余：{}", fileName, b, (size-(i+1)));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                StaticLog.error("切换目录失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return paths;
    }



    /**
     * 单个上传文件
     * @param directoryEnums    ftp服务保存目录
     * @param inputStream       输入文件流
     * @return
     */
    public static String uploadStreamFile(DirectoryEnums directoryEnums,InputStream inputStream){
        FTPClient ftpClient = FtpConfig.getFtpClient();
        String directory = directoryEnums.getDirectory();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //设置缓存大小
            ftpClient.setBufferSize(1024);
            //创建目录
            ftpClient.makeDirectory(directory);
            //切换至FTP目录
            StaticLog.error("切换至FTP目录:{}",directory);
            if(ftpClient.changeWorkingDirectory(directory)){
                String fileName = createFileName(null);
                try {

                    boolean b = ftpClient.storeFile(fileName, inputStream);
                    StaticLog.error("FTP上传文件:{}",b);
                    return FtpConfig.path+directory+fileName;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                StaticLog.error("切换目录失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return null;
    }

    /**
     * 批量上传文件
     * @param directoryEnums    ftp服务保存目录
     * @param inputStreams      输入文件流
     * @return
     */
    public static List<String> uploadStreamFile(DirectoryEnums directoryEnums,List<InputStream> inputStreams){
        FTPClient ftpClient = FtpConfig.getFtpClient();
        List<String> paths = new ArrayList<>();
        String directory = directoryEnums.getDirectory();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //创建目录
            ftpClient.makeDirectory(directory);
            //切换至FTP目录
            if(ftpClient.changeWorkingDirectory(directory)){
                StaticLog.error("切换至FTP目录:{}",directory);
                int size = inputStreams.size();
                for (int i = 0; i < size; i++) {
                    String fileName = createFileName(null);
                    paths.add(FtpConfig.path + directory + fileName);
                    try {
                        boolean b = ftpClient.storeFile(fileName, inputStreams.get(i));
                        StaticLog.error("FTP上传={}=文件:{} 剩余：{}", fileName, b, (size-(i+1)));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                StaticLog.error("切换目录失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return paths;
    }



    /**
     * 批量上传文件
     * @param directoryEnums    ftp服务保存目录
     * @param bytes             字节流
     * @return
     */
    public static List<String> uploadByteFile(DirectoryEnums directoryEnums,List<byte[]> bytes){
        FTPClient ftpClient = FtpConfig.getFtpClient();
        List<String> paths = new ArrayList<>();
        String directory = directoryEnums.getDirectory();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //创建目录
            ftpClient.makeDirectory(directory);
            //切换至FTP目录
            if(ftpClient.changeWorkingDirectory(directory)){
                StaticLog.error("切换至FTP目录:{}",directory);
                //循环上传
                bytes.forEach(by -> {
                    String fileName = createFileName(null);
                    paths.add(FtpConfig.path+directory+fileName);
                    try {
                        boolean b = ftpClient.storeFile(fileName, new ByteArrayInputStream(by));
                        StaticLog.error("FTP上传={}=文件:{}",fileName,b);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }else {
                StaticLog.error("切换目录失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return paths;
    }

    /**
     * 销毁FTP的对象
     * @param ftpClient
     */
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

    /**
     * 创建文件名称
     * @return
     */
    private static String createFileName(String name){
        return DateUtil.getMillis() + ToolUtil.buildRandom(8)+(name!=null?name:"");
    }

    /**
     * 根据地址获得数据的输入流
     *
     * @param strUrl
     *            网络连接地址
     * @return
     */
    public static InputStream getImageStreamByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(60 * 1000);
            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
            return inStream;
        } catch (Exception e) {
            throw new GrilException("网络连接超时");
        }
    }
}
