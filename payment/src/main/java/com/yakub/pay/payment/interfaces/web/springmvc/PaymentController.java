package com.yakub.pay.payment.interfaces.web.springmvc;

import com.yakub.pay.payment.core.context.PayContext;
import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.model.PaymentRequest;
import com.yakub.pay.payment.core.model.PaymentResponse;
import com.yakub.pay.payment.core.model.RefundResponse;
import com.yakub.pay.payment.core.usecase.OrderUseCase;
import com.yakub.pay.payment.core.usecase.PaymentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    public static final String PAYMENT_PATH = "/payment";
    public static final String REFUND_PATH = "/refunds/{payment_id}";

    private PaymentUseCase paymentUseCase;
    private PayContext context;

    @Autowired
    public PaymentController(PaymentUseCase paymentUseCase, PayContext context) {
        this.paymentUseCase = paymentUseCase;
        this.context = context;
    }


    @PostMapping(path = PAYMENT_PATH)
    public ResponseEntity pay(PaymentRequest request) {
        PaymentResponse response = paymentUseCase.pay(context, request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping(path = PAYMENT_PATH + "/{payment_id}")
    public ResponseEntity inquiryPayment(@PathVariable("payment_id") String paymentId) {
        PaymentResponse response = paymentUseCase.get(context, new Id(paymentId));
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @PostMapping(path = REFUND_PATH)
    public ResponseEntity refund(@PathVariable("payment_id") String paymentId) {
        RefundResponse response = paymentUseCase.refund(context, new Id(paymentId));
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
