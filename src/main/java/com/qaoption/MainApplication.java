package com.qaoption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//此注解表示SpringBoot启动类
@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class MainApplication {
	
	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
	
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
