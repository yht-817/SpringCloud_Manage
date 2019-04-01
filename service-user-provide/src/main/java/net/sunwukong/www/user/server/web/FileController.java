package net.sunwukong.www.user.server.web;

import com.sdkinfo.www.log.StaticLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping(value = "/file")
@Api(tags = "文件控制", description = "")
public class FileController extends BaseController {

    @RequestMapping(value = "/uploadimgs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "FTP上传图片", httpMethod = "POST", notes = "FTP上传图片", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "img", value = "直接用表单提交数据，图片不需要进行转换",paramType = "query"),
            @ApiImplicitParam(name = "type", value = "图片类型(1:头像 2:背景图 3:证件图)",paramType = "query"),
    })
    public ResponseData uploadImgFtp(@RequestParam("img") MultipartFile img ,
                                     @RequestParam(value="type",required=true) String type) {
        ResponseData responseData = iFileService.uploadImgFtp(img, type);
        StaticLog.info(responseData.toString());
        return responseData;
    }

}
