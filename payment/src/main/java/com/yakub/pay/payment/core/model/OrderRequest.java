package com.yakub.pay.payment.core.model;

import com.yakub.pay.payment.core.domain.Amount;
import com.yakub.pay.payment.core.domain.Order;
import com.yakub.pay.payment.core.domain.User;
import com.yakub.pay.payment.core.exception.BadRequestException;
import com.yakub.pay.payment.core.exception.PayException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class OrderRequest {

    private String from;
    private String to;
    private String amount;

    public OrderRequest(String from, String to, String amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getAmount() {
        return amount;
    }

    public static class Validator {
        public static Function<OrderRequest, Optional<PayException>> validate() {
            return (order) -> {
                try {
                    Optional.ofNullable(order).orElseThrow(() -> new BadRequestException("empty order object"));
                    Optional.ofNullable(order.from).orElseThrow(() -> new BadRequestException("empty order from"));
                    Optional.ofNullable(order.to).orElseThrow(() -> new BadRequestException("empty order to"));
                    Optional.ofNullable(order.amount).orElseThrow(() -> new BadRequestException("empty order amount"));
                    return Optional.empty();
                } catch (BadRequestException badRequestException) {
                    return Optional.of(badRequestException);
                }
            };
        }

    }

}
