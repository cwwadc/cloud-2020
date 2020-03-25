package com.cloud.payment.service.impl;

import com.cloud.common.entity.Payment;
import com.cloud.payment.mapper.PaymentMapper;
import com.cloud.payment.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;


    @Override
    public Payment getById(Long id) {

        return paymentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Payment payment) {

        return paymentMapper.insert(payment);
    }
}
