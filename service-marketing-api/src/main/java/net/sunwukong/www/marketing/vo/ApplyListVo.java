package net.sunwukong.www.marketing.vo;

import java.util.Date;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ApplyListVo
 * @Author: kangdong
 * @Date: 2018/6/29 下午4:02
 * @Description: 申请(待审核)机构列表
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class ApplyListVo {
    //id标识
    private String id;
    //申请单号
    private String applyNo;
    //用户编码
    private String userNo;
    //用户名称或机构名称
    private String userName;
    //用户头像
    private String userHead;
    //状态名称
    private String stateName;
    //时间
    private Date datetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
