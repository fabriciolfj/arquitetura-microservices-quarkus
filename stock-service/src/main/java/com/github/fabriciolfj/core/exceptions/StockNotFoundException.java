package com.github.fabriciolfj.core.exceptions;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(final String msg) {
        super(msg);
    }
}
