package net.sunwukong.www.marketing.server;

import com.aliyuncs.exceptions.ClientException;
import net.sunwukong.www.base.sms.dao.AliSMSDao;
import net.sunwukong.www.base.sms.enums.SMSTemplateEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明:
 *
 * @author Mick
 * @CreateDate 2018/7/24 15:38
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class Main {

    public static void main(String[] args) throws ClientException {
        //发送短信通知
        Map<String,String> content = new HashMap<>();
        content.put("order","1809031000000000001553595191");
        content.put("phone","15756275361");
        AliSMSDao.sendSms("15756275361",content, SMSTemplateEnum.SMS_143868336);
    }
//    public static void main(String[] args) {
//        String card = "6227007200129897790";
//        System.out.println("      card: " + card);
//        System.out.println("check code: " + getBankCardCheckCode(card));
//        System.out.println("是否为银行卡:" + checkBankCard(card));
//    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }


}
