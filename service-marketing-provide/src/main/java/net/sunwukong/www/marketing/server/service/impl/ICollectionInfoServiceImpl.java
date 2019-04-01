package net.sunwukong.www.marketing.server.service.impl;

import net.sunwukong.www.api.base.BaseResp;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.enums.SysCode;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.marketing.bean.CollectionInfo;
import net.sunwukong.www.marketing.server.dao.CollectionInfoMapper;
import net.sunwukong.www.marketing.server.dao.UserInfoMapper;
import net.sunwukong.www.marketing.server.service.ICollectionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明:收藏信息服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/6/22 12:07
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class ICollectionInfoServiceImpl implements ICollectionInfoService {

    private static final Logger log = LoggerFactory.getLogger(ICollectionInfoServiceImpl.class);

    @Autowired
    CollectionInfoMapper collectionInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 获取用户收藏页面
     * @param userNo    用户编码
     * @param pageNo    当前页面
     * @param pageSize  页面长度
     * @param collectionType  收藏类型
     * @return
     */
    @Override
    public ResponseData getUserCollectionInfoPage(String userNo, int pageNo, int pageSize,String collectionType) {
        ResponseData responseData = new ResponseData();
        int start = ApiTool.getStartPage(pageNo, pageSize);
        List<Map<String,String>> byUserNoPage = null;
        if (collectionType.equals(SysCode.CODE_10110001.getCode())){
            byUserNoPage = collectionInfoMapper.findByUserNoPageInformation(userNo,start,pageSize);
        }else {
            byUserNoPage = collectionInfoMapper.findByUserNoPageChoice(userNo,start,pageSize);
        }

        responseData.setData(byUserNoPage);
        return responseData;
    }


    /**
     * 设置用户收藏
     * @param data
     * @return
     */
    @Override
    public ResponseData setUserCollection(Map<String,String> data) {
        //判断是收藏还是取消
        boolean isCollection = Boolean.parseBoolean(data.get("isCollection"));
        //查询数据库是否有这条记录
        CollectionInfo byUserNoAndContentNo = collectionInfoMapper.findByUserNoAndContentNo(data.get("userNo"), data.get("contentNo"));
        if (isCollection){
            //收藏
            if (byUserNoAndContentNo!=null){
                throw new GrilException("你已收藏该条信息，无需再次收藏！");
            }
            CollectionInfo collectionInfo = new CollectionInfo();
            collectionInfo.setUserNo(data.get("userNo"));
            collectionInfo.setContentNo(data.get("contentNo"));
            collectionInfo.setCollectionType(data.get("collectionType"));
            return addCollectionInfo(collectionInfo);

        }else {
            //取消
            if (byUserNoAndContentNo==null){
                throw new GrilException("你未收藏该条信息，无需取消收藏！");
            }
            collectionInfoMapper.deleteByPrimaryKey(byUserNoAndContentNo.getId());
        }
        return BaseResp.getInstance();
    }

    @Override
    public ResponseData addCollectionInfo(CollectionInfo collectionInfo) {
        collectionInfo.setId(DataBaseTool.createId());
        collectionInfo.setCollectionDate(new Date());
        int i = collectionInfoMapper.insert(collectionInfo);
        if (i<=0){
            throw new GrilException("添加用户收藏失败");
        }
        return BaseResp.getInstance();
    }
}
