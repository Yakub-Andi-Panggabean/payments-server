package com.yakub.pay.payment.core.service;

import com.yakub.pay.payment.core.boundry.OrderRequest;
import com.yakub.pay.payment.core.boundry.OrderResponse;
import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.domain.User;

import java.util.List;

public interface Order {
    OrderResponse order(OrderRequest request);

    OrderResponse get(User user, Id id);

    List<OrderResponse> get(User user);

    OrderResponse cancelOrder(User user, Id id);
}
