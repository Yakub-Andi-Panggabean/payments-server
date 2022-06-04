package com.yakub.pay.payment.core.usecase;

import com.yakub.pay.payment.core.assembler.OrderRequestAssembler;
import com.yakub.pay.payment.core.assembler.OrderResponseAssembler;
import com.yakub.pay.payment.core.bridge.OrderService;
import com.yakub.pay.payment.core.checker.Checker;
import com.yakub.pay.payment.core.domain.Order;
import com.yakub.pay.payment.core.domain.State;
import com.yakub.pay.payment.core.exception.UnexpectedReturnException;
import com.yakub.pay.payment.core.model.OrderRequest;
import com.yakub.pay.payment.core.model.OrderResponse;
import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.domain.User;
import com.yakub.pay.payment.core.validator.Validator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderUseCase {

    private OrderService orderService;

    public OrderUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderResponse order(OrderRequest request) {

        Optional<OrderResponse> error = OrderRequest.Validator
                .validate()
                .apply(request)
                .map(OrderResponseAssembler.error());

        return error.orElse(OrderRequestAssembler.to()
                .andThen(this::tryOrder)
                .apply(request));
    }


    public OrderResponse get(User user, Id id) {

        Optional<OrderResponse> invalidUser = Validator.validate()
                .apply(user)
                .map(OrderResponseAssembler.error());

        Optional<OrderResponse> invalidId = Validator.validate()
                .apply(id)
                .map(OrderResponseAssembler.error());

        return invalidUser.orElse(invalidId.orElse(orderService.get(user, id)
                .map(OrderResponseAssembler.from())
                .orElse(OrderResponseAssembler.errorMessage()
                        .apply("order is not exist"))));

    }

    public List<OrderResponse> get(User user) {
        return orderService.get(user).stream()
                .map(OrderResponseAssembler.from())
                .collect(Collectors.toList());
    }

    public OrderResponse cancelOrder(User user, Id id) {

        Optional<OrderResponse> invalidUser = Validator.validate()
                .apply(user)
                .map(OrderResponseAssembler.error());

        Optional<OrderResponse> invalidId = Validator.validate()
                .apply(id)
                .map(OrderResponseAssembler.error());

        return invalidUser.orElse(invalidId.orElse(orderService.get(user, id)
                .map(this::tryCancel).get()));
    }

    public OrderResponse changeOrderState(Order order, State state) {

        Optional<OrderResponse> invalidOrder = Validator.validate()
                .apply(order)
                .map(OrderResponseAssembler.error());

        Optional<OrderResponse> invalidState = Validator.validate()
                .apply(state)
                .map(OrderResponseAssembler.error());

        return invalidOrder.orElse(invalidState.orElse(tryUpdateState(order, state)));
    }

    private OrderResponse tryOrder(Order req) {
        return Checker.check(req, o -> orderService.order(req)
                        .map(OrderResponseAssembler.from())
                        .orElseThrow(UnexpectedReturnException::new),
                OrderResponseAssembler.error());
    }

    private OrderResponse tryCancel(Order order) {
        return Checker.check(order,
                o -> orderService.order(o)
                        .map(OrderResponseAssembler.from())
                        .orElseThrow(UnexpectedReturnException::new),
                OrderResponseAssembler.error());
    }

    private OrderResponse tryUpdateState(Order order, State state) {
        return Checker.check(order,
                o -> orderService.changeOrderState(o, state)
                        .map(OrderResponseAssembler.from())
                        .orElseThrow(UnexpectedReturnException::new)
                , OrderResponseAssembler.error());
    }


}

