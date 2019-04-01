package net.sunwukong.www.api.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtils {

    public Properties getProperties(String src){
        Properties prop = new Properties();
        try{
            InputStream in = getClass().getResourceAsStream(src);
            prop.load(new InputStreamReader(in, "utf-8"));
            in.close();
        }catch (Exception e){
            return null;
        }
        return prop;
    }
}
