package com.cloud.consulorder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderConsulController {

    @Resource
    private RestTemplate restTemplate;

    private final String INVOKE_URL = "http://myprefix-consul-provider-payment";

    @GetMapping("/consumer/payment/consul")
    public String paymentConsul(){
        return restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
    }

}
