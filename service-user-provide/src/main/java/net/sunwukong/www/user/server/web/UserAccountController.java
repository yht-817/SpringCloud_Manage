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
 * 说明:用户账户控制层
 *
 * @author Mick
 * CreateDate 2018/6/9/009 23:03
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/useraccount")
@Api(tags = "用户账户控制层", description = "")
public class UserAccountController extends BaseController{

    @RequestMapping(value="/getallcoin",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    public ResponseData getallCoin(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String, String> data = requestData.getData();
        return iUserAccountService.getallCoin(data.get("userNo"));
    }
}
