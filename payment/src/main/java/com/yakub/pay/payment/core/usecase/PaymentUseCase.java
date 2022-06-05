package com.yakub.pay.payment.core.usecase;

import com.yakub.pay.payment.core.assembler.PaymentRequestAssembler;
import com.yakub.pay.payment.core.assembler.PaymentResponseAssembler;
import com.yakub.pay.payment.core.bridge.OrderPersistence;
import com.yakub.pay.payment.core.bridge.PaymentPersistence;
import com.yakub.pay.payment.core.context.PayContext;
import com.yakub.pay.payment.core.domain.Order;
import com.yakub.pay.payment.core.exception.PayException;
import com.yakub.pay.payment.core.model.PaymentRequest;
import com.yakub.pay.payment.core.model.PaymentResponse;
import com.yakub.pay.payment.core.model.RefundRequest;
import com.yakub.pay.payment.core.model.RefundResponse;
import com.yakub.pay.payment.core.domain.Id;
import com.yakub.pay.payment.core.domain.User;
import com.yakub.pay.payment.core.validator.Validator;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PaymentUseCase {
    private PaymentPersistence paymentPersistence;
    private OrderPersistence orderPersistence;

    public PaymentUseCase(PaymentPersistence paymentPersistence, OrderPersistence orderPersistence) {
        this.paymentPersistence = paymentPersistence;
        this.orderPersistence = orderPersistence;
    }

    public PaymentResponse pay(PayContext context, PaymentRequest request) {

        Optional<PayException> contextError = Validator.validate().apply(context);

        List<Order> mappedOrders = request.getOrderIds().stream()
                .map(Id::user)
                .map(orderPersistence::get)
                .map(Optional::get)
                .collect(Collectors.toList());

        Function<List<Order>, Optional<PayException>> checkUser = orders ->
                orders.stream()
                        .filter(order -> context.getUser().map(User::getId).orElse(Id.empty()).match(order.getId()))
                        .findAny()
                        .map(order -> Optional
                                .ofNullable(new PayException(
                                        String.format("invalid user for order %s", order.getId().getValue()))))
                        .orElse(Optional.empty());

        return contextError.map(PaymentResponseAssembler.error())
                .orElse(
                        checkUser.apply(mappedOrders)
                                .map(PaymentResponseAssembler.error())
                                .orElse(
                                        PaymentRequestAssembler.to()
                                                .andThen(paymentPersistence::pay)
                                                .apply(request))
                );
    }

    public RefundResponse refund(PayContext context, Id paymentId) {
        return null;
    }

    public PaymentResponse get(PayContext context, Id id) {
        return null;
    }

    public List<PaymentResponse> get(PayContext context) {
        return null;
    }
}
