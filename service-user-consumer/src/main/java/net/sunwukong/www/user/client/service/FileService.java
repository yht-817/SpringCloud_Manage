package net.sunwukong.www.user.client.service;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.client.config.FeignMultipartSupportConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


/**
 * 说明:文件服务接口
 *
 * @author Mick
 * CreateDate 2018/6/22/022 22:00
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "user-server-1",configuration = FeignMultipartSupportConfig.class)
@RequestMapping(value = "/file")
public interface FileService {

    /**
     * 上传文件到ftp文件服务器上然后进行图片信息入库的处理
     *
     * FeignClient 处理后的文件传输
     *
     * @return
     */
    @RequestMapping(value = "/uploadimgs", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseData uploadImgFtp(@RequestPart("img") MultipartFile img,
                              @RequestParam(value = "type", required = true) String type);
}
