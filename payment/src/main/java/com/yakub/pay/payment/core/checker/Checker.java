package com.yakub.pay.payment.core.checker;

import com.yakub.pay.payment.core.exception.PayException;

import java.util.function.Function;

public class Checker {

    public static <T, R> R check(T t, Function<T, R> onSuccess, Function<PayException, R> onError) {
        try {
            return onSuccess.apply(t);
        } catch (Exception e) {
            return onError.apply(new PayException(e));
        }
    }

}
