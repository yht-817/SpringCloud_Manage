package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.CategoryOne;

import java.util.List;

public interface CategoryOneMapperRead extends BaseMapper<CategoryOne> {

    CategoryOne findByCategoryOneNo(String categoryOneNo);

    List<CategoryOne> getCategoryOneConnectCategoryTwo(List<String> oneListNew);

    CategoryOne findByCategoryOneNoConnectCategoryTwo(String categoryOneNo);

    List<CategoryOne> queryAllConnectCategoryTwo();
}