package com.cloud.conroller;

import com.cloud.common.entity.Payment;
import com.cloud.common.util.CommonResult;
import com.cloud.servcie.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderOpenFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/consul/getPayment/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        return paymentFeignService.getPayment(id);
    }

    @GetMapping("/consumer/consul/getDiscovery")
    public CommonResult getDiscoveryClient(){
        return paymentFeignService.getDiscoveryClient();
    }
}
