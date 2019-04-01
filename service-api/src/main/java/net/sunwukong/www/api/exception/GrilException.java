package net.sunwukong.www.api.exception;

/**
 * 类说明:自定义异常
 *
 * @author ideacoding
 * create 2018-04-11 11:32
 * Email ：ideacoding@163.com
 **/
public class GrilException extends RuntimeException{
    /**
     * 异常代码
     */
    private int code;
    /**
     * 异常消息
     */
    private String msg;

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

    public GrilException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public GrilException(String msg) {
        super(msg);
        this.code = 500;
        this.msg = msg;
    }
}
