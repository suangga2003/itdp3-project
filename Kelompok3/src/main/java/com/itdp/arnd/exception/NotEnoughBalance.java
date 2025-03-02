package com.itdp.arnd.exception;

public class NotEnoughBalance extends RuntimeException {
    public NotEnoughBalance() {
        super("Balance not sufficient");
    }
}
