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
public class DESUtil {
    private static Properties props = PropertyControl.decrypt(new PropertiesUtils().getProperties("des.config"));
    public static DES des = new DES(Mode.CBC, Padding.PKCS5Padding, props.getProperty("key").getBytes(), props.getProperty("vi").getBytes());
}
