package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.api.util.TimeUtil;
import net.sunwukong.www.marketing.bean.DemandInfo;
import net.sunwukong.www.marketing.server.dao.SingleDemandServerMapper;
import net.sunwukong.www.marketing.server.service.ISingleDemandServerService;
import net.sunwukong.www.marketing.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 说明:个体服务控制服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/26 15:43
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class ISingleDemandServerServiceImpl extends BaseController implements ISingleDemandServerService {

    @Autowired
    SingleDemandServerMapper singleDemandServerMapper;

    @Override
    public ResponseData getServerList(Map<String, String> map) {
        ResponseData responseData = new ResponseData();
        map.put("serverState", map.get("serverState"));
        List<Map<String, Object>> byUserNoAndDemandStateList = singleDemandServerMapper.findByUserNoAndServerStateList(map);
        //转换时间格式
        byUserNoAndDemandStateList.forEach(data -> {
            data.put("changeDate", TimeUtil.getChatTimeStr(((Date)data.get("changeDate")).getTime()));
        });
        responseData.setData(byUserNoAndDemandStateList);
        return responseData;
    }

    @Override
    public ResponseData getServerDetails(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        Map<String, Object> byDemandServerDetails = singleDemandServerMapper.findByDemandServerDetails(data);
        if (byDemandServerDetails==null){
            throw new GrilException("无法查询详情");
        }
        byDemandServerDetails.put("changeDate", TimeUtil.getChatTimeStr(((Date)byDemandServerDetails.get("changeDate")).getTime()));
        responseData.setData(byDemandServerDetails);
        return responseData;
    }

    /**
     * 修改需求服务状态(公用方法)
     * @param data
     * @return
     */
    @Override
    public ResponseData updateServerStatus(Map<String, String> data) {
        //根据需求编码查询需求信息
        ResponseData<DemandInfo> responseData = iDemandInfoService.getDemandInfo(data.get("demandNo"));
        DemandInfo demandInfo = responseData.getData();
        //修改需求主表服务状态(待结单)
        demandInfo.setServerState(data.get("serverState"));
        if (demandInfo.getServerState().equals(SysCode.CODE_10290001.getCode())
                || demandInfo.getServerState().equals(SysCode.CODE_10290005.getCode())){
            demandInfo.setRobUser(data.get("userNo"));
        }else {
            demandInfo.setServiceUser(data.get("userNo"));
        }
        //修改需求主表需求状态(待结单)
        demandInfo.setDemandState(ApiTool.getDemandState(demandInfo.getServerState()));
        //修改需求主表服务状态变更时间
        demandInfo.setChangeDate(new Date());
        responseData  = iDemandInfoService.updateDemandinfo(demandInfo);
        return responseData;
    }

    /**
     * 更新状态为待结单(我已完成服务)
     * @param data
     * @return
     */
    @Override
    public ResponseData updateServerStatusStayStatement(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        data.put("serverState",SysCode.CODE_10290003.getCode());
        updateServerStatus(data);
        return responseData;
    }
}
