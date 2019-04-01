package net.sunwukong.www.api.crypto.symmetric;

import net.sunwukong.www.api.crypto.Mode;
import net.sunwukong.www.api.crypto.Padding;
import net.sunwukong.www.api.crypto.PropertyControl;
import net.sunwukong.www.api.util.PropertiesUtils;

import java.util.Properties;

/**
 * 类说明:AES加密工具类
 *
 * @author yangzhuang
 * @create 2018-03-28 14:52
 * @Email ：ideacoding@163.com
 **/
public class AESUtil {
    /**
     * 引入配置文件
     */
    private static Properties props = PropertyControl.decrypt(new PropertiesUtils().getProperties("aes.config"));

    /**
     * AES对象
     */
    public static AES aes;
    /**
     * 获得初始化的Aes
     * @return Aes对象
     */
    public static AES getAes() {
        if (aes == null) {
            synchronized (AESUtil.class) {
                if (null == aes) {
                    //StaticLog.info("key:{} vi:{}",props.getProperty("key"),props.getProperty("vi"));
                    aes = new AES(Mode.CBC, Padding.PKCS5Padding, props.getProperty("key").getBytes(), props.getProperty("vi").getBytes());
                }
            }
        }
        return aes;
    }
}
