package net.sunwukong.www.marketing.vo;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: MyDemandServerListVo
 * @Author: kangdong
 * @Date: 2018/6/26 下午2:30
 * @Description: 我的需求或服务的列表
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
public class MyDemandServerListVo {

    //id标识
    private String id;
    //用户id
    //private String userId;
    //需求单号
    private String demandNo;
    //需求特殊说明
    private String specialRemark;
    //服务时间
    private String dateTime;
    //服务状态码
    private String stateCode;
    //服务状态名
    private String stateName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDemandNo() {
        return demandNo;
    }

    public void setDemandNo(String demandNo) {
        this.demandNo = demandNo;
    }

    public String getSpecialRemark() {
        return specialRemark;
    }

    public void setSpecialRemark(String specialRemark) {
        this.specialRemark = specialRemark;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
