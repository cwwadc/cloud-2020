package com.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = FeignFallbackPaymentService.class) //找到哪个微服务
public interface FeignPaymentService {

    @GetMapping("/payment/get/{id}")
    String getPaymentInfo(@PathVariable("id") Integer id);

    @GetMapping("/payment/timeout/{id}")
    String getPaymentInfo_timeout(@PathVariable("id") Integer id);

    @GetMapping("/payment/breaker/{id}")
    String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
