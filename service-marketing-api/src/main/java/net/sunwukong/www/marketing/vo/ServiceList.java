package net.sunwukong.www.marketing.vo;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: ServiceList
 * @Author: kangdong
 * @Date: 2018/6/29 下午5:12
 * @Description: 运营官下服务机构列表
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class ServiceList {
    //id标识
    private String id;
    //用户编码
    private String userNo;
    //用户表id
    private String userId;
    //用户名称或机构名称
    private String userName;
    //用户头像
    private String userHead;
    //用户类型，10020001普通用户，10020002城市管理者，10020003猴王服务机构
    private String userType;
    //用户等级
    private String userGrade;
    //用户星级(评分)
    private String userStar;
    //完成订单数
    private String finishCount;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserStar() {
        return userStar;
    }

    public void setUserStar(String userStar) {
        this.userStar = userStar;
    }

    public String getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(String finishCount) {
        this.finishCount = finishCount;
    }
}
