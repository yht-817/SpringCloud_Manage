package net.sunwukong.www.user.bean;

public class UserScope {
    private String id;

    private String userNo;

    private String scopeOneNo;

    private String scopeTwoNo;

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

    public String getScopeOneNo() {
        return scopeOneNo;
    }

    public void setScopeOneNo(String scopeOneNo) {
        this.scopeOneNo = scopeOneNo == null ? null : scopeOneNo.trim();
    }

    public String getScopeTwoNo() {
        return scopeTwoNo;
    }

    public void setScopeTwoNo(String scopeTwoNo) {
        this.scopeTwoNo = scopeTwoNo == null ? null : scopeTwoNo.trim();
    }
}