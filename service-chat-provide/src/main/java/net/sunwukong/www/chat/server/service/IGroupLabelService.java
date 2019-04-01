package net.sunwukong.www.chat.server.service;

import net.sunwukong.www.api.entity.ResponseData;

/**
 * 说明:标签服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/10 16:18
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IGroupLabelService {
    /**
     * 获取标签列表
     * @return
     */
    ResponseData queryLabels();
}
