package net.sunwukong.www.marketing.bean;

public class SysConfig {
    private String id;

    private String configCode;

    private String configRemark;

    private Integer configVal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode == null ? null : configCode.trim();
    }

    public String getConfigRemark() {
        return configRemark;
    }

    public void setConfigRemark(String configRemark) {
        this.configRemark = configRemark == null ? null : configRemark.trim();
    }

    public Integer getConfigVal() {
        return configVal;
    }

    public void setConfigVal(Integer configVal) {
        this.configVal = configVal;
    }
}