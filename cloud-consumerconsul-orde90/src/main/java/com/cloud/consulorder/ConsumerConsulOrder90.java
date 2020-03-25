package com.cloud.consulorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerConsulOrder90 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerConsulOrder90.class);
    }
}
