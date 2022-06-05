package com.yakub.pay.payment.interfaces.web.springmvc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


    public static final String ORDER_PATH = "/order";


    @PostMapping(path = ORDER_PATH)
    public ResponseEntity createOrder() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(path = ORDER_PATH)
    public ResponseEntity cancelResponse() {
        return ResponseEntity.ok(null);
    }

}
