package com.example.shop.global.response;

import com.example.shop.global.exception.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessExceptionHandler.class)
    public ResponseEntity<ErrorResponse> handleCustomException(BusinessExceptionHandler e) {
        HttpStatus errorCode = e.getErrorCodes().getStatus();
        return ResponseEntity.status(errorCode).body(new ErrorResponse(errorCode.name(),e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(message, e.getStatusCode().toString());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
