package net.sunwukong.www.marketing.server.util;


import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * 对文件统一加解密
 */
public class DecryptionFileConf {
    public static Properties decrypt(Properties props){
        try {
            Set keyValue = props.keySet();
            for (Iterator it = keyValue.iterator(); it.hasNext();)
            {
                String key = (String) it.next();
                if (key!=null){
                    props.setProperty(key,
                            DesEncrypt.decrypt(props.getProperty(key), DesEncrypt.PASSWORD_CRYPT_KEY));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }
}
