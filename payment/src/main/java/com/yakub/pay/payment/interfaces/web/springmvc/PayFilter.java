package com.yakub.pay.payment.interfaces.web.springmvc;

import com.yakub.pay.payment.core.context.PayContext;
import com.yakub.pay.payment.logger.slf4jlogger.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class PayFilter implements Filter {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PayContext.class.getName());


    private PayContext context;

    @Autowired
    public PayFilter(PayContext context) {
        this.context = context;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uuid = UUID.randomUUID().toString();
        MDC.put(PayContext.REQUEST_ID, uuid);
        context.set(PayContext.LOGGER_KEY, new Logger(logger));
        context.getLogger().info("start filter");
        filterChain.doFilter(servletRequest, servletResponse);
        MDC.remove(PayContext.REQUEST_ID);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
