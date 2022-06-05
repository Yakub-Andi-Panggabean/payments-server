package com.yakub.pay.payment.core.assembler;

import com.yakub.pay.payment.core.domain.Payment;
import com.yakub.pay.payment.core.exception.PayException;
import com.yakub.pay.payment.core.model.PaymentResponse;

import java.util.function.Function;

public class PaymentResponseAssembler {
    public static Function<Payment, PaymentResponse> from() {
        return payment -> new PaymentResponse(payment);
    }

    public static Function<PayException, PaymentResponse> error() {
        return exception -> new PaymentResponse(exception);
    }

    public static Function<String, PaymentResponse> errorMessage() {
        return errorMessage -> new PaymentResponse(new PayException(errorMessage));
    }
}
