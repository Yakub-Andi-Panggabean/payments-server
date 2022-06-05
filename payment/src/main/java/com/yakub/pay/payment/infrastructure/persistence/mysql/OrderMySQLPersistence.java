package com.yakub.pay.payment.infrastructure.persistence.mysql;

import com.yakub.pay.payment.core.bridge.OrderPersistence;
import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.domain.Order;
import com.yakub.pay.payment.core.domain.State;
import com.yakub.pay.payment.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderMySQLPersistence implements OrderPersistence {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderMySQLPersistence(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Order> order(Order request) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> get(Id id) {
        return Optional.empty();
    }

    @Override
    public List<Order> get(User user) {
        return null;
    }

    @Override
    public Optional<Order> cancelOrder(Order request) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> changeOrderState(Order order, State state) {
        return Optional.empty();
    }
}
