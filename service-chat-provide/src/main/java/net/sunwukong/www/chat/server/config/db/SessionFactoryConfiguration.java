package net.sunwukong.www.chat.server.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明:数据库配置
 *
 * @author Mick
 * CreateDate 2018/6/10/010 9:18
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
@MapperScan(basePackages = SessionFactoryConfiguration.PACKAGE, sqlSessionFactoryRef = "chatMessSqlSessionFactory")
public class SessionFactoryConfiguration {
    static final String PACKAGE = "net.sunwukong.www.chat.server.dao";
    static final String MAPPER_LOCATION = "classpath:/mapping/*.xml";
    static final String MAPPER_CONFIG = "classpath:/mybatis-config.xml";

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Value("${spring.datasource.readSize}")
    private String dataSourceSize;

    @Resource(name = "writeDataSource")
    private DataSource dataSource;

    @Resource(name = "readDataSources")
    private List<DataSource> readDataSources;

    @Bean(name = "chatMessTransactionManager")
    public DataSourceTransactionManager chatMessWriteTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "chatMessSqlSessionFactory")
    public SqlSessionFactory chatMessSqlSessionFactory(@Qualifier("writeDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(SessionFactoryConfiguration.MAPPER_LOCATION));
        //设置mybatis-config.xml配置文件位置
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(MAPPER_CONFIG));
        return sessionFactory.getObject();
    }

    /**
     * 有多少个数据源就要配置多少个bean
     * @return
     */
    @Bean
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
        int size = Integer.parseInt(dataSourceSize);
        MaketingAbstractRoutingDataSource proxy = new MaketingAbstractRoutingDataSource(size);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // DataSource writeDataSource = SpringContextHolder.getBean("writeDataSource");
        // 写
        targetDataSources.put(DataSourceType.write.getType(),dataSource);
        // targetDataSources.put(DataSourceType.read.getType(),readDataSource);
        //多个读数据库时
        for (int i = 0; i < size; i++) {
            targetDataSources.put(i, readDataSources.get(i));
        }
        proxy.setDefaultTargetDataSource(dataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }
}
