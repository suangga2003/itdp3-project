package com.itdp.arnd.exception;

public class InvalidExchangeCurrency extends RuntimeException {
    public InvalidExchangeCurrency() {
        super("Invalid exchange currency. There should be one primary currency (IDR)!");
    }
}
