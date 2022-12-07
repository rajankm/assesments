package com.modulrfinance.technicaltest.account;

public class InsufficientFundsException extends RuntimeException {

    InsufficientFundsException() {
        super("Insufficient funds.");
    }
}
