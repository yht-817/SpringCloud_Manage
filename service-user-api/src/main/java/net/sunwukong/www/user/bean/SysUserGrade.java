package net.sunwukong.www.user.bean;

public class SysUserGrade {
    private String id;

    private String userGrade;

    private String gradeName;

    private Integer dividingBegin;

    private Integer dividingEnd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade == null ? null : userGrade.trim();
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName == null ? null : gradeName.trim();
    }

    public Integer getDividingBegin() {
        return dividingBegin;
    }

    public void setDividingBegin(Integer dividingBegin) {
        this.dividingBegin = dividingBegin;
    }

    public Integer getDividingEnd() {
        return dividingEnd;
    }

    public void setDividingEnd(Integer dividingEnd) {
        this.dividingEnd = dividingEnd;
    }
}