package net.sunwukong.www.marketing.bean;

public class ServiceScopeOne {
    private String id;

    private String scopeOneNo;

    private String scopeOneName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getScopeOneNo() {
        return scopeOneNo;
    }

    public void setScopeOneNo(String scopeOneNo) {
        this.scopeOneNo = scopeOneNo == null ? null : scopeOneNo.trim();
    }

    public String getScopeOneName() {
        return scopeOneName;
    }

    public void setScopeOneName(String scopeOneName) {
        this.scopeOneName = scopeOneName == null ? null : scopeOneName.trim();
    }
}