package com.yakub.pay.payment.core.validator;

import com.yakub.pay.payment.core.exception.PayException;

import java.util.Optional;
import java.util.function.Function;

public class Validator {

    public static <T> Function<T, Optional<PayException>> validate() {
        return t -> {
            try {
                Optional.ofNullable(t).orElseThrow(() ->
                        new PayException(String.format("empty value for type %s", t.getClass().getName())));
                return Optional.empty();
            } catch (PayException exception) {
                return Optional.of(exception);
            }
        };
    }
}
