package net.sunwukong.www.marketing.server.util;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.sdkinfo.www.pay.alipay.constant.AliConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 支付宝参数配置
 */
public class AlipayConfig {
    private static final Logger log = LoggerFactory.getLogger(AlipayConfig.class);
    /**
     * 网关地址
     */
    public static String URL;

    /**
     * APPID
     */
    public static String KEY_ID;

    /**
     * 私钥
     */
    public static String PRIVATE_KEY;

    /**
     * 支付宝公钥
     */
    public static String PUBLIC_KEY;

    /**
     * 签名算法类型(根据生成私钥的算法,RSA2或RSA)
     */
    public static String SIGNTYPE;

    /**
     * 请求数据格式
     */
    public static String FORMAT;

    /**
     * 编码集
     */
    public static String CHARSET;

    /**
     * 商家(卖家)支付宝用户ID
     */
    public static String SELLERID;

    /**
     * 门店编号
     */
    public static String STOREID;

    /**
     * 超时
     */
    public static String TIMEOUT_EXPRESS;

    /**
     * 异步通知地址
     */
    public static String NOTIFY_URL;

    /**
     * 同步通知地址
     */
    public static String RETURN_URL;

    /**
     * 连接支付宝客户端对象
     */
    private static AlipayClient alipayClient = null;

    /**
     * 对配置文件加密的数据进行还原进行连接
     */
    static {
        InputStream inputStream = AlipayConfig.class.getClassLoader().getResourceAsStream("alipay.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        //prop = DecryptionFileConf.decrypt(prop);
        URL = prop.getProperty(AliPayKeyConf.ACCESS_URL);       // 网关请求地址
        KEY_ID = prop.getProperty(AliPayKeyConf.ACCESS_KEY_ID); // 用户ID
        PRIVATE_KEY = prop.getProperty(AliPayKeyConf.ACCESS_PRIVATE_KEY); // 私钥
        PUBLIC_KEY = prop.getProperty(AliPayKeyConf.ACCESS_PUBLIC_KEY);   // 公钥
        SIGNTYPE = prop.getProperty(AliPayKeyConf.ACCESS_SIGNTYPE);  // 签名算法
        FORMAT = prop.getProperty(AliPayKeyConf.ACCESS_FORMAT);      // 数据请求格式
        CHARSET = prop.getProperty(AliPayKeyConf.ACCESS_CHARSET);    // 数据集编码格式
        STOREID = prop.getProperty(AliPayKeyConf.ACCESS_STORESNO);   // 支付宝门店编号
        NOTIFY_URL = prop.getProperty(AliConstant.ACCESS_NOTIFY_URL);// 异步通知的连接
    }

    /**
     * 获得初始化的AlipayClient
     *
     * @return 支付宝客户端
     */
    public static AlipayClient getAlipayClient() {
        if (alipayClient == null) {
            synchronized (AlipayConfig.class) {
                if (null == alipayClient) {
                    alipayClient = new DefaultAlipayClient(URL, KEY_ID, PRIVATE_KEY, FORMAT, CHARSET, PUBLIC_KEY, SIGNTYPE);
                }
            }
        }
        return alipayClient;
    }
}
