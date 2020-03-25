package com.cloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
public class NacosClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info/get")
    public String getConfigInfo(){
        return configInfo;
    }

}
