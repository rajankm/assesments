package com.modulrfinance.technicaltest.atm;

import com.modulrfinance.technicaltest.account.AccountService;
import com.modulrfinance.technicaltest.money.Currency;
import com.modulrfinance.technicaltest.money.Denomination;
import com.modulrfinance.technicaltest.money.MoneyAsNotes;

import java.math.BigDecimal;
import java.text.DecimalFormat;

class ATMService {

    private AccountService accountService;
    private MoneyAsNotes vault;
    private Currency currency;

    ATMService(AccountService accountService, MoneyAsNotes vault) {
        this.accountService = accountService;
        this.vault = vault;
        this.currency = Currency.EURO; //setting as default value
    }

    String checkBalance(String accountNumber) {
        BigDecimal balance = accountService.checkBalance(accountNumber);
        DecimalFormat df = new DecimalFormat("#,###.00");
        return currency.value()+df.format(balance);
    }
    void replenish(MoneyAsNotes moneyAsNotes) {
        vault = vault.add(moneyAsNotes);
    }

    MoneyAsNotes withdraw(String accountNumber, BigDecimal amount) {
        MoneyAsNotes withdrawalPool = prepareMoney(amount);
        accountService.withdraw(accountNumber, amount);
        vault = vault.remove(withdrawalPool);
        return withdrawalPool;
    }

    private MoneyAsNotes prepareMoney(BigDecimal amount) {
        MoneyAsNotes moneyAsNotes = prepareMoneyStep(amount, vault);
        if (moneyAsNotes == null) {
            throw new NotEnoughNotesException();
        }
        return moneyAsNotes;
    }

    private MoneyAsNotes prepareMoneyStep(BigDecimal amount, MoneyAsNotes remainingVault) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            return MoneyAsNotes.createEmpty();
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return null;
        }
        for (Denomination denomination : Denomination.valuesDescending(remainingVault.isOrdered())) {
            if (remainingVault.get(denomination) > 0) {
                BigDecimal remainingAmount = amount.subtract(denomination.value());
                MoneyAsNotes moneyAsNotes = prepareMoneyStep(remainingAmount, remainingVault.remove(denomination, 1));
                if (moneyAsNotes != null) {
                    return moneyAsNotes.add(denomination, 1);
                }
            }
        }
        return null;
    }

 /*   public Currency getCurrency() {
        return currency;
    }*/

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
