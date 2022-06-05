package com.yakub.pay.payment.core.logger;

public interface Logger {

    void debug(String text, Object... params);

    void info(String text, Object... params);

    void warning(String text, Object... params);

    void error(String text, Object... params);

    void error(String text, Exception ex);

}
