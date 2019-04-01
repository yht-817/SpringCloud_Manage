package net.sunwukong.www.chat.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient     //表明自己是一个EurekaClient
public class ServiceChatProvideApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceChatProvideApplication.class, args);
	}
}
