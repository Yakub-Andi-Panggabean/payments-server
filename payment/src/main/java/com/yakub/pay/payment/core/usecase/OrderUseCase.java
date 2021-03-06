package com.yakub.pay.payment.core.usecase;

import com.yakub.pay.payment.core.assembler.OrderRequestAssembler;
import com.yakub.pay.payment.core.assembler.OrderResponseAssembler;
import com.yakub.pay.payment.core.bridge.OrderPersistence;
import com.yakub.pay.payment.core.checker.Checker;
import com.yakub.pay.payment.core.context.PayContext;
import com.yakub.pay.payment.core.domain.Order;
import com.yakub.pay.payment.core.domain.State;
import com.yakub.pay.payment.core.exception.UnexpectedReturnException;
import com.yakub.pay.payment.core.model.OrderRequest;
import com.yakub.pay.payment.core.model.OrderResponse;
import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.validator.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderUseCase {

    private OrderPersistence orderPersistence;

    public OrderUseCase(OrderPersistence orderPersistence) {
        this.orderPersistence = orderPersistence;
    }

    public OrderResponse order(PayContext context, OrderRequest request) {

        Optional<OrderResponse> error = OrderRequest.Validator
                .validate()
                .apply(request)
                .map(OrderResponseAssembler.error());

        error.ifPresent(err -> context.getLogger().error("invalid order request, reason : {}", err));

        return error.orElse(OrderRequestAssembler.to()
                .andThen(this::tryOrder)
                .apply(request));
    }


    public OrderResponse get(PayContext context, Id id) {

        context.getLogger().info("try to get order with id: {}", id);

        return Validator.validate()
                .apply(id)
                .map(OrderResponseAssembler.error())
                .orElse(orderPersistence.get(id)
                        .map(OrderResponseAssembler.from())
                        .orElse(OrderResponseAssembler.errorMessage()
                                .apply("order is not exist")));

    }

    public List<OrderResponse> get(PayContext context) {
        return context.getUser()
                .map(orderPersistence::get)
                .orElse(Collections.emptyList()).stream()
                .map(OrderResponseAssembler.from())
                .collect(Collectors.toList());
    }

    public OrderResponse cancelOrder(PayContext context, Id id) {

        Optional<OrderResponse> invalidUser = context.getUser()
                .map(user -> Validator.validate()
                        .apply(user)
                        .map(OrderResponseAssembler.error()))
                .orElse(
                        Optional.of(OrderResponseAssembler
                                .errorMessage()
                                .apply("user not exist on context")));

        Optional<OrderResponse> invalidId = Validator.validate()
                .apply(id)
                .map(OrderResponseAssembler.error());

        return invalidUser.orElse(invalidId.orElse(orderPersistence.get(id)
                .map(this::tryCancel).get()));
    }

    public OrderResponse changeOrderState(PayContext context, Order order, State state) {

        Optional<OrderResponse> invalidOrder = Validator.validate()
                .apply(order)
                .map(OrderResponseAssembler.error());

        Optional<OrderResponse> invalidState = Validator.validate()
                .apply(state)
                .map(OrderResponseAssembler.error());

        return invalidOrder.orElse(invalidState.orElse(tryUpdateState(order, state)));
    }

    private OrderResponse tryOrder(Order req) {
        return Checker.check(req, o -> orderPersistence.order(req)
                        .map(OrderResponseAssembler.from())
                        .orElseThrow(UnexpectedReturnException::new),
                OrderResponseAssembler.error());
    }

    private OrderResponse tryCancel(Order order) {
        return Checker.check(order,
                o -> orderPersistence.order(o)
                        .map(OrderResponseAssembler.from())
                        .orElseThrow(UnexpectedReturnException::new),
                OrderResponseAssembler.error());
    }

    private OrderResponse tryUpdateState(Order order, State state) {
        return Checker.check(order,
                o -> orderPersistence.changeOrderState(o, state)
                        .map(OrderResponseAssembler.from())
                        .orElseThrow(UnexpectedReturnException::new)
                , OrderResponseAssembler.error());
    }


}

