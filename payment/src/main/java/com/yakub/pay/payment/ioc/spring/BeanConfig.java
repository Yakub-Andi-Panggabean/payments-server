package com.yakub.pay.payment.ioc.spring;

import com.yakub.pay.payment.core.bridge.OrderPersistence;
import com.yakub.pay.payment.core.bridge.PaymentPersistence;
import com.yakub.pay.payment.core.context.DefaultPayContext;
import com.yakub.pay.payment.core.context.PayContext;
import com.yakub.pay.payment.core.usecase.OrderUseCase;
import com.yakub.pay.payment.core.usecase.PaymentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

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

    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)
    @Bean
    public PayContext context() {
        return new DefaultPayContext();
    }

}
