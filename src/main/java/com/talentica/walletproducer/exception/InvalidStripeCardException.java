package com.talentica.walletproducer.exception;

public class InvalidStripeCardException extends RuntimeException{

    public InvalidStripeCardException(String message) {
        super(message);
    }
}
