package net.sunwukong.www.user.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.output.MobileVersionOutput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 说明:APP版本更新控制层
 *
 * @author swk
 * @CreateDate 2018/8/20 19:19
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/mobileversion")
@Api(tags = "APP版本更新控制层", description = "")
public class MobileVersionController extends BaseController {

    @RequestMapping(value="/getappversion",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取APP版本信息",response = MobileVersionOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appName", value = "APP名称（ios android）",paramType = "query",defaultValue = "android"),
            @ApiImplicitParam(name = "appType", value = "APP类型（正式版：1 测试版：0）",paramType = "query",defaultValue = "1"),
    })
    public ResponseData getAppVersion(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iMobileVersionService.getAppVersion(data.get("appName"),data.get("appType"),requestData.getV());
    }
}
