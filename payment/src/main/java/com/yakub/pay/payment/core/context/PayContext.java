package com.yakub.pay.payment.core.context;

import com.yakub.pay.payment.core.domain.User;
import com.yakub.pay.payment.core.logger.Logger;

import java.util.Optional;

public interface PayContext {

    String USER_KEY = "user";
    String LOGGER_KEY = "logger";
    String REQUEST_ID = "requestid";

    Optional<User> getUser();

    Optional<Object> get(String key);

    <T> Optional<T> get(String key, T clazz);

    void set(String key, Object value);

    Logger getLogger();

}
