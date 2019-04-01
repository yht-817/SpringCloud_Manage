package net.sunwukong.www.user.client.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.CategoryOne;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明:目录控制层
 *
 * @author Mick
 * CreateDate 2018/6/22/022 22:02
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/v1/category")
@Api(tags = "服务类别目录", description = "")
public class CategoryController extends BaseController {

    @RequestMapping(value="/getcategorylist",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取目录列表", httpMethod = "POST", notes = "获取目录列表",response = CategoryOne.class)
    public ResponseData getCategoryList(){
        return categoryService.getCategoryList();
    }
}
