package net.sunwukong.www.user.server.config;

/**
 * 说明:接口服务地址
 *
 * @author Mick
 * CreateDate 2018/6/24/024 2:36
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class ServiceUrl {

    //运营服务地址头
    public static final String MARKETING_HEAD = "http://localhost/marketing-service/api/v1/";

    //向申请服务机构表插入数据
    public static final String ADD_MERCHAN_APPLY = MARKETING_HEAD+"merchanapply/applyMerchan";
    //注册赠送悟空币
    public static final String SEND_COIN = MARKETING_HEAD+"platformaccount/getRegisterSendWuKongCoin";
}
