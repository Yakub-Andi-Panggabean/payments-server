package com.yakub.pay.payment.core.service;

import com.yakub.pay.payment.core.boundry.PaymentRequest;
import com.yakub.pay.payment.core.boundry.PaymentResponse;
import com.yakub.pay.payment.core.boundry.RefundRequest;
import com.yakub.pay.payment.core.boundry.RefundResponse;
import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.domain.User;

import java.util.List;

public interface Payment {
    PaymentResponse pay(PaymentRequest request);

    RefundResponse refund(RefundRequest request);

    PaymentResponse get(User user, Id id);

    List<PaymentResponse> get(User user);
}
