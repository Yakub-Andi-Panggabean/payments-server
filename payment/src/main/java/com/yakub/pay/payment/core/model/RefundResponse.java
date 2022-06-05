package com.yakub.pay.payment.core.model;

import com.yakub.pay.payment.core.domain.Payment;

public class RefundResponse {
    private Payment payment;
    private Exception exception;

    public RefundResponse(Payment payment, Exception exception) {
        this.payment = payment;
        this.exception = exception;
    }

    public Payment getPayment() {
        return payment;
    }

    public Exception getException() {
        return exception;
    }

    public boolean isSuccess() {
        return this.exception == null;
    }
}
