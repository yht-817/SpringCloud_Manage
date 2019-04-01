package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.bean.CategoryOne;
import net.sunwukong.www.user.bean.CategoryTwo;
import net.sunwukong.www.user.bean.UserCategory;
import net.sunwukong.www.user.server.dao.usercenter.read.UserCategoryMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserCategoryMapperWrite;
import net.sunwukong.www.user.server.service.IUserCategoryService;
import net.sunwukong.www.user.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 说明:用户服务类目服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/23 14:39
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserCategoryServiceImpl extends BaseController implements IUserCategoryService {

    @Autowired
    UserCategoryMapperWrite userCategoryMapperWrite;

    @Autowired
    UserCategoryMapperRead userCategoryMapperRead;

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserCategory(UserCategory userCategory) {
        ResponseData responseData = new ResponseData();
        userCategory.setId(DataBaseTool.createId());
        int i = userCategoryMapperWrite.insert(userCategory);
        if (i<=0){
            throw new GrilException("添加用户服务类目失败");
        }
        return responseData;
    }

    @Override
    public ResponseData getUserCategory(String userNo) {
        ResponseData responseData = new ResponseData();
        //获取我服务的类目列表
        List<UserCategory> list = userCategoryMapperRead.findByUserNo(userNo);
        //获取我服务的一级类目
        List<String> oneList = new ArrayList<>();
        List<String> twoList = new ArrayList<>();
        list.forEach(userCategory -> {
            oneList.add(userCategory.getCategoryOneNo());
            twoList.add(userCategory.getCategoryTwoNo());
        });
        //去重一级目录
        List<String> oneListNew = new ArrayList<>(new TreeSet<String>(oneList));
        //获取服务类目信息
        ResponseData<List<CategoryOne>> connectCategoryTwo = iCategoryService.getCategoryOneConnectCategoryTwo(oneListNew);
        List<CategoryOne> categoryOneList = connectCategoryTwo.getData();
        System.out.println(categoryOneList.get(0));
        //剔除不必要的二级服务
        Iterator<CategoryOne> iterator = categoryOneList.iterator();
        while (iterator.hasNext()){
            CategoryOne categoryOne = iterator.next();
            List<CategoryTwo> twos = new ArrayList<>();
            Iterator<CategoryTwo> categoryTwoIterator = categoryOne.getCategoryTwos().iterator();
            while (categoryTwoIterator.hasNext()){
                CategoryTwo categoryTwo = categoryTwoIterator.next();
                twoList.forEach(s -> {
                    if (s.equals(categoryTwo.getCategoryTwoNo())){
                        twos.add(categoryTwo);
                    }
                });
            }
            categoryOne.setCategoryTwos(twos);
        }
        responseData.setData(categoryOneList);
        return responseData;
    }

    @Override
    public ResponseData findByUserNo(String userNo) {
        ResponseData responseData = new ResponseData();
        List<UserCategory> byUserNo = userCategoryMapperRead.findByUserNo(userNo);
        responseData.setData(byUserNo);
        return responseData;
    }
}
