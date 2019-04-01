package net.sunwukong.www.user.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.web.multipart.MultipartFile;

/**
 * 说明:文件服务接口
 *
 * @author Mick
 * CreateDate 2018/6/22/022 22:00
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IFileService {
    /**
     * 使用ftp上传图片
     * @param img 原生图片类型
     * @param type 图片类型(1:头像 2:背景图 3:证件图)
     * @return
     */
    ResponseData uploadImgFtp(MultipartFile img, String type);
}
