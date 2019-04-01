package net.sunwukong.www.user.server.dbconfig;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 说明:
 *
 * @author Mick
 * CreateDate 2018/6/15 15:08
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Configuration
public class DataSourceConfig {
    private static Logger log = LoggerFactory.getLogger(DataSourceConfig.class);
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "userCenterWriteDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.write.user-center")
    public DataSource userCenterWriteDataSource() {
        log.info("-------------------- userCenterWriteDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "userCenterReadDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read.user-center")
    public DataSource userCenterReadDataSource() {
        log.info("-------------------- userCenterReadDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "marketingWriteDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.write.marketing")
    public DataSource marketingWriteDataSource() {
        log.info("-------------------- marketingWriteDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "chatMessWriteDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.write.chat-mess")
    public DataSource chatMessWriteDataSource() {
        log.info("-------------------- chatMessWriteDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "chatMessReadDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read.chat-mess")
    public DataSource chatMessReadDataSource() {
        log.info("-------------------- chatMessReadDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "marketingReadDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read.marketing")
    public DataSource marketingReadDataSource() {
        log.info("-------------------- marketingReadDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }



    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", "admin");
        reg.addInitParameter("loginPassword", "admin");
        reg.addInitParameter("logSlowSql", "true");
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }
}
