package com.cloud.controller;

import com.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.print.DocFlavor;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

//    @Value("${server.port}")
//    private String serverPort;

    @GetMapping("/payment/get/{id}")
    public String getPaymentInfo(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_OK(id);
    }

    @GetMapping("/payment/timeout/{id}")
    public String getPaymentInfo_timeout(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_TIME_OUT(id);
    }

    @GetMapping("/payment/breaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        return paymentService.paymentCircuitBreaker(id);
    }
}
