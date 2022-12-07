package com.modulrfinance.technicaltest.account;

import static java.lang.String.format;

public class AccountNotFoundException extends RuntimeException {

    AccountNotFoundException(String accountNumber) {
        super(format("Account [%s] has not been found.", accountNumber));
    }
}
