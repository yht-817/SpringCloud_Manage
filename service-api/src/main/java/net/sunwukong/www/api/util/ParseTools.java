package net.sunwukong.www.api.util;

import com.sdkinfo.www.pay.wechatpay.util.WXPayUtil;
import net.sunwukong.www.api.entity.RequestData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 解析回调的（微信、支付宝）传参的解析公用方法
 */

public class ParseTools {

    // 支付宝的公用解析方法
    public static RequestData<Map<String, String>> analyticAlipay(HttpServletRequest request) {
        RequestData<Map<String, String>> requestData = new RequestData<>();
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        System.out.println("params转换字符串进行数据查看：" + params.toString());
        requestData.setData(params);
        return requestData;
    }

    // 微信回调公用解析参数
    public static Map<String, String> wxParsing(HttpServletRequest request, HttpServletResponse response) {
        try {
            BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuffer tStringBuffer = new StringBuffer();
            String sTempOneLine = new String("");
            while ((sTempOneLine = tBufferedReader.readLine()) != null) {
                tStringBuffer.append(sTempOneLine);
            }
            String result = tStringBuffer.toString();
            tBufferedReader.close();
            System.out.println("微信支付通知结果：" + result);
            // 解析微信通知返回的信息
            Map<String, String> datamsgmap = WXPayUtil.xmlToMap(result); // 把xml信息转换成map对象
            System.out.println("微信调用异步返回当前充值的结果" + datamsgmap.toString());
            return datamsgmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
