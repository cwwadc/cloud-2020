package com.cloud.payment.service;

import com.cloud.common.entity.Payment;

public interface PaymentService {

    Payment getById(Long id);

    int insert(Payment payment);
}
