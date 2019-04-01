package net.sunwukong.www.base.sms.enums;

/**
 * 说明:模板代码
 *
 * @author Mick
 * @CreateDate 2018/6/23 17:39
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public enum SMSTemplateEnum {
    SMS_143863410("SMS_143863410","悟空券购买提醒"),
    SMS_143868336("SMS_143868336","悟空券提醒"),
    SMS_141200869("SMS_141200869","悟空券"),
    SMS_142946730("SMS_142946730","悟空券-多变量"),
    SMS_136387226("SMS_136387226","通用模板"),
    SMS_136392221("SMS_136392221","密码重置"),
    SMS_136397187("SMS_136397187","注册模板"),
    SMS_142951710("SMS_142951710","注册成功通知");

    /**
     * 代码模板
     */
    private String code;
    /**
     * 描述
     */
    private String desc;

    SMSTemplateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
