package com.yakub.pay.payment.interfaces.web.springmvc;

import com.yakub.pay.payment.core.context.PayContext;
import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.model.OrderRequest;
import com.yakub.pay.payment.core.model.OrderResponse;
import com.yakub.pay.payment.core.usecase.OrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {


    public static final String ORDER_PATH = "/order";

    private OrderUseCase orderUseCase;
    private PayContext context;

    @Autowired
    public OrderController(PayContext context, OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
        this.context = context;
    }

    @PostMapping(path = ORDER_PATH)
    public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse response = orderUseCase.order(context, orderRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping(path = ORDER_PATH + "/{order_id}")
    public ResponseEntity cancelOrder(@PathVariable("order_id") String orderId) {
        OrderResponse response = orderUseCase.cancelOrder(context, new Id(orderId));
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping(path = ORDER_PATH + "/{order_id}")
    public ResponseEntity getOrder(@PathVariable("order_id") String orderId) {
        OrderResponse response = orderUseCase.get(context, new Id(orderId));
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
