package com.yakub.pay.payment.core.bridge;

import com.yakub.pay.payment.core.model.PaymentRequest;
import com.yakub.pay.payment.core.model.PaymentResponse;
import com.yakub.pay.payment.core.model.RefundRequest;
import com.yakub.pay.payment.core.model.RefundResponse;
import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.domain.User;

import java.util.List;

public interface PaymentService {
    PaymentResponse pay(PaymentRequest request);

    RefundResponse refund(RefundRequest request);

    PaymentResponse get(User user, Id id);

    List<PaymentResponse> get(User user);
}
