package net.sunwukong.www.user.vo;

import net.sunwukong.www.user.bean.UserCategory;
import net.sunwukong.www.user.bean.UserCity;
import net.sunwukong.www.user.bean.UserScope;

import java.util.List;

/**
 * 说明:
 *
 * @author Mick
 * CreateDate 2018/6/28/028 20:26
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class ServerDetails {

    private String userNo;
    private String userType;
    private List<UserCategory> userCategories;
    private List<UserCity> userCitys;
    private List<UserScope> userScopes;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public List<UserCategory> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(List<UserCategory> userCategories) {
        this.userCategories = userCategories;
    }

    public List<UserCity> getUserCitys() {
        return userCitys;
    }

    public void setUserCitys(List<UserCity> userCitys) {
        this.userCitys = userCitys;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<UserScope> getUserScopes() {
        return userScopes;
    }

    public void setUserScopes(List<UserScope> userScopes) {
        this.userScopes = userScopes;
    }
}
