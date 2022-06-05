package com.yakub.pay.payment.interfaces.web.springmvc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    public static final String PAYMENT_PATH = "/payment";
    public static final String REFUND_PATH = "/refunds/{payment_id}";

    @PostMapping(path = PAYMENT_PATH)
    public ResponseEntity pay() {
        return ResponseEntity.ok(null);
    }

    @GetMapping(path = PAYMENT_PATH + "/{payment_id}")
    public ResponseEntity inquiryPayment(@PathVariable("payment_id") String paymentId) {
        return ResponseEntity.ok(null);
    }


    @PostMapping(path = REFUND_PATH)
    public ResponseEntity refund(@PathVariable("payment_id") String paymentId) {
        return ResponseEntity.ok(null);
    }


}
