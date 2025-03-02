package com.itdp.arnd.exception;

public class InvalidInputValue extends RuntimeException {
    public InvalidInputValue() {
        super("Invalid input value: must be greater than 0");
    }
}
