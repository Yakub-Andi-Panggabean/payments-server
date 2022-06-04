package com.yakub.pay.payment.core.exception;

public class PayException extends RuntimeException {

    public PayException() {
    }

    public PayException(String message) {
        super(message);
    }

    public PayException(Throwable cause) {
        super(cause);
    }
}
