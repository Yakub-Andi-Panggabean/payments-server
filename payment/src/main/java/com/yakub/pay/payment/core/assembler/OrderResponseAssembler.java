package com.yakub.pay.payment.core.assembler;

import com.yakub.pay.payment.core.domain.Order;
import com.yakub.pay.payment.core.exception.PayException;
import com.yakub.pay.payment.core.model.OrderRequest;
import com.yakub.pay.payment.core.model.OrderResponse;

import java.util.function.Function;

public class OrderResponseAssembler {
    public static Function<Order, OrderResponse> from() {
        return order -> new OrderResponse(order);
    }

    public static Function<PayException, OrderResponse> error() {
        return exception -> new OrderResponse(exception);
    }

    public static Function<String, OrderResponse> errorMessage() {
        return errorMessage -> new OrderResponse(new PayException(errorMessage));
    }
}
