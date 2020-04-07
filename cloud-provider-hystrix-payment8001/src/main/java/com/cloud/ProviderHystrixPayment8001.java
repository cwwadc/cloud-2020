package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient // eureka客户端
@EnableCircuitBreaker //要在SpringCloud中使用断路器或者服务降级，必须加上@EnableCircuitBreaker注解
public class ProviderHystrixPayment8001 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderHystrixPayment8001.class,args);
    }
}
