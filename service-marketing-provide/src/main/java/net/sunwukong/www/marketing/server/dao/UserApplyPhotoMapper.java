package net.sunwukong.www.marketing.server.dao;

import net.sunwukong.www.api.base.BaseMapper;
import net.sunwukong.www.marketing.bean.UserApplyPhoto;

import java.util.List;

public interface UserApplyPhotoMapper extends BaseMapper<UserApplyPhoto>{

    /**
     * 通过申请编码查询图片资料
     * @param applyNo
     * @return
     */
    List<UserApplyPhoto> findByApplyNo(String applyNo);
}