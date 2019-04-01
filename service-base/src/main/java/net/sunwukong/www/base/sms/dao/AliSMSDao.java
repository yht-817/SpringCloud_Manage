package net.sunwukong.www.base.sms.dao;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.sdkinfo.www.log.StaticLog;
import net.sunwukong.www.api.util.JSONUtil;
import net.sunwukong.www.base.sms.config.SMSConfig;
import net.sunwukong.www.base.sms.enums.SMSTemplateEnum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 说明:阿里云短信接口
 *
 * @author Mick
 * CreateDate 2018/6/4 14:42
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class AliSMSDao {

    /**
     * 发送短信
     * @param phones    电话号码
     * @param content   通知内容
     * @param template  模板ID
     * @return  响应短信发送结果
     * @throws ClientException  异常处理
     */
    public static SendSmsResponse sendSms(String phones, Map<String,String> content, SMSTemplateEnum template) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SMSConfig.getProduct(), SMSConfig.getDomain());
        IAcsClient acsClient = new DefaultAcsClient(SMSConfig.getIClientProfile());

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phones);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("GoKong");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(template.getCode());

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(JSONUtil.map2json(content));

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        StaticLog.error("阿里云发送短信：{}",sendSmsResponse.getMessage());
        return sendSmsResponse;
    }

    /**
     * 查询短信详情
     * @param bizId     短信流水号
     * @return  返回短信详情
     * @throws ClientException  异常处理
     */
    public static QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SMSConfig.getProduct(), SMSConfig.getDomain());
        IAcsClient acsClient = new DefaultAcsClient(SMSConfig.getIClientProfile());

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber("15000000000");
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }
}
