package net.sunwukong.www.marketing.server.service.impl;

import com.sdkinfo.www.log.StaticLog;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.DemandEvaluate;
import net.sunwukong.www.marketing.bean.DemandInfo;
import net.sunwukong.www.marketing.bean.UserInfo;
import net.sunwukong.www.marketing.server.dao.DemandEvaluateMapper;
import net.sunwukong.www.marketing.server.service.IDemandEvaluateService;
import net.sunwukong.www.marketing.server.web.BaseController;
import net.sunwukong.www.user.bean.SysUserGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 说明:需求评价服务接口实现
 *
 * @author Mick
 * CreateDate 2018/6/25/025 18:18
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IDemandEvaluateServiceImpl extends BaseController implements IDemandEvaluateService {

    @Autowired
    DemandEvaluateMapper demandEvaluateMapper;

    @Override
    @Transactional(rollbackFor = {GrilException.class})
    public ResponseData addDemandEvaluate(DemandEvaluate demandEvaluate) {
        ResponseData<DemandInfo> demandInfoResponseData = iDemandInfoService.getDemandInfo(demandEvaluate.getDemandNo());
        DemandInfo demandInfo = demandInfoResponseData.getData();
        ResponseData responseData = new ResponseData();
        demandEvaluate.setId(DataBaseTool.createId());
        demandEvaluate.setEvaluateDate(new Date());
        demandEvaluate.setUserNo(demandInfo.getRobUser());
        int i = demandEvaluateMapper.insertSelective(demandEvaluate);
        if (i<=0){
            throw new GrilException("添加服务评价失败");
        }
        //修改用户评分值
        ResponseData<UserInfo> byUserNo = iUserInfoServer.getUserByUserNo(demandEvaluate.getUserNo());
        UserInfo byUserNoData = byUserNo.getData();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserNo(byUserNoData.getUserNo());
        userInfo.setEvaluateDividing(byUserNoData.getEvaluateDividing()+demandEvaluate.getPresonStar()+demandEvaluate.getTimeStar()+demandEvaluate.getQualityStat());
        //根据累计评分获取等级信息
        ResponseData<SysUserGrade> sysUserGradeWhereEvaluate = iSysUserGradeService.getSysUserGradeWhereEvaluate(userInfo.getEvaluateDividing());
        userInfo.setUserGrade(sysUserGradeWhereEvaluate.getData().getUserGrade());
        userInfo.setUserGradeChangeDate(new Date());
        iUserInfoServer.updateByUserNoSelective(userInfo);
        //判断是否投诉
        if (demandEvaluate.getComplainState().equals(SysCode.CODE_10280002.getCode())){
            //发送投诉通知 获取服务机构信息
            ResponseData<UserInfo> userByUserNo = iUserInfoServer.getUserByUserNo(demandEvaluate.getUserNo());
            //获取联系方式
            String phoneNo = userByUserNo.getData().getPhoneNo();
            StaticLog.info("投诉通知成功："+phoneNo);

            responseData.setMessage("投诉成功");
        }
        return responseData;
    }
}
