package net.sunwukong.www.chat.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.chat.bean.GroupLabel;
import net.sunwukong.www.chat.server.dao.GroupLabelMapper;
import net.sunwukong.www.chat.server.service.IGroupLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明:标签服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/10 16:19
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IGroupLabelServiceImpl implements IGroupLabelService {

    @Autowired
    GroupLabelMapper groupLabelMapper;

    /**
     * 获取标签列表
     * @return
     */
    @Override
    public ResponseData queryLabels() {
        ResponseData responseData = new ResponseData();
        List<GroupLabel> groupLabels = groupLabelMapper.queryAll();
        responseData.setData(groupLabels);
        return responseData;
    }
}
