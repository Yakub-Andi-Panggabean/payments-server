package com.yakub.pay.payment.core.domain;

public class Id {

    private String value;

    public Id(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Id empty() {
        return new Id("");
    }

    public static Id user(String value) {
        return new Id(value);
    }

    public static Id merchant(String value) {
        return new Id(value);
    }
}
