package net.sunwukong.www.user.server.dbconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 说明:
 *
 * @author Mick
 * CreateDate 2018/6/7 14:19
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
@MapperScan(basePackages= UserCenterReadSessionFactory.PACKAGE, sqlSessionFactoryRef = "userCenterReadSqlSessionFactory")
public class UserCenterReadSessionFactory {
    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "net.sunwukong.www.user.server.dao.usercenter.read";
    static final String MAPPER_LOCATION = "classpath:net/sunwukong/www/user/server/dao/usercenter/read/mapping/*.xml";

    @Autowired
    @Qualifier("userCenterReadDataSource")
    private DataSource dataSource;

    @Bean(name = "userCenterReadTransactionManager")
    public DataSourceTransactionManager userCenterReadTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "userCenterReadSqlSessionFactory")
    public SqlSessionFactory userCenterReadSqlSessionFactory(@Qualifier("userCenterReadDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        //设置mybatis-config.xml配置文件位置
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource("classpath:/mybatis-config.xml"));
        return sessionFactory.getObject();
    }
}
