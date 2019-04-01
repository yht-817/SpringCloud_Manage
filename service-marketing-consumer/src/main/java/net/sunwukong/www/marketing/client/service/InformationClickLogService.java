package net.sunwukong.www.marketing.client.service;

import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.InformationClickLog;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 说明:资讯点击记录接口服务
 *
 * @author Mick
 * CreateDate 2018/6/13/013 15:20
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@FeignClient(value = "marketing-server-1", path = "/informationclicklog")
public interface InformationClickLogService {
}
