package com.cloud.consumer.controller;

import com.cloud.common.entity.Payment;
import com.cloud.common.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {

    @Resource
    private RestTemplate restTemplate;
    private final String httpUrl="http://localhost:8001";
    private final String paymentUrl = "http://CLOUD-PAYMENT-SERVICE";

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        CommonResult result = restTemplate.getForObject(paymentUrl + "/payment/get/" + id, CommonResult.class);
        if (result== null){
            return new CommonResult(444,"查询失败", result);
        }
        return new CommonResult<>(200,"查询成功",result);
    }

    @PostMapping("/payment/insert")
    public CommonResult insert(@RequestBody Payment payment){
        CommonResult result = restTemplate.postForObject(paymentUrl+"/payment/insert",payment,CommonResult.class);
        if (result== null){
            return new CommonResult<>(444,"插入数据库失败", null);
        }
        return new CommonResult<>(200,"插入数据库成功",result);
    }
}
