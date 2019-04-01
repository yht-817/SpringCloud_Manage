package net.sunwukong.www.base.sms.config;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.sdkinfo.www.core.util.PropertiesUtil;
import com.sdkinfo.www.setting.dialect.Props;
import net.sunwukong.www.base.sms.util.SMSConstant;

import java.util.Properties;

/**
 * 说明:阿里云短信配置
 *
 * @author Mick
 * CreateDate 2018/6/4 14:35
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class SMSConfig {
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String product = "Dysmsapi";

    /**
     * 产品域名,开发者无需替换
     */
    private static final String domain = "dysmsapi.aliyuncs.com";

    /**
     *KEY ID
     */
    private static String accessKeyId;

    /**
     *KEY SECRET
     */
    private static String accessKeySecret;

    /**
     * 签名
     */
    private static String sign;

    /**
     * 短信客户端
     */
    private static IClientProfile iClientProfile;

    static {
        Properties prop = Props.getProp("ali-sms.properties");
        Properties sms = PropertiesUtil.decrypt(prop,false);
        accessKeyId = sms.getProperty(SMSConstant.SMS_ACCESS_KEY);
        accessKeySecret = sms.getProperty(SMSConstant.SMS_SECRET_KEY);
        sign = sms.getProperty(SMSConstant.SMS_SIGN);
    }

    public static String getSign() {
        return sign;
    }

    public static String getProduct() {
        return product;
    }

    public static String getDomain() {
        return domain;
    }

    /**
     * 获得初始化的iClientProfile
     * @return 短信客户端
     */
    public static IClientProfile getIClientProfile() {
        if (iClientProfile == null) {
            synchronized (com.sdkinfo.www.sms.config.SMSConfig.class) {
                if (null == iClientProfile) {
                    iClientProfile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
                }
            }
        }
        return iClientProfile;
    }
}
