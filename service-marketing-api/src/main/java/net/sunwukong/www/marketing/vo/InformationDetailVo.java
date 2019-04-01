package net.sunwukong.www.marketing.vo;

import io.swagger.annotations.ApiModel;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: InformationDetailVo
 * @Author: kangdong
 * @Date: 2018/7/24 下午8:24
 * @Description: 全球新闻资讯详情
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@ApiModel(value = "InformationDetailVo",description = "全球新闻资讯详情")
public class InformationDetailVo {

    //=======================新闻资讯信息=======================
    //资讯id
    private String id;
    //资讯编码
    private String informationNo;
    //新闻资讯标题
    private String informationName;
    //新闻资讯内容
    private String informationUrl;
    //新闻资讯发布时间
    private Object releaseDate;
    //资讯模式1023：原创、转载
    private String informationMode;
    //是否收藏
    private Boolean collection;

    private int clickNum;

    private String hotState;

    //=======================作者=======================
    //发布用户编码
    private String userNo;
    //发布用户头像
    private String userHead;
    //发布用户昵称
    private String nikeName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInformationNo() {
        return informationNo;
    }

    public void setInformationNo(String informationNo) {
        this.informationNo = informationNo;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    public String getInformationUrl() {
        return informationUrl;
    }

    public void setInformationUrl(String informationUrl) {
        this.informationUrl = informationUrl;
    }

    public Object getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Object releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getInformationMode() {
        return informationMode;
    }

    public void setInformationMode(String informationMode) {
        this.informationMode = informationMode;
    }

    public Boolean getCollection() {
        return collection;
    }

    public void setCollection(Boolean collection) {
        this.collection = collection;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    public String getHotState() {
        return hotState;
    }

    public void setHotState(String hotState) {
        this.hotState = hotState;
    }
}
