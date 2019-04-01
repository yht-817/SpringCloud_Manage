package net.sunwukong.www.user.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 说明:用户注册入参
 *
 * @author swk
 * @CreateDate 2018/8/24 14:47
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "RegisterInput",description = "用户注册入参")
public class RegisterInput {

    @ApiModelProperty(dataType = "String", name = "account", value = "用户账号")
    @NotNull(message = "用户账号不能为空")
    private String account;
    @ApiModelProperty(dataType = "String", name = "accountType", value = "账号类型（1电话号码 2邮箱）")
    @NotNull(message = "账号类型不能为空")
    private String accountType;
    @ApiModelProperty(dataType = "String",name = "code",value = "验证码")
    @NotNull(message = "验证码不能为空")
    private String code;
    @ApiModelProperty(dataType = "String",name = "nikeName",value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String nikeName;
    @ApiModelProperty(dataType = "String",name = "password",value = "密码")
    @NotNull(message = "密码不能为空")
    private String password;
    @ApiModelProperty(dataType = "String",name = "phoneType",value = "手机型号")
    private String phoneType;
    @ApiModelProperty(dataType = "String",name = "deviceNo",value = "设备编码")
    private String deviceNo;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
