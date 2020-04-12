package com.cloud.controller;

import com.cloud.service.FeignPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private FeignPaymentService feignPaymentService;

    @GetMapping("/consumer/get/{id}")
    public String getPaymentInfo(@PathVariable Integer id){
//        int num = 10/0;
        return "我是消费者" + serverPort + feignPaymentService.getPaymentInfo(id);
    }

    //服务降级
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @GetMapping("/consumer/timeout/{id}")
    @HystrixCommand
    public String getPaymentInfo_timeout(@PathVariable("id") Integer id){
        int num = 10/0;
        return "我是消费者" + serverPort + feignPaymentService.getPaymentInfo_timeout(id);
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return serverPort + "我是消费者，支付微服务payment超时，请检查自身问题";
    }

    @GetMapping("/consumer/breaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        return  "我是消费者" + serverPort + feignPaymentService.paymentCircuitBreaker(id);
    }

    //下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，o(╥﹏╥)o";
    }

}
