package com.yakub.pay.payment.core.model;

import com.yakub.pay.payment.core.domain.Payment;
import com.yakub.pay.payment.core.exception.PayException;

public class PaymentResponse {

    private Payment payment;
    private PayException exception;


    public PaymentResponse(Payment payment, PayException exception) {
        this.payment = payment;
        this.exception = exception;
    }


    public PaymentResponse(Payment payment) {
        this(payment, null);
    }

    public PaymentResponse(PayException exception) {
        this(null, exception);
    }
}
