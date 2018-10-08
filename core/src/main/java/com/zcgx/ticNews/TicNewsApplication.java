package com.zcgx.ticNews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class TicNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicNewsApplication.class, args);
	}
}
