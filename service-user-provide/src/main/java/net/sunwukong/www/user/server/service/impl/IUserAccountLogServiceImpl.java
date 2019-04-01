package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.api.util.TimeUtil;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.bean.UserAccountLog;
import net.sunwukong.www.user.server.dao.usercenter.read.UserAccountLogMapperRead;
import net.sunwukong.www.user.server.dao.usercenter.write.UserAccountLogMapperWrite;
import net.sunwukong.www.user.server.service.IUserAccountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 说明:账户金额变动服务接口实现
 *
 * @author Mick
 * CreateDate 2018/6/15 17:40
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Service
public class IUserAccountLogServiceImpl implements IUserAccountLogService {

    @Autowired
    UserAccountLogMapperWrite userAccountLogMapperWrite;

    @Autowired
    UserAccountLogMapperRead userAccountLogMapperRead;

    /**
     * 获取用户昨日金币
     * @param userNo    用户编码
     * @return
     */
    @Override
    public ResponseData getYesterDayCoin(String userNo) {
        ResponseData responseData = new ResponseData();
        List<UserAccountLog> byUserNoList = userAccountLogMapperRead.findByUserNoList(userNo);
        int coinNo = byUserNoList.stream().mapToInt(userAccountLog -> userAccountLog.getChangeAmount().intValue()).sum();
        if (coinNo<0){
            coinNo = 0;
        }
        responseData.setData(coinNo);
        return responseData;
    }

    /**
     * 分页获取用户资金变动明细
     * @param userNo    用户编码
     * @param pageNo    当前页
     * @param pageSize  页面长度
     * @return
     */
    @Override
    public ResponseData getDetailsPage(String userNo, String pageNo, String pageSize) {
        ResponseData responseData = new ResponseData();
        List<Map<String,Object>> allListPage = userAccountLogMapperRead.getAllListPage(userNo,ApiTool.getStartPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize)),Integer.parseInt(pageSize));
        allListPage.forEach(data->{
            data.put("changeDate", TimeUtil.getChatTimeStr(((Date)data.get("changeDate")).getTime()));
        });
        responseData.setData(allListPage);
        return responseData;
    }

    /**
     * 添加用户资金明细
     * @param userAccountLog    用户资金变动对象
     * @return
     */
    @Override
    @Transactional(rollbackFor={GrilException.class})
    public ResponseData addUserAccountLog(UserAccountLog userAccountLog) {
        ResponseData responseData = new ResponseData();
        userAccountLog.setId(DataBaseTool.createId());
        userAccountLog.setChangeDate(new Date());
        int i = userAccountLogMapperWrite.insertSelective(userAccountLog);
        if (i<=0){
            throw new GrilException("添加用户资金明细失败");
        }
        return responseData;
    }

}
