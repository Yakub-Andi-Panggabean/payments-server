package com.yakub.pay.payment.core.context;

import com.yakub.pay.payment.core.domain.User;
import com.yakub.pay.payment.core.logger.Logger;

import java.util.Map;
import java.util.Optional;

public class DefaultPayContext implements PayContext {

    public static final String USER_KEY = "user";
    public static final String LOGGER_KEY = "logger";

    private Map<String, Object> map;

    public DefaultPayContext(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public Optional<User> getUser() {
        return Optional.ofNullable((User) map.get(USER_KEY));
    }

    @Override
    public Optional<Object> get(String key) {
        return Optional.ofNullable(map.get(key));
    }

    @Override
    public <T> Optional<T> get(String key, T clazz) {
        return Optional.ofNullable((T) map.get(key));
    }

    @Override
    public void set(String key, Object value) {
        map.put(key, value);
    }

    @Override
    public Logger getLogger() {
        return (Logger) map.get(LOGGER_KEY);
    }
}
