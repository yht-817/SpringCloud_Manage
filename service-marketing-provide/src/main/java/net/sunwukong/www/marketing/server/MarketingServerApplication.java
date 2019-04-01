package net.sunwukong.www.marketing.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient     //表明自己是一个EurekaClient
public class MarketingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketingServerApplication.class, args);
    }
}
