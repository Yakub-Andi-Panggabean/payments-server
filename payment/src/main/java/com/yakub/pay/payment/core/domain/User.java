package com.yakub.pay.payment.core.domain;

public class User {

    private Id id;

    public User(Id id) {
        this.id = id;
    }

    public Id getId() {
        return id;
    }

    public static User id(Id id) {
        return null;
    }

    public static User from(Id id) {
        return null;
    }

    public static User to(Id id) {
        return null;
    }
}
