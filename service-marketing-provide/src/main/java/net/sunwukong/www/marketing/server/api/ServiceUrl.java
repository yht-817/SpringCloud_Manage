package net.sunwukong.www.marketing.server.api;

public class ServiceUrl {

    //用户服务地址头
    public static final String MARKETING_HEAD = "http://localhost/user-service/api/v1/";

    //获取机构申请信息
    public static final String GET_MERCHAN_APPLY_INFO = MARKETING_HEAD+"applyserver/getDetails";

    //向用户表更新数据
    public static final String ADD_USER_INFO = MARKETING_HEAD+"user/updatedata";

    //获取用户账户信息
    public static final String GETUSERACCOUNT = MARKETING_HEAD + "useraccount/getuseraccount";

    //修改用户账户金额
    public static final String UPDATE_USER_ACCOUNT = MARKETING_HEAD + "useraccount/updateuseraccount";

    //修改用户累计评分
    public static final String UPDATE_USER_EVALUATE = MARKETING_HEAD + "user/updateuserevaluate";

    //获取用户服务详情
    public static final String GET_SERVER_DETAILS = MARKETING_HEAD + "applyserver/getserverdetails";

    //保存申请服务信息
    public static final String SAVE_SERVER_DETAILS = MARKETING_HEAD + "applyserver/saveserverdetails";
}
