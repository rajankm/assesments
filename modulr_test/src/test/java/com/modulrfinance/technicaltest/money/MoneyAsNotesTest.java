package com.modulrfinance.technicaltest.money;

import org.junit.Test;

import static com.modulrfinance.technicaltest.money.Denomination.FIFTY;
import static com.modulrfinance.technicaltest.money.Denomination.FIVE;
import static com.modulrfinance.technicaltest.money.Denomination.TEN;
import static com.modulrfinance.technicaltest.money.Denomination.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class MoneyAsNotesTest {

    @Test
    public void addsNotesToMoneyPool() {
        // given
        MoneyAsNotes moneyAsNotes = MoneyAsNotes.createEmpty();

        // when
        moneyAsNotes = moneyAsNotes.add(FIVE, 1)
            .add(TEN, 2)
            .add(TWENTY, 3)
            .add(FIFTY, 4);

        // then
        assertThat(moneyAsNotes.get(FIVE)).isEqualTo(1);
        assertThat(moneyAsNotes.get(TEN)).isEqualTo(2);
        assertThat(moneyAsNotes.get(TWENTY)).isEqualTo(3);
        assertThat(moneyAsNotes.get(FIFTY)).isEqualTo(4);
    }

    @Test
    public void cannotAddNegativeQuantityOfNotes() {
        // given
        MoneyAsNotes moneyAsNotes = MoneyAsNotes.createEmpty()
            .add(FIVE, 10);

        // when
        Throwable thrown = catchThrowable(() -> moneyAsNotes.add(FIVE, -1));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
        assertThat(moneyAsNotes.get(FIVE)).isEqualTo(10);
    }

    @Test
    public void removesNotesFromMoneyPool() {
        // given
        MoneyAsNotes moneyAsNotes = MoneyAsNotes.createEmpty()
            .add(FIVE, 10);

        // when
        moneyAsNotes = moneyAsNotes.remove(FIVE, 3);

        // then
        assertThat(moneyAsNotes.get(FIVE)).isEqualTo(7);
    }

    @Test
    public void cannotRemoveNegativeQuantityOfNotes() {
        // given
        MoneyAsNotes moneyAsNotes = MoneyAsNotes.createEmpty()
            .add(FIVE, 10);

        // when
        Throwable thrown = catchThrowable(() -> moneyAsNotes.remove(FIVE, -1));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
        assertThat(moneyAsNotes.get(FIVE)).isEqualTo(10);
    }

    @Test
    public void cannotRemoveMoreThanCurrentQuantityOfNotes() {
        // given
        MoneyAsNotes moneyAsNotes = MoneyAsNotes.createEmpty()
            .add(FIVE, 10);

        // when
        Throwable thrown = catchThrowable(() -> moneyAsNotes.remove(FIVE, 15));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
        assertThat(moneyAsNotes.get(FIVE)).isEqualTo(10);
    }

    @Test
    public void removesMoneyPoolFromSourceMoneyPool() {
        // given
        MoneyAsNotes moneyAsNotes = MoneyAsNotes.createEmpty()
            .add(FIVE, 10)
            .add(TEN, 3);

        MoneyAsNotes anotherMoneyAsNotes = MoneyAsNotes.createEmpty()
            .add(FIVE, 3)
            .add(TEN, 1);

        // when
        moneyAsNotes = moneyAsNotes.remove(anotherMoneyAsNotes);

        // then
        assertThat(moneyAsNotes.get(FIVE)).isEqualTo(7);
        assertThat(moneyAsNotes.get(TEN)).isEqualTo(2);
    }

    @Test
    public void cannotRemoveMoneyPoolFromSourceMoneyPoolIfItDoesNotContainCorrespondingNotes() {
        // given
        MoneyAsNotes moneyAsNotes = MoneyAsNotes.createEmpty()
            .add(FIVE, 10);

        MoneyAsNotes anotherMoneyAsNotes = MoneyAsNotes.createEmpty()
            .add(TWENTY, 1);

        // when
        Throwable thrown = catchThrowable(() -> moneyAsNotes.remove(anotherMoneyAsNotes));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void addsAnotherMoneyPool() {
        // given
        MoneyAsNotes moneyAsNotes = MoneyAsNotes.createEmpty()
            .add(TWENTY, 3)
            .add(FIFTY, 4);

        MoneyAsNotes anotherMoneyAsNotes = MoneyAsNotes.createEmpty()
            .add(FIVE, 1)
            .add(TEN, 2)
            .add(TWENTY, 3);

        // when
        moneyAsNotes = moneyAsNotes.add(anotherMoneyAsNotes);

        // then
        assertThat(moneyAsNotes.get(FIVE)).isEqualTo(1);
        assertThat(moneyAsNotes.get(TEN)).isEqualTo(2);
        assertThat(moneyAsNotes.get(TWENTY)).isEqualTo(6);
        assertThat(moneyAsNotes.get(FIFTY)).isEqualTo(4);
    }
}
