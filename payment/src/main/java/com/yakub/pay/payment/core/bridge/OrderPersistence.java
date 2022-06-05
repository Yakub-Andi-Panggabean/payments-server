package com.yakub.pay.payment.core.bridge;

import com.yakub.pay.payment.core.domain.Order;

import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.domain.State;
import com.yakub.pay.payment.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface OrderPersistence {
    Optional<Order> order(Order request);

    Optional<Order> get(Id id);

    List<Order> get(User user);

    Optional<Order> cancelOrder(Order request);

    Optional<Order> changeOrderState(Order order, State state);
}
