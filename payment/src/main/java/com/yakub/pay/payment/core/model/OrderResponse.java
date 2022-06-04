package com.yakub.pay.payment.core.model;

import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.domain.Order;
import com.yakub.pay.payment.core.exception.PayException;

import java.util.Optional;

public class OrderResponse {
    private Order order;
    private PayException exception;

    public OrderResponse(Order order, PayException exception) {
        this.order = order;
        this.exception = exception;
    }

    public OrderResponse(Order order) {
        this(order, null);
    }

    public OrderResponse(PayException exception) {
        this(null, exception);
    }

    public Order getOrder() {
        return order;
    }

    public Exception getException() {
        return exception;
    }

    public boolean isSuccess() {
        return Optional.ofNullable(order).isPresent();
    }
}
