package net.sunwukong.www.api.data;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明:Map数据
 *
 * @author Mick
 * CreateDate 2018/6/10/010 9:39
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class DataMap {

    /**
     * 存储验证码信息
     */
    public static Map<String,Map<String,String>> securityCodeMap = new HashMap<String, Map<String,String>>(16);

    /**
     * 存储延签数据
     */
    public static Map<String,String> tokenMap = new HashMap<String, String>(16);
}
