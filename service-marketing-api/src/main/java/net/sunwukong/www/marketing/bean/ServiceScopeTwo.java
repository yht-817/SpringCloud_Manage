package net.sunwukong.www.marketing.bean;

public class ServiceScopeTwo {
    private String id;

    private String scopeOneNo;

    private String scopeTwoNo;

    private String scopeTwoName;

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

    public String getScopeTwoNo() {
        return scopeTwoNo;
    }

    public void setScopeTwoNo(String scopeTwoNo) {
        this.scopeTwoNo = scopeTwoNo == null ? null : scopeTwoNo.trim();
    }

    public String getScopeTwoName() {
        return scopeTwoName;
    }

    public void setScopeTwoName(String scopeTwoName) {
        this.scopeTwoName = scopeTwoName == null ? null : scopeTwoName.trim();
    }
}