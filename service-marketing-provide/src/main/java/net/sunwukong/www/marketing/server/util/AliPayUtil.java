package net.sunwukong.www.marketing.server.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.sdkinfo.www.core.date.DateUtil;
import com.sdkinfo.www.core.util.StrUtil;
import com.sdkinfo.www.core.util.ToolUtil;

import java.util.Map;
import java.util.UUID;

public class AliPayUtil {
    /**
     * 验证异步通知结果
     *
     * @param params 待验证参数
     * @return 返回true or false
     */
    public static boolean alipayNotify(Map<String, String> params) {
        // 计算得出通知验证结果
        boolean verify_result = false;
        try {
            verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.PUBLIC_KEY,AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return verify_result;
    }


    /**
     * 生成 uuid， 即用来标识一笔单，也用做 nonce_str
     *
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    /**
     * 元转化为分
     *
     * @param total_fee
     * @return
     */
    public static String yuanToSubdivision(String total_fee) {
        if (StrUtil.isNotEmpty(total_fee)) {
            return String.valueOf((new Double(Double.parseDouble(total_fee) * 100).intValue()));
        }
        return null;
    }

    /**
     * 生成商品订单号(18位)
     *
     * @return 18位商品订单编号
     */
    public static String createOrderNumber() {
        return DateUtil.getMillis() + ToolUtil.buildRandom(4);
    }

    /**
     * 生成交易订单号(20位)
     *
     * @return 20位交易订单编号
     */
    public static String createTradeOrderNumber() {
        return DateUtil.getMillis() + ToolUtil.buildRandom(6);
    }
}
