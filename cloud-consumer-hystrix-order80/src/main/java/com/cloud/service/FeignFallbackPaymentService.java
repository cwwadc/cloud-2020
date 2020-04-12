package com.cloud.service;

import org.springframework.stereotype.Component;

@Component
public class FeignFallbackPaymentService implements FeignPaymentService {

    private static final String fallback = "支付系统繁忙请稍后再试，from：FeignPaymentService fallback";

    @Override
    public String getPaymentInfo(Integer id) {
        return fallback;
    }

    @Override
    public String getPaymentInfo_timeout(Integer id) {
        return fallback;
    }

    @Override
    public String paymentCircuitBreaker(Integer id) {
        return fallback;
    }
}
