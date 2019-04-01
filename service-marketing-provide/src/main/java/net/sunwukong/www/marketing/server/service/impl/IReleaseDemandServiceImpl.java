package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.marketing.bean.UserApply;
import net.sunwukong.www.marketing.server.dao.CityMangerApplyMapper;
import net.sunwukong.www.marketing.server.dao.DemandInfoMapper;
import net.sunwukong.www.marketing.server.dao.UserApplyMapper;
import net.sunwukong.www.marketing.server.service.IReleaseDemandService;
import net.sunwukong.www.marketing.server.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明:发布需求服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/4 15:28
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IReleaseDemandServiceImpl extends BaseController implements IReleaseDemandService {
    @Autowired
    CityMangerApplyMapper cityMangerApplyMapper;

    @Autowired
    UserApplyMapper userApplyMapper;

    @Autowired
    DemandInfoMapper demandInfoMapper;
    @Override
    public ResponseData getCityOfficerList(String cityNo) {
        ResponseData responseData = new ResponseData();
        Map<String,String> map = new HashMap<>();
        map.put("cityNo",cityNo);
        map.put("auditState", SysCode.CODE_10070002.getCode());

        //获取当前城市的城市运营官
        List<Map<String, Object>> cityOfficerList = cityMangerApplyMapper.getCityOfficerList(map);
        cityOfficerList.forEach(data->{
            List<UserApply> byuserNoMangerAndAuditState = iUserApplyService.findByUserNoManagerAndAuditState(String.valueOf(data.get("userNo")),SysCode.CODE_10070002.getCode());
            data.put("orderNumber","0");
            byuserNoMangerAndAuditState.forEach(userApply->{
                Map<String,String> map1 = new HashMap<>();
                map1.put("userNo",String.valueOf(userApply.getUserNo()));
                map1.put("serverState",SysCode.CODE_10290004.getCode());
                map1.put("demandState",SysCode.CODE_10080004.getCode());
                data.put("orderNumber",Integer.parseInt(String.valueOf(data.get("orderNumber")))+demandInfoMapper.countByUserNoAndServerStateAndDemandState(map1));
            });
            data.put("underNumber",byuserNoMangerAndAuditState.size());
        });
        responseData.setData(cityOfficerList);
        return responseData;
    }
}
