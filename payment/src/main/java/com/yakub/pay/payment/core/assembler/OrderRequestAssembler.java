package com.yakub.pay.payment.core.assembler;

import com.yakub.pay.payment.core.domain.*;
import com.yakub.pay.payment.core.model.OrderRequest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Function;

public class OrderRequestAssembler {

    public static Function<OrderRequest, Order> to() {
        return req -> new Order(Id.empty(),
                User.from(Id.user(req.getFrom())),
                User.to(Id.merchant(req.getTo())),
                new BigDecimal(req.getAmount()),
                State.NEW,
                new Date(), new Date()
        );
    }

}
