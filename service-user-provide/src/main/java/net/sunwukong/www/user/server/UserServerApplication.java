package net.sunwukong.www.user.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient     //表明自己是一个EurekaClient
public class UserServerApplication/* extends SpringBootServletInitializer */{

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UserServerApplication.class);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

    /*@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("10240KB"); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize("102400KB");
        //临时目录
        factory.setLocation("d:/temp");
        return factory.createMultipartConfig();
    }*/
}
