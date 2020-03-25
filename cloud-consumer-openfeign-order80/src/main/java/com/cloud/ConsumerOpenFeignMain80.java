package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerOpenFeignMain80 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerOpenFeignMain80.class, args);
    }
}
