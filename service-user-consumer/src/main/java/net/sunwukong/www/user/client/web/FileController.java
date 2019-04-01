package net.sunwukong.www.user.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 说明:上传文件控制层
 *
 * @author Mick
 * CreateDate 2018/6/22/022 22:02
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/v1/file")
@Api(tags = "文件控制", description = "")
public class FileController extends BaseController{

    // 使用FTP上传文件进行数据记录
    @RequestMapping(value = "/uploadftpfile", method = {RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "FTP上传图片", httpMethod = "POST", notes = "FTP上传图片", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "img", value = "直接用表单提交图片和类型数据", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "图片类型(1:头像 2:背景图 3:证件图)", paramType = "query"),
    })
    public ResponseData uploadImgFtp(@RequestPart("img") MultipartFile img,
                                     @RequestParam(value = "type", required = true) String type) {
        return fileService.uploadImgFtp(img, type);
    }
}
