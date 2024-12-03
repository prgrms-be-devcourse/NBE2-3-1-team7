package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class DuplicatedEmail extends BusinessExceptionHandler {
    public DuplicatedEmail() {super(ErrorCodes.DUPLICATE_EMAIL);}
}
