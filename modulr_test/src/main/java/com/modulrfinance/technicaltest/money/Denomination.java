package com.modulrfinance.technicaltest.money;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public enum Denomination {
    FIVE(new BigDecimal("5.00")),
    TEN(new BigDecimal("10.00")),
    TWENTY(new BigDecimal("20.00")),
    FIFTY(new BigDecimal("50.00")),
    ;

    private final BigDecimal value;

    Denomination(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal value() {
        return value;
    }

    public static List<Denomination> valuesDescending(boolean ordered) {
        if(ordered){
            return Arrays.stream(Denomination.values())
                    .collect(toList());
        }
        return Arrays.stream(Denomination.values())
            .sorted(Comparator.reverseOrder())
            .collect(toList());
    }
    /*public static List<Denomination> valuesAscending() {
        return Arrays.stream(Denomination.values())
                .collect(toList());
    }*/
}
