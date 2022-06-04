package com.yakub.pay.payment.core.logger;

public interface Logger {

    void debug(String text, String... params);

    void info(String text, String... params);

    void warning(String text, String... params);

    void error(String text, String... params);

    void error(String text, Exception ex, String... params);

}
