package com.example.shop.global.exception;

public class ProductUpdateFailedException extends RuntimeException {
    public ProductUpdateFailedException(String message) {
        super(message);
    }
}
