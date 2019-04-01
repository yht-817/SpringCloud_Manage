package net.sunwukong.www.user.server.service.impl;

import com.sdkinfo.www.log.StaticLog;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.ftp.DirectoryEnums;
import net.sunwukong.www.base.ftp.FTPUtil;
import net.sunwukong.www.user.server.service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 说明:文件服务实现
 *
 * @author Mick
 * @CreateDate 2018/6/23 11:36
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IFileServiceImpl implements IFileService {

    /**
     * 连接ftp进行 然后把传入的文件上传到文件服务器 然后进行消息入库的处理
     *
     * @param img  原生图片类型
     * @param type 图片类型(1:头像 2:背景图 3:证件图)
     * @return
     */
    public ResponseData uploadImgFtp(MultipartFile img, String type){
        ResponseData responseData = new ResponseData();
        try {
            String url = "";
            switch (type){
                case "1":
                    url = FTPUtil.uploadStreamFile(DirectoryEnums.CODE_100010003, img.getInputStream());
                    break;
                case "2":
                    url = FTPUtil.uploadStreamFile(DirectoryEnums.CODE_100010004, img.getInputStream());
                    break;
                case "3":
                    url = FTPUtil.uploadStreamFile(DirectoryEnums.CODE_100010005, img.getInputStream());
                    break;
            }
            StaticLog.info("上传成功：{}",url);
            responseData.setData(url);
        } catch (IOException e) {
            throw new GrilException("上传文件失败");
        }

        return responseData;
    }
}
