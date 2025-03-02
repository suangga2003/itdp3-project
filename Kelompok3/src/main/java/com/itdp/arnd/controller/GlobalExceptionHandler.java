package com.itdp.arnd.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.itdp.arnd.dto.ErrorDetail;
import com.itdp.arnd.exception.InvalidExchangeCurrency;
import com.itdp.arnd.exception.InvalidInputValue;
import com.itdp.arnd.exception.NotEnoughBalance;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotEnoughBalance.class)
    public ResponseEntity<?> handleNotEnoughBalance(NotEnoughBalance ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = InvalidExchangeCurrency.class)
    public ResponseEntity<?> handleInvalidExchangeCurrency(InvalidExchangeCurrency ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = InvalidInputValue.class)
    public ResponseEntity<?> handleInvalidInputValue(InvalidInputValue ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}
