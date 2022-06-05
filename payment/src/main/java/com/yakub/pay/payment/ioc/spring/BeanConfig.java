package com.yakub.pay.payment.ioc.spring;

import com.yakub.pay.payment.core.bridge.OrderPersistence;
import com.yakub.pay.payment.core.bridge.PaymentPersistence;
import com.yakub.pay.payment.core.usecase.OrderUseCase;
import com.yakub.pay.payment.core.usecase.PaymentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public OrderUseCase orderUseCaseBean(OrderPersistence service) {
        return new OrderUseCase(service);
    }

    @Bean
    public PaymentUseCase paymentUseCaseBean(OrderPersistence orderPersistence, PaymentPersistence paymentPersistence) {
        return new PaymentUseCase(paymentPersistence, orderPersistence);
    }

}
