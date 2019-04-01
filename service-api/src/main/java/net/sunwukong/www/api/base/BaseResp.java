package net.sunwukong.www.api.base;

import net.sunwukong.www.api.entity.ResponseData;

/**
 * 说明:公用响应消息
 *
 * @author Mick
 * @CreateDate 2018/7/9 15:09
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class BaseResp {
    private static ResponseData responseData = null;

    /**
     * 单例 响应成功
     * @return
     */
    public static ResponseData getInstance(){
        if (responseData==null){
            synchronized (BaseResp.class) {
                responseData = new ResponseData();
            }
        }
        responseData.setData(null);
        return responseData;
    }

    /**
     * 单例 响应成功
     * @return
     */
    public static ResponseData getInstance(Object o){
        ResponseData responseData = new ResponseData();
        responseData.setData(o);
        return responseData;
    }
}
