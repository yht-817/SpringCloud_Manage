package net.sunwukong.www.user.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:APP版本信息响应
 *
 * @author swk
 * @CreateDate 2018/8/20 18:40
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "MobileVersionOutput",description = "APP版本信息响应")
public class MobileVersionOutput {

    @ApiModelProperty(dataType = "String",name = "update",value = "是否可以更新")
    private boolean update;
    @ApiModelProperty(dataType = "String",name = "newVersion",value = "新版本号")
    private String newVersion;
    @ApiModelProperty(dataType = "String",name = "apkFileUrl",value = "apk下载地址")
    private String apkFileUrl;
    @ApiModelProperty(dataType = "String",name = "updateLog",value = "更新内容")
    private String updateLog;
    @ApiModelProperty(dataType = "String",name = "targetSize",value = "apk大小")
    private String targetSize;
    @ApiModelProperty(dataType = "String",name = "sign",value = "签名")
    private String sign;
    @ApiModelProperty(dataType = "String",name = "constraints",value = "是否强制更新")
    private boolean constraints;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    public String getApkFileUrl() {
        return apkFileUrl;
    }

    public void setApkFileUrl(String apkFileUrl) {
        this.apkFileUrl = apkFileUrl;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public String getTargetSize() {
        return targetSize;
    }

    public void setTargetSize(String targetSize) {
        this.targetSize = targetSize;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean isConstraints() {
        return constraints;
    }

    public void setConstraints(boolean constraints) {
        this.constraints = constraints;
    }
}
