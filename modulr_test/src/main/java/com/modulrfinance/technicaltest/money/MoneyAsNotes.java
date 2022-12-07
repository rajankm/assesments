package com.modulrfinance.technicaltest.money;

import java.util.EnumMap;





/**
 * This class represents a collection of paper currency with notes of various denominations.
 *
 * It is used to represent both
 * the "vault" of the ATM: the entire collection of notes in the ATM machine
 * AND
 * the notes that the user receives in their withdrawal from the ATM.
 */
public class MoneyAsNotes {

    private final EnumMap<Denomination, Long> money;
    private boolean ordered;

    private MoneyAsNotes() {
        money = new EnumMap<>(Denomination.class);
        for (Denomination denomination : Denomination.valuesDescending(false)) {
            money.put(denomination, 0L);
        }
    }

    public static MoneyAsNotes createEmpty() {
        return new MoneyAsNotes();
    }

    public long get(Denomination denomination) {
        return money.get(denomination);
    }

    public MoneyAsNotes add(Denomination denomination, long quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot add a negative quantity of notes.");
        }
        MoneyAsNotes newMoneyAsNotes = copy();
        newMoneyAsNotes.money.put(denomination, newMoneyAsNotes.money.get(denomination) + quantity);
        return newMoneyAsNotes;
    }

    public MoneyAsNotes add(MoneyAsNotes other) {
        MoneyAsNotes newMoneyAsNotes = copy();
        for (Denomination denomination : newMoneyAsNotes.money.keySet()) {
            newMoneyAsNotes = newMoneyAsNotes.add(denomination, other.get(denomination));
        }
        return newMoneyAsNotes;
    }

    public MoneyAsNotes remove(Denomination denomination, long quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot remove a negative quantity of notes.");
        }
        MoneyAsNotes newMoneyAsNotes = copy();
        Long currentQuantity = newMoneyAsNotes.money.get(denomination);
        if (quantity > currentQuantity) {
            throw new IllegalArgumentException("Not enough notes in the pool to remove.");
        }
        newMoneyAsNotes.money.put(denomination, currentQuantity - quantity);
        return newMoneyAsNotes;
    }

    public MoneyAsNotes remove(MoneyAsNotes other) {
        MoneyAsNotes newMoneyAsNotes = copy();
        for (Denomination denomination : money.keySet()) {
            newMoneyAsNotes = newMoneyAsNotes.remove(denomination, other.get(denomination));
        }
        return newMoneyAsNotes;
    }

    private MoneyAsNotes copy() {
        MoneyAsNotes newMoneyAsNotes = createEmpty();
        for (Denomination denomination : money.keySet()) {
            newMoneyAsNotes.money.put(denomination, get(denomination));
        }
        return newMoneyAsNotes;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }
}
