package com.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_OK,id:"+ id + "\t" + "O(∩_∩)O哈哈~";
    }

    //服务降级
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TIME_OUT(Integer id){
        int timeNumber =10000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_TIME_OUT,id: " + id + "\t" + "O(∩_∩)O哈哈~" + " 耗时" + timeNumber + "毫秒";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + " 8001系统繁忙系统报错,请稍后再试id: " + id;
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties ={
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "1000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("id不能为负数");
        }if (id > Integer.MAX_VALUE){
            throw new RuntimeException("id越界，超过Integer最大值");
        }
        return "线程池：" + Thread.currentThread().getName() + "调用成功，流水号：" + IdUtil.simpleUUID();
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id不能为负数" + id;
    }
}
