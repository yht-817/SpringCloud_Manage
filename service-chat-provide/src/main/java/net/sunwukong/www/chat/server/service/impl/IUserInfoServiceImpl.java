package net.sunwukong.www.chat.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.chat.bean.FriendInvite;
import net.sunwukong.www.chat.bean.UserInfo;
import net.sunwukong.www.chat.server.dao.UserInfoMapper;
import net.sunwukong.www.chat.server.service.IUserInfoService;
import net.sunwukong.www.chat.server.web.BaseController;
import net.sunwukong.www.chat.vo.QueryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明:用户服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/10 17:01
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserInfoServiceImpl extends BaseController implements IUserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 检索用户
     * @param userNo    用户编码
     * @param content   检索内容
     * @param start     开始位置
     * @param end
     * @return
     */
    @Override
    public ResponseData queryPageSearchUser(String userNo,String content,int start,int end) {
        ResponseData responseData = new ResponseData();
        List<QueryUser> maps = userInfoMapper.queryPageSearchUser(content,start,end);

        //查询当前检索者与检索用户是否是好友
        maps.forEach(map -> {
            //如果搜索到自己 默认为false 因为不能加自己为好友
            if (map.getUserNo().equals(userNo)) {
                map.setIsokFriend(true);
            }else {
                FriendInvite friendInvite = iFriendInviteService.findByUserNoAndFriendUserNoAndInviteState(userNo, map.getUserNo(), SysCode.CODE_10150002.getCode());
                if (friendInvite != null) {
                    map.setIsokFriend(true);
                } else {
                    map.setIsokFriend(false);
                }
            }
        });
        responseData.setData(maps);
        return responseData;
    }

    /**
     * 根据用户编码查询用户信息
     * @param userNo    用户编码
     * @return
     */
    @Override
    public UserInfo findByUserNo(String userNo) {
        UserInfo byUserNo = userInfoMapper.findByUserNo(userNo);
        if (byUserNo==null){
            throw new GrilException("用户不存在");
        }
        return byUserNo;
    }


    /**
     * 根据用户编码获取用户ID
     * @param userNo    用户编码
     * @return
     */
    @Override
    public ResponseData getUserId(String userNo) {
        ResponseData responseData = new ResponseData();
        String userId = userInfoMapper.getUserId(userNo);
        responseData.setData(userId);
        return responseData;
    }
}
