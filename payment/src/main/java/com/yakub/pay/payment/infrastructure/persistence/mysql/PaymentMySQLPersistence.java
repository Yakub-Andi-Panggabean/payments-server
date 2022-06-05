package com.yakub.pay.payment.infrastructure.persistence.mysql;

import com.yakub.pay.payment.core.bridge.PaymentPersistence;
import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.domain.Payment;
import com.yakub.pay.payment.core.domain.User;
import com.yakub.pay.payment.core.model.PaymentResponse;
import com.yakub.pay.payment.core.model.RefundResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMySQLPersistence implements PaymentPersistence {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PaymentMySQLPersistence(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PaymentResponse pay(Payment payment) {
        return null;
    }

    @Override
    public RefundResponse refund(Payment payment) {
        return null;
    }

    @Override
    public PaymentResponse get(Id id) {
        return null;
    }

    @Override
    public List<PaymentResponse> get(User user) {
        return null;
    }
}
