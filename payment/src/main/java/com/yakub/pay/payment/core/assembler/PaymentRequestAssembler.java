package com.yakub.pay.payment.core.assembler;

import com.yakub.pay.payment.core.domain.*;
import com.yakub.pay.payment.core.model.PaymentRequest;

import java.util.Optional;
import java.util.function.Function;

public class PaymentRequestAssembler {

    public static Function<PaymentRequest, Payment> to() {
        return req -> new Payment();
    }

}
