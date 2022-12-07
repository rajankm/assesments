package com.modulrfinance.technicaltest.atm;

class NotEnoughNotesException extends RuntimeException {

    NotEnoughNotesException() {
        super("Not enough notes in the ATM.");
    }
}
