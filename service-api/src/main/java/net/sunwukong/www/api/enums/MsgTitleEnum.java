package net.sunwukong.www.api.enums;

/**
 * 说明:服务通知类型
 *
 * @author Mick
 * @CreateDate 2018/7/12 19:56
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public enum MsgTitleEnum {
    CODE_10001("10001", "审核通过"),
    CODE_10002("10002", "审核未通过"),
    CODE_10003("10003", "服务完成通知"),
    CODE_10004("10004", "您有一单服务待结单"),
    CODE_10005("10005", "抢单成功通知"),
    CODE_10006("10006", "悟空币变动通知"),
    CODE_10007("10007", "群申请"),
    CODE_10008("10008", "群邀请"),
    CODE_10009("10009", "群消息"),
    CODE_10010("10010", "好友申请"),
    CODE_10011("10011", "您有待支付的订单"),
    CODE_10012("10012", "服务开始通知"),
    CODE_10013("10013", "需求单被抢通知"),
    CODE_10014("10014", "蟠桃充值提醒"),
    CODE_10015("10015", "评论提醒"),
    CODE_10016("10016", "点赞提醒"),
    ;


    private String code;
    private String msg;

    MsgTitleEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
