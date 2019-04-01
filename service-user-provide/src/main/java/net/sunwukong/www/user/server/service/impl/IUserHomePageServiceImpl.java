package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.StatusEnum;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.bean.UserHomePage;
import net.sunwukong.www.user.bean.UserHomePageVisit;
import net.sunwukong.www.user.output.UserHomeOutput;
import net.sunwukong.www.user.output.Visit;
import net.sunwukong.www.user.server.dao.chatmess.read.FriendInviteMapperRead;
import net.sunwukong.www.user.server.dao.marketing.read.DemandServerMapperRead;
import net.sunwukong.www.user.server.dao.marketing.read.InformationClickLogMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.read.UserHomePageMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserHomePageMapperWrite;
import net.sunwukong.www.user.server.service.IUserHomePageService;
import net.sunwukong.www.user.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 说明:个人主页实现类
 *
 * @author Mick
 * CreateDate 2018/6/12/012 16:53
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IUserHomePageServiceImpl extends BaseController implements IUserHomePageService {

    @Autowired
    UserHomePageMapperRead userHomePageMapperRead;

    @Autowired
    UserHomePageMapperWrite userHomePageMapperWrite;

    @Autowired
    InformationClickLogMapperRead informationClickLogMapperRead;

    @Autowired
    DemandServerMapperRead demandServerMapperRead;

    @Autowired
    FriendInviteMapperRead friendInviteMapperRead;

    /**
     * 更新自我介绍
     * @param id                ID
     * @param selfEvaluation    自我介绍
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updateData(String id, String selfEvaluation) {
        UserHomePage userHomePage = new UserHomePage();
        userHomePage.setId(id);
        userHomePage.setSelfEvaluation(selfEvaluation);
        return updateUserHomePage(userHomePage);
    }

    /**
     * 获取用户主页信息
     * @param userNo        用户编码
     * @return
     */
    @Override
    public ResponseData getUserHomePage(String userNo,String visitorNo) {
        ResponseData responseData = new ResponseData();
        if(!userNo.equals(visitorNo)){
            UserHomePageVisit userHomePageVisit = new UserHomePageVisit();
            userHomePageVisit.setUserNo(userNo);
            userHomePageVisit.setVisitNo(visitorNo);
            iUserHomePageVisitService.addUserVisit(userHomePageVisit);
        }
        UserHomeOutput output = new UserHomeOutput();

        //获取主页个人图片
        UserHomePage byUserInfo = findByUserInfo(userNo);
        output.setPageData(byUserInfo);

        //获取个人主页粉丝列表
        List<Map<String, String>> userClickList = informationClickLogMapperRead.getUserClickList(userNo,0,0);
        output.setFansNo(userClickList.size());

        //获取个人主页服务单数
        int countServer = demandServerMapperRead.countByUserNoAndServerState(userNo, SysCode.CODE_10290004.getCode());
        output.setServerNo(countServer);

        //获取主页访问量
        Visit visit = iUserHomePageVisitService.getVisit(userNo);
        output.setVisit(visit);

        //获取是否是好友
        boolean isokFriend = iFriendInviteService.judgeIsExistFriend(userNo, visitorNo);
        output.setIsokFriend(isokFriend);

        responseData.setData(output);

        return responseData;
    }

    /**
     * 根据用户编码获取个人图片
     * @param userNo    用户编码
     * @return
     */
    @Override
    public UserHomePage findByUserInfo(String userNo) {
        UserHomePage byUserNo = userHomePageMapperRead.findByUserNo(userNo);
        if (byUserNo == null) {
            UserHomePage userHomePage = new UserHomePage();
            userHomePage.setId(DataBaseTool.createId());
            userHomePage.setUserNo(userNo);
            userHomePage.setSelfEvaluation("这家伙很懒，什么也没留下！");
            userHomePageMapperWrite.insertSelective(userHomePage);
            byUserNo = userHomePageMapperRead.findByUserNo(userNo);
        }
        return byUserNo;
    }

    /**
     * 添加图片信息
     * @param id        用户编码
     * @param imgNo     图片下标
     * @param homePhoto 图片地址
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addImg(String id,String imgNo, String homePhoto) {
        ResponseData responseData = new ResponseData();
        int i = userHomePageMapperWrite.updateHomePhotofield(id, "home_photo" + Integer.parseInt(imgNo), homePhoto);
        if (i<=0){
            responseData.setStatus(StatusEnum.FAIL);
            return responseData;
        }
        return responseData;
    }

    /**
     * 删除图片信息
     * @param id            ID
     * @param imgNo         图片index
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData delImg(String id, String imgNo) {
        ResponseData responseData = new ResponseData();
        int i = userHomePageMapperWrite.updateHomePhotofield(id, "home_photo" + imgNo,"");
        if (i<=0){
            responseData.setStatus(StatusEnum.FAIL);
            return responseData;
        }
        return responseData;
    }

    /**
     * 修改图片信息
     * @param id            ID
     * @param imgNo         图片index
     * @param homePhoto     图片地址
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updateImg(String id, String imgNo, String homePhoto) {
        ResponseData responseData = new ResponseData();
        String field = "home_photo" + imgNo;
        int i = userHomePageMapperWrite.updateHomePhotofield(id, field,homePhoto);
        if (i<=0){
            responseData.setStatus(StatusEnum.FAIL);
            return responseData;
        }
        return responseData;
    }

    @Override
    public ResponseData setHomeCover(UserHomePage userHomePage) {
        return updateUserHomePage(userHomePage);
    }

    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData updateUserHomePage(UserHomePage userHomePage) {
        ResponseData responseData = new ResponseData();
        int i = userHomePageMapperWrite.updateByPrimaryKeySelective(userHomePage);
        if (i<=0){
            throw new GrilException("修改个人主页失败");
        }
        return responseData;
    }
}
