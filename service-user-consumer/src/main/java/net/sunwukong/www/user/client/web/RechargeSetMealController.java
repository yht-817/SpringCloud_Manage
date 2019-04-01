package net.sunwukong.www.user.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.RechargeSetMeal;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 说明:充值套餐控制层
 *
 * @author Mick
 * CreateDate 2018/6/19/019 17:39
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/v1/rechargesetmeal")
@Api(tags = "充值服务控制", description = "")
public class RechargeSetMealController extends BaseController {

    @RequestMapping(value="/getlist",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取充值套餐列表", httpMethod = "POST", notes = "获取充值套餐列表",response = RechargeSetMeal.class)
    public ResponseData getList(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        return rechargeSetMealService.getList(requestData);
    }
}
