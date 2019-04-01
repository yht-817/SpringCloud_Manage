package net.sunwukong.www.marketing.server.util.webpay;

import net.sunwukong.www.marketing.server.util.DecryptionFileConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WeChartConfig {

    private static final Logger logger = LoggerFactory.getLogger(WeChartConfig.class);

    /**
     * 商户APPID wxda714bf5b2252c4f
     */
    public static String APP_ID;

    /**
     * 商户账户 1412403102
     */
    public static String MCH_ID;

    /**
     * AppSercret
     */
    public static String APP_SERCRET;
    /**
     * 商户秘钥  32位，在微信商户平台中设置 58D936B61C9B624764144C64B620BE28
     */
    public static String PARTNER_KEY;

    /**
     * 预支付请求地址
     */
    public static final String PREPAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 查询订单地址
     */
    public static final String ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 关闭订单地址
     */
    public static final String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";

    /**
     * 申请退款地址
     */
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
     * 查询退款地址
     */
    public static final String REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";

    /**
     * 下载账单地址
     */
    public static final String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";

    /**
     * 服务器异步通知页面路径 http://120.79.40.28:8083/pay-manage/weichartcontroller/paynotify
     */
    public static String NOTIFY_URL;
    //public static final String NOTIFY_URL = "http://pay.ngrok.sdkinfo.com:8000/pay-manage/weichartcontroller/paynotify";

    /**
     * 页面跳转同步通知页面路径
     */
    public static String RETURN_URL = "http://xxx.com";

    /**
     * 退款通知地址
     */
    public static String REFUND_NOTIFY_URL = "http://xxx.com";

    /**
     * 退款需要证书文件，证书文件的地址
     */
    public static String REFUND_FILE_PATH = "http://xxx.com";

    /**
     * get token from server
     */
    static {
        InputStream inputStream = WeChartConfig.class.getClassLoader().getResourceAsStream("wechatpay.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        prop = DecryptionFileConf.decrypt(prop);
        APP_ID = prop.getProperty(WeChatConstant.ACCESS_KEY_ID);
        MCH_ID = prop.getProperty(WeChatConstant.ACCESS_MCH_ID);
        PARTNER_KEY = prop.getProperty(WeChatConstant.ACCESS_KEY_SECRET);
        NOTIFY_URL = prop.getProperty(WeChatConstant.ACCESS_NOTIFY_URL);
    }
}
