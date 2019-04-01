package net.sunwukong.www.api.crypto;

import com.sdkinfo.www.core.codec.Base64;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * 类说明:配置文件控制
 *
 * @author yangzhuang
 * @create 2018-03-28 15:47
 * @Email ：ideacoding@163.com
 **/
public class PropertyControl {

    /**
     * 配置文件解密
     * @param props 资源文件
     * @return  返回解密后的文件
     */
    public static Properties decrypt(Properties props){
        try {
            Set keyValue = props.keySet();
            for (Iterator it = keyValue.iterator(); it.hasNext();)
            {
                String key = (String) it.next();
                if (key!=null){
                    props.setProperty(key,
                            Base64.decodeStr(props.getProperty(key)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }

    /**
     * 配置文件解密
     * @param props 资源文件
     * @param isok  是否解密：true 解密    false 不解密
     * @return      返回处理后的文件
     */
    public static Properties decrypt(Properties props, boolean isok){
        if (isok){
            Set<Object> keys = props.keySet();
            keys.forEach(key->{
                String k = String.valueOf(key);
                if (k!=null){
                    try {
                        props.setProperty(k,Base64.decodeStr(props.getProperty(k)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return props;
        }
        return props;
    }
}
