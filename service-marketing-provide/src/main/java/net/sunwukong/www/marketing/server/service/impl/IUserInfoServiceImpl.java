package net.sunwukong.www.marketing.server.service.impl;

import com.google.common.base.Throwables;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.marketing.bean.UserInfo;
import net.sunwukong.www.marketing.server.dao.UserInfoMapper;
import net.sunwukong.www.marketing.server.service.IUserInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: IUserInfoServerImpl
 * @Author: kangdong
 * @Date: 2018/6/16 下午2:51
 * @Description:
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service
public class IUserInfoServiceImpl implements IUserInfoServer{

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 根据用户编码查询运营库用户信息
     * @param userNo 用户编码
     * @return ResponseData
     */
    @Override
    public ResponseData getUserByUserNo(String userNo) {
        ResponseData responseData = new ResponseData();
        try {
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(userInfoMapper.getUserByUserNo(userNo));
        } catch (Exception e) {
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }

    @Override
    public ResponseData getUserMsgIsokPerfect(String userNo) {
        ResponseData responseData = new ResponseData();
        ResponseData<UserInfo> repUser = getUserByUserNo(userNo);
        UserInfo userInfo = repUser.getData();
        if (userInfo.getPhoneNo()==null||"null".equals(userInfo.getPhoneNo())){
            responseData.setData(false);
            responseData.setMessage("用户信息未完善");
            return responseData;
        }
        responseData.setData(true);
        return responseData;
    }


    /**
     * 通过用户编码更新用户库、运营库、消息库的用户表信息
     * @Author: kangdong
     * @param userInfo
     * @return
     */
    @Override
    public ResponseData updateByUserNoSelective(UserInfo userInfo) {
        ResponseData responseData = new ResponseData();
        try {
            responseData.setCode(StatusEnum.SUCCESS.getCode());
            responseData.setMessage(StatusEnum.SUCCESS.getMsg());
            responseData.setData(userInfoMapper.updateByUserNoSelective(userInfo));
        } catch (Exception e) {
            responseData.setCode(StatusEnum.PARAM_ERROR.getCode());
            responseData.setMessage(Throwables.getRootCause(e).getMessage());
            responseData.setStatus(StatusEnum.PARAM_ERROR);
        }
        return responseData;
    }
}
