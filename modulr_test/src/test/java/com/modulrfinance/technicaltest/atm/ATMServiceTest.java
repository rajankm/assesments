package com.modulrfinance.technicaltest.atm;

import com.modulrfinance.technicaltest.account.AccountNotFoundException;
import com.modulrfinance.technicaltest.account.AccountService;
import com.modulrfinance.technicaltest.account.InsufficientFundsException;
import com.modulrfinance.technicaltest.money.MoneyAsNotes;
import org.junit.Test;

import java.math.BigDecimal;

import static com.modulrfinance.technicaltest.money.Denomination.FIFTY;
import static com.modulrfinance.technicaltest.money.Denomination.FIVE;
import static com.modulrfinance.technicaltest.money.Denomination.TEN;
import static com.modulrfinance.technicaltest.money.Denomination.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ATMServiceTest {

    @Test
    public void checksAccountBalance() {
        // given
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("2738.59"));
        ATMService atmService = new ATMService(accountService, MoneyAsNotes.createEmpty());

        // when
        String formattedBalance = atmService.checkBalance("01001");

        // then
        assertThat(formattedBalance).isEqualTo("£2,738.59");
    }

    @Test
    public void throwsExceptionWhenCheckingNonexistentAccountBalance() {
        // given
        ATMService atmService = new ATMService(new AccountService(), MoneyAsNotes.createEmpty());

        // when
        Throwable thrown = catchThrowable(() -> atmService.checkBalance("01001"));

        // then
        assertThat(thrown)
            .isInstanceOf(AccountNotFoundException.class)
            .hasMessage("Account [01001] has not been found.");
    }

    @Test
    public void withdrawsMoney() {
        // given
        MoneyAsNotes vault = MoneyAsNotes.createEmpty()
            .add(FIFTY, 1)
            .add(TWENTY, 3);
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("2738.59"));
        ATMService atmService = new ATMService(accountService, vault);

        // when
        MoneyAsNotes withdrawnMoney = atmService.withdraw("01001", new BigDecimal("60.00"));

        // then
        assertThat(withdrawnMoney.get(FIVE)).isEqualTo(0);
        assertThat(withdrawnMoney.get(TEN)).isEqualTo(0);
        assertThat(withdrawnMoney.get(TWENTY)).isEqualTo(3);
        assertThat(withdrawnMoney.get(FIFTY)).isEqualTo(0);

        // and
        assertThat(atmService.checkBalance("01001")).isEqualTo("£2,678.59");

        Throwable throwable = catchThrowable(() -> atmService.withdraw("01001", new BigDecimal("20.00")));
        assertThat(throwable).isInstanceOf(NotEnoughNotesException.class);
    }

    @Test
    public void withdrawsMoneyWithMinimalNumberOfNotesDispensed() {
        // given
        MoneyAsNotes vault = MoneyAsNotes.createEmpty()
            .add(TWENTY, 6)
            .add(TEN, 2)
            .add(FIFTY, 2);
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("2738.59"));
        ATMService atmService = new ATMService(accountService, vault);

        // when
        MoneyAsNotes withdrawnMoney = atmService.withdraw("01001", new BigDecimal("120.00"));

        // then
        assertThat(withdrawnMoney.get(FIVE)).isEqualTo(0);
        assertThat(withdrawnMoney.get(TEN)).isEqualTo(0);
        assertThat(withdrawnMoney.get(TWENTY)).isEqualTo(1);
        assertThat(withdrawnMoney.get(FIFTY)).isEqualTo(2);
    }

    @Test
    public void prioritiesFivesWhenDispensingMoney() {
        // given
        MoneyAsNotes vault = MoneyAsNotes.createEmpty()
            .add(FIVE, 10)
            .add(FIFTY, 1);
        vault.setOrdered(true);
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("2738.59"));
        ATMService atmService = new ATMService(accountService, vault);

        // when
        MoneyAsNotes withdrawnMoney = atmService.withdraw("01001", new BigDecimal("50.00"));

        // then
        assertThat(withdrawnMoney.get(FIVE)).isEqualTo(10);
        assertThat(withdrawnMoney.get(TEN)).isEqualTo(0);
        assertThat(withdrawnMoney.get(TWENTY)).isEqualTo(0);
        assertThat(withdrawnMoney.get(FIFTY)).isEqualTo(0);
    }

    @Test
    public void fiveNoteRuleDoesNotBreakTheMoneyWithdrawal() {
        // given
        MoneyAsNotes vault = MoneyAsNotes.createEmpty()
            .add(FIVE, 1)
            .add(TWENTY, 1);
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("2738.59"));
        ATMService atmService = new ATMService(accountService, vault);

        // when
        MoneyAsNotes withdrawnMoney = atmService.withdraw("01001", new BigDecimal("20.00"));

        // then
        assertThat(withdrawnMoney.get(TWENTY)).isEqualTo(1);
    }

    @Test
    public void fiveNoteRuleAndMinimalNotesRuleWorkTogether() {
        // given
        MoneyAsNotes vault = MoneyAsNotes.createEmpty()
            .add(FIVE, 5)
            .add(TWENTY, 1);
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("2738.59"));
        ATMService atmService = new ATMService(accountService, vault);

        // when
        MoneyAsNotes withdrawnMoney = atmService.withdraw("01001", new BigDecimal("25.00"));

        // then
        assertThat(withdrawnMoney.get(FIVE)).isEqualTo(1);
        assertThat(withdrawnMoney.get(TWENTY)).isEqualTo(1);
    }

    @Test
    public void throwsExceptionWhenWithdrawingMoneyFromNonexistentAccount() {
        // given
        MoneyAsNotes vault = MoneyAsNotes.createEmpty()
            .add(TWENTY, 3)
            .add(TEN, 1)
            .add(FIFTY, 1);
        ATMService atmService = new ATMService(new AccountService(), vault);

        // when
        Throwable thrown = catchThrowable(() -> atmService.withdraw("01001", new BigDecimal("60.00")));

        // then
        assertThat(thrown)
            .isInstanceOf(AccountNotFoundException.class)
            .hasMessage("Account [01001] has not been found.");
    }

    @Test
    public void throwsExceptionWhenNotEnoughNotesInATM() {
        // given
        MoneyAsNotes vault = MoneyAsNotes.createEmpty()
            .add(TWENTY, 2);
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("2738.59"));
        ATMService atmService = new ATMService(accountService, vault);

        // when
        Throwable thrown = catchThrowable(() -> atmService.withdraw("01001", new BigDecimal("60.00")));

        // then
        assertThat(thrown)
            .isInstanceOf(NotEnoughNotesException.class);
    }

    @Test
    public void replenishesMoney() {
        // given
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("2738.59"));
        ATMService atmService = new ATMService(accountService, MoneyAsNotes.createEmpty());
        MoneyAsNotes moneyAsNotes = MoneyAsNotes.createEmpty()
            .add(FIVE, 4)
            .add(TEN, 3)
            .add(TWENTY, 2)
            .add(FIFTY, 1);

        // when
        atmService.replenish(moneyAsNotes);

        // then
        MoneyAsNotes withdrawnMoney = atmService.withdraw("01001", new BigDecimal("140.00"));
        assertThat(withdrawnMoney.get(FIVE)).isEqualTo(4);
        assertThat(withdrawnMoney.get(TEN)).isEqualTo(3);
        assertThat(withdrawnMoney.get(TWENTY)).isEqualTo(2);
        assertThat(withdrawnMoney.get(FIFTY)).isEqualTo(1);

        // and
        assertThat(catchThrowable(() -> atmService.withdraw("01001", new BigDecimal("50.00")))).isInstanceOf(NotEnoughNotesException.class);
        assertThat(catchThrowable(() -> atmService.withdraw("01001", new BigDecimal("20.00")))).isInstanceOf(NotEnoughNotesException.class);
    }

    @Test
    public void throwsExceptionWhenInsufficientFunds() {
        // given
        MoneyAsNotes moneyAsNotes = MoneyAsNotes.createEmpty()
            .add(FIFTY, 1);
        AccountService accountService = new AccountService();
        accountService.createAccount("01001", new BigDecimal("49.99"));
        ATMService atmService = new ATMService(accountService, moneyAsNotes);

        // when
        Throwable throwable = catchThrowable(() -> atmService.withdraw("01001", new BigDecimal("50.00")));

        // and
        assertThat(throwable).isInstanceOf(InsufficientFundsException.class);
    }

}