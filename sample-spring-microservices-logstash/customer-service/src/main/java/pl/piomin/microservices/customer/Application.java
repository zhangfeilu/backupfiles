package pl.piomin.microservices.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@RibbonClient(name = "commercialAuto-service", configuration = RibbonConfiguration.class)
public class Application {
//COMMERCIALAUTO-SERVICE
//commercialAuto-service
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
