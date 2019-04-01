package net.sunwukong.www.user.server.web;

import io.swagger.annotations.Api;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 说明:验证码控制层
 *
 * @author Mick
 * CreateDate 2018/6/9/009 23:03
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/securitycode")
@Api(tags = "验证码控制层", description = "")
public class SecurityCodeController extends BaseController{

    @RequestMapping(value="/getcode",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    public ResponseData getCode(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iSecurityCodeService.getCode(data.get("account"),data.get("accountType"));
    }

    @RequestMapping(value = "/checkacode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public ResponseData checkaCode(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iSecurityCodeService.checkaCode(data.get("account"),data.get("code"));
    }

    @RequestMapping(value="/getimgcode",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    public ResponseData getImgCode(){
        return iSecurityCodeService.getImgCode();
    }

    @RequestMapping(value = "/checkaimgcode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public ResponseData checkaImgCode(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iSecurityCodeService.checkaImgCode(data.get("account"),data.get("accountType"),data.get("code"));
    }
}
