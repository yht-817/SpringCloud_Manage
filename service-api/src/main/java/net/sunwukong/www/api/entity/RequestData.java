package net.sunwukong.www.api.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类说明:数据请求实体
 *
 * @author ideacoding
 * @create 2018-01-18 11:33
 * @Email ：ideacoding@163.com
 **/
@ApiModel(value = "RequestData",description = "数据请求实体")
public class RequestData<T> {
    //@JsonProperty(value = "OS")
    @ApiModelProperty(dataType = "String",name = "os",value = "客户端类型")
    private String os;
    @ApiModelProperty(dataType = "String",name = "v",value = "版本")
    private String v;
    @ApiModelProperty(dataType = "T",name = "data",value = "变化参数")
    private T data;
    @ApiModelProperty(dataType = "String",name = "userId",value = "用户ID")
    private String userNo;
    @ApiModelProperty(dataType = "String",name = "lang",value = "语言 zh=中文 en=英文")
    private String lang = "zh";

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public RequestData(String os, String v, T data, String userNo, String lang) {
        this.os = os;
        this.v = v;
        this.data = data;
        this.userNo = userNo;
        this.lang = lang;
    }

    public RequestData() {
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "os='" + os + '\'' +
                ", v='" + v + '\'' +
                ", data=" + data +
                ", userNo='" + userNo + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
