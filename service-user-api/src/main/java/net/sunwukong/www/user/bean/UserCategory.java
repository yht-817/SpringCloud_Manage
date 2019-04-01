package net.sunwukong.www.user.bean;

public class UserCategory {
    private String id;

    private String userNo;

    private String categoryOneNo;

    private String categoryTwoNo;

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

    public String getCategoryOneNo() {
        return categoryOneNo;
    }

    public void setCategoryOneNo(String categoryOneNo) {
        this.categoryOneNo = categoryOneNo == null ? null : categoryOneNo.trim();
    }

    public String getCategoryTwoNo() {
        return categoryTwoNo;
    }

    public void setCategoryTwoNo(String categoryTwoNo) {
        this.categoryTwoNo = categoryTwoNo == null ? null : categoryTwoNo.trim();
    }
}