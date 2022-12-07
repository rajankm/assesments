package com.modulrfinance.technicaltest.account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class AccountService {

    private Map<String, BigDecimal> accounts;

    public AccountService() {
        accounts = new HashMap<>();
    }

    public BigDecimal checkBalance(String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            throw new AccountNotFoundException(accountNumber);
        }
        return accounts.get(accountNumber);
    }

    public void withdraw(String accountNumber, BigDecimal amount) {
        if (!accounts.containsKey(accountNumber)) {
            throw new AccountNotFoundException(accountNumber);
        }
        BigDecimal currentBalance = accounts.get(accountNumber);
        BigDecimal newBalance = currentBalance.subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException();
        }
        accounts.put(accountNumber, newBalance);
    }

    public void createAccount(String accountNumber, BigDecimal balance) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException(format("Account [%s] already exists.", accountNumber));
        }
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot create account with the negative balance.");
        }
        accounts.put(accountNumber, balance);
    }
}
