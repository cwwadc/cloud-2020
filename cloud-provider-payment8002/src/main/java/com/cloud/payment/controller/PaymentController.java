package com.cloud.payment.controller;

import com.cloud.common.entity.Payment;
import com.cloud.common.util.CommonResult;
import com.cloud.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;
    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        log.info("查询数据库成功");
        Payment Payment = paymentService.getById(id);
        if (Payment == null){
            return new CommonResult<>(444,"没有对应的记录，服务端口："+serverPort, null);
        }
        return new CommonResult<>(200,"查询成功，服务端口："+ serverPort,Payment);
    }

    @PostMapping("/payment/insert")
    public CommonResult insert(@RequestBody Payment payment){
        log.info("插入数据库成功");
        if (paymentService.insert(payment)<0){
            return new CommonResult<>(444,"插入数据库失败，服务端口："  + serverPort, null);
        }
        return new CommonResult<>(200,"插入数据库成功，服务端口："  + serverPort,payment);
    }

    @GetMapping("/payment/getDiscovery")
    public CommonResult getDiscoveryClient(){


        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("element----------"+ element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance serviceInstance : instances) {
            log.info(serviceInstance.getHost() + "\t" + serviceInstance.getPort() +"\t"+ serviceInstance.getUri() + "\t" + serviceInstance.getServiceId());
        }
        return new CommonResult(200,"success",this.discoveryClient);
    }

}
