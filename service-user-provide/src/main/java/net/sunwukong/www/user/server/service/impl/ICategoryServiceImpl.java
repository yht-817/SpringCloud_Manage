package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.user.bean.CategoryOne;
import net.sunwukong.www.user.server.dao.usercenter.read.CategoryOneMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.read.CategoryTwoMapperRead;
import net.sunwukong.www.user.server.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明:服务目录接口实现
 *
 * @author Mick
 * CreateDate 2018/6/22/022 22:23
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class ICategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryOneMapperRead categoryOneMapperRead;

    @Autowired
    CategoryTwoMapperRead categoryTwoMapperRead;

    @Override
    public ResponseData getCategoryList() {
        ResponseData responseData = new ResponseData();
        List<CategoryOne> categoryOnes = categoryOneMapperRead.queryAllConnectCategoryTwo();
        responseData.setData(categoryOnes);
        return responseData;
    }

    @Override
    public ResponseData getCategoryOneConnectCategoryTwo(String categoryOneNo) {
        ResponseData responseData = new ResponseData();
        CategoryOne byCategoryOneNo = categoryOneMapperRead.findByCategoryOneNoConnectCategoryTwo(categoryOneNo);
        responseData.setData(byCategoryOneNo);
        return responseData;
    }

    @Override
    public ResponseData getCategoryOneConnectCategoryTwo(List<String> oneListNew) {
        ResponseData responseData = new ResponseData();
        if (oneListNew.size()<=0){
            throw new GrilException("传入的服务城市列表为空!");
        }
        List<CategoryOne> categoryOneConnectCategoryTwo = categoryOneMapperRead.getCategoryOneConnectCategoryTwo(oneListNew);
        responseData.setData(categoryOneConnectCategoryTwo);
        return responseData;
    }
}
