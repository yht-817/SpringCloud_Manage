package net.sunwukong.www.marketing.vo;

import java.math.BigDecimal;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ChoiceInfoListVo
 * @Author: kangdong
 * @Date: 2018/7/11 下午6:02
 * @Description: 首页全球精选显示列表
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 */
public class ChoiceInfoListVo {


    private String id;

    //资源编码
    private String resourceNo;

    //资源名称
    private String resourceName;

    //资源展示图片
    private String photo;

    //购买金额
    private BigDecimal payAmount;

    //优惠卷原价
    private BigDecimal costAmount;

    //单位
    private String unit;

    //choiceType 精选类型：广告10350001，现金券10350002，商品购买10350003
    private String choiceType;

    //是否已收藏
    private Boolean collection;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceNo() {
        return resourceNo;
    }

    public void setResourceNo(String resourceNo) {
        this.resourceNo = resourceNo;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(String choiceType) {
        this.choiceType = choiceType;
    }

    public Boolean getCollection() {
        return collection;
    }

    public void setCollection(Boolean collection) {
        this.collection = collection;
    }
}
