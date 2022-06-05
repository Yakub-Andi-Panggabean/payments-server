package com.yakub.pay.payment.logger.slf4jlogger;

import org.slf4j.Marker;

import java.util.UUID;

public class Logger implements com.yakub.pay.payment.core.logger.Logger {

    private org.slf4j.Logger logger;

    public Logger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void debug(String text, Object... params) {
        logger.debug(text, params);
    }

    @Override
    public void info(String text, Object... params) {
        logger.info(text, params);
    }

    @Override
    public void warning(String text, Object... params) {
        logger.warn(text, params);
    }

    @Override
    public void error(String text, Object... params) {
        logger.error(text, params);
    }

    @Override
    public void error(String text, Exception ex) {
        logger.error(text, ex);
    }
}
