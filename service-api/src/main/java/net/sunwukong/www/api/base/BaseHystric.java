package net.sunwukong.www.api.base;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;


/**
 * 说明:公用熔断
 *
 * @author Mick
 * CreateDate 2018/6/14 19:03
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class BaseHystric {

    public static ResponseData plusMsg(){
        ResponseData responseData = new ResponseData();
        responseData.setStatus(StatusEnum.SERVER_DISABLED);
        return responseData;
    }
}
