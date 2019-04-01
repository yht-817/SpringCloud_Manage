package net.sunwukong.www.marketing.server.config.web;

import net.sunwukong.www.api.filter.WebFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 说明:Web配置(注意过滤器的顺序)
 *
 * @author Mick
 * CreateDate 2018/6/11 15:03
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Configuration
public class WebConfig {

    /**
     * web注册
     */
    @Bean
    public FilterRegistrationBean webFilterRegistration() {
        WebFilter webFilter = new WebFilter();
        //开启APP参数与web参数的传递
        webFilter.setEnableParamPrint(false);
        webFilter.setEnableOnParam(true);
        webFilter.setUrlExclusion(Arrays.asList("/weixinpayurlp"));
        FilterRegistrationBean registration = new FilterRegistrationBean(webFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }
}
