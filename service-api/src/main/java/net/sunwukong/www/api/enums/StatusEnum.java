package net.sunwukong.www.api.enums;

/**
 * 说明:结果状态枚举
 *
 * @author Mick
 * CreateDate 2018/6/10/010 10:43
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public enum StatusEnum {
    PARAM_ERROR(101,"参数错误"),
    SUCCESS(200,"操作成功"),
    FAIL(401,"操作失败"),
    NOT_FOUND(404,"资源不存在"),
    SERVER_DISABLED(503,"服务不可用"),
    ERROR(500,"系统内部错误"),


    USER_EXIST(10001,"用户已存在"),
    CODE_ERROR(10002,"验证码错误"),
    CODE_TIMEOUT(10003,"验证码过期"),
    USER_OR_PASSWORD_ERROR(10004,"用户名或密码错误"),
    USER_NOT_EXIST(10005,"用户不存在"),
    CODE_SEND_FAIL(10006,"验证码发送过于频繁"),
    SIGN_ERROR(10007,"签名错误");

    private int code;
    private String msg;

    StatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



}
