package net.sunwukong.www.chat.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient  //向服务中心注册
@EnableFeignClients     //开启Feign的功能
@SpringBootApplication
public class ServiceChatConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceChatConsumerApplication.class, args);
	}
}
