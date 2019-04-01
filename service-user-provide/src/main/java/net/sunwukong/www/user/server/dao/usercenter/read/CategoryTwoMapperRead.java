package net.sunwukong.www.user.server.dao.usercenter.read;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.user.bean.CategoryTwo;

import java.util.List;

public interface CategoryTwoMapperRead extends BaseMapper<CategoryTwo> {
    /**
     * 通过一级目录编码查询二级目录
     * @param categoryOneNo 一级目录编码
     * @return
     */
    List<CategoryTwo> findByCategoryOneNo(String categoryOneNo);
}