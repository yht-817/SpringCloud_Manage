package net.sunwukong.www.marketing.server.service;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.marketing.bean.Information;
import net.sunwukong.www.marketing.dto.SaveInformationInput;

import java.io.IOException;

/**
 * 说明:资讯服务接口
 *
 * @author Mick
 * @CreateDate 2018/7/19 14:49
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface IInformationService {

    ResponseData addInformation(Information information);

    /**
     * 根据资讯编码获取资讯详情
     *
     * @param informationNo 资讯编码
     * @param userNo        用户编码
     * @return
     */
    ResponseData getDetails(String informationNo, String userNo);


    /**
     * 增加资讯点击数
     *
     * @param informationNo
     * @return
     */
    boolean updateClickNumPlus(String informationNo);


    /**
     * 获取URL链接内容
     *
     * @param url  链接内容
     * @param type 文章类型(1:微信)
     * @return
     */
    ResponseData getArticleContent(String url, String type) throws IOException;

    /**
     * 发布资讯
     *
     * @param data
     * @return
     */
    ResponseData saveInformation(SaveInformationInput data);

    /**
     * 获取资讯分类
     *
     * @return
     */
    ResponseData getInformationClass();

    /**
     * 删除资讯
     * @param informationNo 资讯编码
     * @return
     */
    ResponseData delInformation(String informationNo);

    /**
     * 修改资讯
     * @param informationNo     资讯编码
     * @param informationType   资讯分类
     * @param informationClass  资讯类型
     * @param informationMode   资讯模式
     * @param informationName   资讯名称
     * @param informationPhoto  资讯图片
     * @param photoSizeType     布局方式
     * @param informationContent    资讯内容
     * @return
     */
    ResponseData updateInformation(String informationNo, String informationType, String informationClass, String informationMode, String informationName, String informationPhoto, String photoSizeType, String informationContent);

    /**
     * 分页获取资讯列表
     * @param informationClass  资讯分类
     * @param start             开始位置
     * @param pageSize          检索长度
     * @param keyword           关键字
     * @return
     */
    ResponseData getPageList(String informationClass, int start, int pageSize, String keyword);
}
