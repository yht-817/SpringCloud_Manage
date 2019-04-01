package net.sunwukong.www.user.server.service;


import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.user.bean.UserHomePage;

/**
 * 说明:个人主页服务接口
 *
 * @author Mick
 * CreateDate 2018/6/11 16:49
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public interface IUserHomePageService {

    /**
     * 修改个人主页接口
     * @param id                ID
     * @param selfEvaluation    自我介绍
     * @return                  返回响应对象
     */
    ResponseData updateData(String id, String selfEvaluation);

    /**
     * 获取个人主页信息
     * @param userNo        用户编码
     * @return              返回响应对象
     */
    ResponseData getUserHomePage(String userNo,String visitorNo);

    /**
     * 添加个人主页图片
     * @param id        用户编码
     * @param imgNo     图片下标
     * @param homePhoto 图片内容
     * @return
     */
    ResponseData addImg(String id,String imgNo,String homePhoto);

    /**
     * 删除个人主页图片
     * @param id            ID
     * @param imgNo         图片index
     * @return              返回响应对象
     */
    ResponseData delImg(String id,String imgNo);

    /**
     * 修改个人图片
     * @param id            ID
     * @param imgNo         图片index
     * @param homePhoto     图片内容byte[]
     * @return              返回响应对象
     */
    ResponseData updateImg(String id,String imgNo,String homePhoto);

    /**
     * 设置个人主页封面
     * @param userHomePage
     * @return
     */
    ResponseData setHomeCover(UserHomePage userHomePage);

    /**
     * 更新用户主页信息
     * @param userHomePage
     * @return
     */
    ResponseData updateUserHomePage(UserHomePage userHomePage);

    /**
     * 根据用户编码获取个人图片
     * @param userNo    用户编码
     * @return
     */
    UserHomePage findByUserInfo(String userNo);
}
