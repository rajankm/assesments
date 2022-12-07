package com.modulrfinance.technicaltest.account;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class AccountServiceTest {

    @Test
    public void checksAccountBalance() {
        // given
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("2738.59"));

        // when
        BigDecimal balance = accountService.checkBalance("01001");

        // then
        assertThat(balance).isEqualTo(new BigDecimal("2738.59"));
    }

    @Test
    public void throwsExceptionWhenCheckingNonexistentAccountBalance() {
        // given
        AccountService accountService = new AccountService();

        // when
        Throwable thrown = catchThrowable(() -> accountService.checkBalance("01001"));

        // then
        assertThat(thrown)
            .isInstanceOf(AccountNotFoundException.class)
            .hasMessage("Account [01001] has not been found.");
    }

    @Test
    public void withdrawsMoney() {
        // given
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("2738.59"));
        BigDecimal withdrawalAmount = new BigDecimal("11.90");

        // when
        accountService.withdraw("01001", withdrawalAmount);

        // then
        assertThat(accountService.checkBalance("01001")).isEqualTo(new BigDecimal("2726.69"));
    }

    @Test
    public void throwsExceptionWhenWithdrawingMoneyFromNonexistentAccount() {
        // given
        AccountService accountService = new AccountService();
        BigDecimal withdrawalAmount = new BigDecimal("11.90");

        // when
        Throwable thrown = catchThrowable(() -> accountService.withdraw("01001", withdrawalAmount));

        // then
        assertThat(thrown)
            .isInstanceOf(AccountNotFoundException.class)
            .hasMessage("Account [01001] has not been found.");
    }

    @Test
    public void throwsExceptionWhenInsufficientFunds() {
        // given
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("99.99"));
        BigDecimal withdrawalAmount = new BigDecimal("100.00");

        // when
        Throwable thrown = catchThrowable(() -> accountService.withdraw("01001", withdrawalAmount));

        // then
        assertThat(thrown)
            .isInstanceOf(InsufficientFundsException.class);
    }

    @Test
    public void throwsExceptionWhenCreatingAccountThatAlreadyExists() {
        // given
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("2738.59"));

        // when
        Throwable thrown = catchThrowable(() -> accountService.createAccount("01001", new BigDecimal("2738.59")));

        // then
        assertThat(thrown)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Account [01001] already exists.");
    }

    @Test
    public void throwsExceptionWhenCreatingAccountWithNegativeBalance() {
        // given
        AccountService accountService = new AccountService();

        // when
        Throwable thrown = catchThrowable(() -> accountService.createAccount("01001", new BigDecimal("-100.00")));

        // then
        assertThat(thrown)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Cannot create account with the negative balance.");
    }

}