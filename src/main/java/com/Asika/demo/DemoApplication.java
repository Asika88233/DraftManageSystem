package com.Asika.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:config/spring-context-kaptcha.xml"})
@SpringBootApplication(scanBasePackages = "com.Asika.demo")
@MapperScan("com.Asika.demo.mapper")
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
