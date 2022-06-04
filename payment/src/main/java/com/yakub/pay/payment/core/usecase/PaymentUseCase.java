package com.yakub.pay.payment.core.usecase;

import com.yakub.pay.payment.core.bridge.PaymentService;
import com.yakub.pay.payment.core.model.PaymentRequest;
import com.yakub.pay.payment.core.model.PaymentResponse;
import com.yakub.pay.payment.core.model.RefundRequest;
import com.yakub.pay.payment.core.model.RefundResponse;
import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.domain.User;

import java.util.List;

public class PaymentUseCase {
    private PaymentService paymentService;

    public PaymentUseCase(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentResponse pay(PaymentRequest request) {
        return null;
    }

    public RefundResponse refund(RefundRequest request) {
        return null;
    }

    public PaymentResponse get(User user, Id id) {
        return null;
    }

    public List<PaymentResponse> get(User user) {
        return null;
    }
}
