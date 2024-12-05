package com.example.shop.global.exception;

public class DataInsertFailedException extends RuntimeException {
    public DataInsertFailedException(String message) {
        super(message);
    }
}
