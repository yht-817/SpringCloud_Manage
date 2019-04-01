package net.sunwukong.www.user.server.model;

import java.util.Date;

public class MobileVersion {
    private String id;

    private String appName;

    private String appVersion;

    private String appType;

    private String appFilePath;

    private String appQrcodePath;

    private String appLog;

    private Date appTime;

    private String constraints;

    private String targetSize;

    private String updateSwitch;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getAppFilePath() {
        return appFilePath;
    }

    public void setAppFilePath(String appFilePath) {
        this.appFilePath = appFilePath == null ? null : appFilePath.trim();
    }

    public String getAppQrcodePath() {
        return appQrcodePath;
    }

    public void setAppQrcodePath(String appQrcodePath) {
        this.appQrcodePath = appQrcodePath == null ? null : appQrcodePath.trim();
    }

    public String getAppLog() {
        return appLog;
    }

    public void setAppLog(String appLog) {
        this.appLog = appLog == null ? null : appLog.trim();
    }

    public Date getAppTime() {
        return appTime;
    }

    public void setAppTime(Date appTime) {
        this.appTime = appTime;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public String getTargetSize() {
        return targetSize;
    }

    public void setTargetSize(String targetSize) {
        this.targetSize = targetSize == null ? null : targetSize.trim();
    }

    public String getUpdateSwitch() {
        return updateSwitch;
    }

    public void setUpdateSwitch(String updateSwitch) {
        this.updateSwitch = updateSwitch == null ? null : updateSwitch.trim();
    }
}