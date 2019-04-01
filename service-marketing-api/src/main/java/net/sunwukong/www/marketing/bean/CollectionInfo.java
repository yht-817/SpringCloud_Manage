package net.sunwukong.www.marketing.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "CollectionInfo",description = "用户收藏实体")
public class CollectionInfo {
    @ApiModelProperty(dataType = "String",name = "id",value = "收藏ID")
    private String id;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "collectionType",value = "收藏类型 资讯收藏:10110001 精选收藏:10110002")
    private String collectionType;

    @ApiModelProperty(dataType = "String",name = "contentNo",value = "内容编码")
    private String contentNo;

    @ApiModelProperty(dataType = "String",name = "collectionDate",value = "收藏日期")
    private Date collectionDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType == null ? null : collectionType.trim();
    }

    public String getContentNo() {
        return contentNo;
    }

    public void setContentNo(String contentNo) {
        this.contentNo = contentNo == null ? null : contentNo.trim();
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }
}